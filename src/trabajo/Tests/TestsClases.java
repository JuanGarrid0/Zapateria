package trabajo.Tests;
import Clases.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import Funciones.*;
public class TestsClases {
    
	Cliente c1= new Cliente("proyectodefinitivoprogram@gmail.com", "535535","Jony");
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
		assertNotEquals(z1.getPrecio(),z2.getPrecio(),0.01);
		
	}
	@Test
	public void testMail() {
		String[] splitString= c1.getCorreo().split("@");
		assertEquals(2, splitString.length);
		assertTrue(splitString[1].equals("gmail.com"));
		
	}

	
	@Test
	public void testCode() {
		
		assertTrue(z1.getCodigo() < z2.getCodigo());
	}
	@Test
	public void recursividadTest() {
		
		int password= Integer.parseInt(c1.getContrasena());
		assertTrue(Recursividad.capicua(password));
		
	}

	
}
