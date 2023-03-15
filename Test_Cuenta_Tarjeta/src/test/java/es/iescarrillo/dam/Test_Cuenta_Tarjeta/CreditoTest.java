package es.iescarrillo.dam.Test_Cuenta_Tarjeta;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class CreditoTest {
	
	Cuenta cuenta1=new Cuenta ("ES0182666888", "Jose");
	
	@SuppressWarnings("deprecation")
	Date date1=new Date(2025, 2, 29);
	
	Credito credito1=new Credito("987654321", "Jose", date1, 5000);
	
	Movimiento m = new Movimiento();
	
	/**
	 * Test para comprobar si existe una cuenta asociada a la tarjeta, accediendo a la tarjeta a través de cuenta
	 * @throws Exception
	 */
	@Test
	public void cuentaAsociada() throws Exception {
		
		credito1.setCuenta(cuenta1);
		credito1.ingresar(1000);
		
		assertThat(cuenta1.getSaldo(), equalTo(1000.0));
	}
	
	@Test
	public void testMovimientoCredito() throws Exception {
		
		credito1.setCuenta(cuenta1);
		credito1.ingresar(1000);
				
		assertTrue(cuenta1.mMovimientos.size()!=0);
		
	}
	
	@Test
	public void testIngresoCredito() throws Exception {
		
		credito1.setCuenta(cuenta1);
		credito1.ingresar(2000);
		
		assertThat(cuenta1.getSaldo(), equalTo(2000.0));
	}
	
	/**
	 * El método devuelve la comisión que te cobran por hacer una retirada, un 5% de la cantidad retirada con un mínimo de 3 euros
	 * @throws Exception
	 */
	@Test
	public void testRetirarCredito() throws Exception {
	    
		credito1.setCuenta(cuenta1);
	    credito1.retirar(500);
	    
	    assertThat(credito1.getSaldo(), equalTo(25.0));
	}
	
	/**
	 * Para forzar que entre en la excepción, hay que retirar más del 5% del saldo de la tarjeta
	 * @throws Exception
	 */
	@Test
	public void retirarSaldoInsuficienteDebito() throws Exception {
							
		Throwable exception = assertThrows(Exception.class, () -> credito1.retirar(150000));
		
	    assertThat(exception.getMessage(), equalTo("Crédito insuficiente"));
		
		
	}
	
	@Test
	public void pagoEstablecimiento() throws Exception {
		
		credito1.setCuenta(cuenta1);
		credito1.pagoEnEstablecimiento("Amazonia", 60);
		
		assertTrue(credito1.getSaldo()<5000 );
	}
	
	@Test
	public void testLiquidar() throws Exception {
		
		credito1.setCuenta(cuenta1);
		
		credito1.ingresar(1000);
		
		credito1.liquidar(03, 2023);
		
		assertTrue(cuenta1.mMovimientos.firstElement()!=null);
	}

}
