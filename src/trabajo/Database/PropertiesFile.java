package trabajo.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesFile {
	
	final static String PROPERTIES_FILE = "src/datos/base.properties";

	public static void actualizarPropiedades() {
		
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
        	File file = new File(PROPERTIES_FILE);
        	if(file.exists()) {
        		inputStream = new FileInputStream(PROPERTIES_FILE);
        		properties.load(inputStream);
        	}
        	//properties.setProperty("calzado", "");
            properties.setProperty("driver", "org.sqlite.JDBC");
            properties.setProperty("file", "C:\\Users\\Alumno\\git\\Trabajo\\src\\datos");
            properties.setProperty("connection", "jdbc:sqlite:src/datos/database.db");

            outputStream = new FileOutputStream(PROPERTIES_FILE);
            properties.store(outputStream, null);

            System.out.println("Datos actualizados correctamente");
        } catch (IOException ex) {
	        ex.printStackTrace();
            System.out.println("Error al actualizar los datos");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
		        ex.printStackTrace();
            }
        }
    }
	public static Properties cargarProperties() {
		
		
    	Properties properties = new Properties();
        FileInputStream inputStream = null;

        try {
        	File file = new File(PROPERTIES_FILE);
        	if (!file.exists()) {
        		System.out.println("El archivo no existe");
        	}
            inputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(inputStream);
            
        } catch (IOException e) {
	        e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
    		        e.printStackTrace();
                }
            }
        }
        
        return properties;
    }
	
	public static void main(String[] args) {
		actualizarPropiedades();
	}
}
