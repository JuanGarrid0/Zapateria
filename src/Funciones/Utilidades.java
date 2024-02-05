package Funciones;

import static org.junit.Assert.assertNotNull;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import Clases.*;

public class Utilidades {
	
	public static double sumaPrecio(List<Calzado> lista) {
		double precio =0;
		for (Calzado c : lista) {		
			precio=precio+c.getPrecio();
		}
		return precio;
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage) throws IOException {
        BufferedImage resizedImage = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 100, 50, null);
        g.dispose();
        return resizedImage;
    }
	public static BufferedImage getImage(String name) throws IOException {
		BufferedImage	bi = null;// BufferedImage bi=(resizeImage(ImageIO.read(new File("src\\imagenes\\"+name +".png"))));
		ImageIO imageIO= null;
		  try {
			  
			  	bi	=(resizeImage(ImageIO.read(new File("src\\imagenes\\"+name +".png"))));
	            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return bi;
    }

}
