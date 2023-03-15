package es.iescarrillo.dam.Test_Cuenta_Tarjeta;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class CuentaTest {
	
	Cuenta cuenta1= new Cuenta("ES12345", "Miguel");
	
	
	Movimiento m = new Movimiento();
	
	
	@Test
	public void testMovimiento() throws Exception {
		
		cuenta1.ingresar(1000);
		
		assertTrue(cuenta1.mMovimientos.size()>0);
		
	}
	
	@Test
	public void testIngresoCuenta() throws Exception {
		
		cuenta1.ingresar("ingreso en caja", 2000);
		
		assertTrue(cuenta1.getSaldo()>0);
		
	}
	
	@Test
	public void testIngresoCuentaMovimiento() throws ParseException {
		
		String sDate1="21/02/2023";
		java.util.Date 
		fecha=new SimpleDateFormat("dd/mm/yyyy").parse(sDate1);
		m.setFecha(fecha);
		m.setConcepto("Nomina");
		m.setImporte(2000);
		
		cuenta1.addMovimiento(m);
		
		assertTrue(cuenta1.getSaldo()!=0);
		
	}
	
	@Test
	public void testRetirarSaldoInsuf() throws Exception{
			
		Throwable exception = assertThrows(Exception.class, () -> cuenta1.retirar(200));
		
	    assertThat(exception.getMessage(), equalTo("Saldo insuficiente"));
				
	}
	
	
	@Test
	public void testRetirar() throws Exception {
	    
	    cuenta1.ingresar(1000);
	    cuenta1.retirar(500);
	    
	    assertThat(cuenta1.getSaldo(), equalTo(500.0));
	}
	
	@Test
	public void testRetirar2() throws Exception {
	    
	    cuenta1.ingresar(1000);
	    cuenta1.retirar("Retirada en caja", 500);
	    
	    assertThat(cuenta1.getSaldo(), equalTo(500.0));
	}
	
	@Test
	public void testIngresarNumNegativo() throws Exception {
		
		Throwable exception = assertThrows(Exception.class, () -> cuenta1.ingresar(-1000));
		
	    assertThat(exception.getMessage(), equalTo("No se puede ingresar una cantidad negativa"));
			
	}
	
	@Test
	public void testRetirarNumNegativo() throws Exception {
		
		Throwable exception = assertThrows(Exception.class, () -> cuenta1.retirar(-1000));
		
	    assertThat(exception.getMessage(), equalTo("No se puede retirar una cantidad negativa"));
			
	}
	
	@Test
	public void testIngresarNumNegativo1() throws Exception {
		
		Throwable exception = assertThrows(Exception.class, () -> cuenta1.ingresar("Ingreso en caja", -1000));
		
	    assertThat(exception.getMessage(), equalTo("No se puede ingresar una cantidad negativa"));
			
	}
	
	@Test
	public void testRetirarNumNegativo1() throws Exception {
		
		Throwable exception = assertThrows(Exception.class, () -> cuenta1.retirar("Retirada en caja",-1000));
		
	    assertThat(exception.getMessage(), equalTo("No se puede retirar una cantidad negativa"));
			
	}
	
	@Test
	public void testRetirarSaldoInsuf1() throws Exception{
			
		Throwable exception = assertThrows(Exception.class, () -> cuenta1.retirar("Retirada en caja",200));
		
	    assertThat(exception.getMessage(), equalTo("Saldo insuficiente"));
				
	}
	
	/*
	@ParameterizedTest
	public void testIngreso() throws Exception {
		
		cuenta1.ingresar("ingreso efectivo", -1000);
		cuenta1.ingresar(2000);
		
		
	}*/
	
	

}
