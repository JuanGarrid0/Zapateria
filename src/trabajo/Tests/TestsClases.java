package trabajo.Tests;
import Clases.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import Funciones.*;
public class TestsClases {
    
	Cliente c1= new Cliente("Jony", "proyectodefinitivoprogram@gmail.com", "535535");
	Calzado z1 = new Calzado(01, "Sevilla", 8.99, "Rojo");
	Calzado z2 = new Calzado(02, "Barcelona", 9.99, "Azul");
	Recursividad recursividad=new Recursividad();
	
	
	@Test
	public void testPass() {
		String passString= c1.getContrasena();
		String[] c= passString.split("");
		assertTrue(c.length<8);
		
	}
	
	
	@Test
	public void testPrecio() {
		assertEquals(z1.getPrecio(),z2.getPrecio());
		
	}
	@Test
	public void testMail() {
		String[] splitString= c1.getCorreo().split("@");
		assertEquals(splitString.length,2);
		assertEquals(splitString[1].equals("@gmail.com"),0);
		
	}

	
	@Test
	public void testCode() {
		
		assertTrue(z1.getCodigo() < z2.getCodigo());
	}
	@Test
	public void recursividadTest() {
		
		int password= Integer.parseInt(c1.getContrasena());
		assertFalse(Recursividad.capicua(password));
		
	}

	
}
