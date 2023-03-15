package es.iescarrillo.dam.Test_Cuenta_Tarjeta;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class DebitoTest {
	
	Cuenta cuenta1=new Cuenta ("DE12345", "Yeray");
	
	@SuppressWarnings("deprecation")
	Date date1=new Date(2023, 8, 15);
	
	Debito debito1=new Debito("2345687", "Yeray", date1);
	
	/**
	 * Test para comprobar si existe una cuenta asociada a la tarjeta, accediendo a la tarjeta a través de cuenta
	 * @throws Exception
	 */
	@Test
	public void cuentaAsociada() throws Exception {
		
		debito1.setCuenta(cuenta1);
		cuenta1.ingresar(1000);
		
		assertThat(debito1.getSaldo(), equalTo(1000.0));
	}
	
	@Test
	public void testMovimientoDebito() throws Exception {
		
		debito1.setCuenta(cuenta1);
		debito1.ingresar(1000);
				
		assertTrue(cuenta1.mMovimientos.size()!=0);
		
	}
	
	@Test
	public void testIngresoDebito() throws Exception {
		
		debito1.setCuenta(cuenta1);
		debito1.ingresar(2000);
		
		assertTrue(cuenta1.getSaldo()>0);
	}
	
	@Test
	public void testRetirarDebito() throws Exception {
	    
		debito1.setCuenta(cuenta1);
	    debito1.ingresar(1000);
	    debito1.retirar(500);
	    
	    assertThat(cuenta1.getSaldo(), equalTo(500.0));
	}
	
	@Test
	public void testIngresarNumNegativoDebito() throws Exception {
		
		debito1.setCuenta(cuenta1);
		
		Throwable exception = assertThrows(Exception.class, () -> debito1.ingresar(-1000));
		
	    assertThat(exception.getMessage(), equalTo("No se puede ingresar una cantidad negativa"));
			
	}
	
	@Test
	public void retirarSaldoInsuficienteDebito() throws Exception {
		
		debito1.setCuenta(cuenta1);
				
		Throwable exception = assertThrows(Exception.class, () -> debito1.retirar(1000));
		
	    assertThat(exception.getMessage(), equalTo("Saldo insuficiente"));
		
		
	}
	
	@Test
	public void pagoEstablecimiento() throws Exception {
		
		debito1.setCuenta(cuenta1);
		debito1.ingresar(500);
		debito1.pagoEnEstablecimiento("Amazonia", 60);
		
		assertTrue(debito1.getSaldo()<500 );
	}
	
	@Test
	public void saldo() throws Exception {
		
		debito1.setCuenta(cuenta1);
		debito1.ingresar(500);
		debito1.retirar(500);
		
		assertTrue(debito1.getSaldo()==0 );
	}
	
	
	

}
