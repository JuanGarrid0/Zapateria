package trabajo.Database;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import Clases.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.invoke.StringConcatFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class GestorBD {
	private final String PROPERTIES_FILE = "src/datos/base.properties";

	private Properties properties;
	private String driverName;
	private String databaseFile;
	private String connectionString;

	private static Logger logger = Logger.getLogger(GestorBD.class.getName());
	
	public GestorBD() {
		try {				
			properties = new Properties();
			properties.load(new FileReader(PROPERTIES_FILE));
			
			driverName = properties.getProperty("driver");
			connectionString = properties.getProperty("connection");
		
			Class.forName(driverName);
		} catch (Exception ex) {
			logger.warning(String.format("Error al cargar el driver de BBDD: %s", ex.getMessage()));
		}
}
	
	public void crearBBDD() {

		String sql1 = "CREATE TABLE IF NOT EXISTS Cliente (\n"
                + " correo TEXT PRIMARY KEY,\n"
                + " contrasena TEXT NOT NULL,\n"
                + " nombre TEXT NOT NULL,\n"
                + ");";
;

		String sql2 = "CREATE TABLE IF NOT EXISTS Calzado (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " nombre TEXT NOT NULL,\n"
                + " precio REAL NOT NULL\n"
                + " color TEXT NOT NULL,\n;"
                + ");";
;

		
		
		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt1 = con.prepareStatement(sql1);
			 PreparedStatement pStmt2 = con.prepareStatement(sql2);) {
			
	        if (!pStmt1.execute() && !pStmt2.execute()) {
	        	logger.info("Se han creado las tablas");
	        }
		} catch (Exception ex) {
			logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
		}
	}
	
	
	public void insertarCliente(List<Cliente> clientes) {
		
		String sql = "INSERT INTO Cliente (correo, contrasena, nombre) VALUES (?, ?, ?);";
		
		try (Connection con = DriverManager.getConnection(connectionString);
			 PreparedStatement pStmt = con.prepareStatement(sql)) {
									
			for (Cliente p : clientes) {
				pStmt.setString(1, p.getCorreo());
				pStmt.setString(2, p.getNombre());
				pStmt.setString(3, p.getContrasena());
				
				if (pStmt.executeUpdate() != 1) {					
					logger.warning(String.format("No se ha insertado el Cliente: %s", p));
				} else {				
					logger.info(String.format("Se ha insertado el Cliente: %s", p));
				}
			}
			
			logger.info(String.format("%d Clientes insertados en la BBDD", clientes.size()));
		} catch (Exception ex) {
			logger.warning(String.format("Error al insertar clientes: %s", ex.getMessage()));
		}			
	}
	
	
	public List<Cliente> getClientesList() {
		List<Cliente> c = new ArrayList<>();
		String sql = "SELECT * FROM Cliente";

		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			ResultSet rs = pStmt.executeQuery();			
			Cliente cl;
			
			while (rs.next()) {
				cl = new Cliente(rs.getString("correo"), 
						rs.getString("nombre"), 
						rs.getString("contrasena") );
				c.add(cl);
			}
			
			rs.close();
			logger.info(String.format("Se han recuperado %d clientes.", c.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los clientes: %s", ex.getMessage()));						
		}		
		
		return c;
	}
	
	
	public void insertarCalzado(List<Calzado> calzados) {
		
		String sql = "INSERT INTO Calzado (id, nombre, precio, color) VALUES (?, ?, ?, ? );";
		
		try (Connection con = DriverManager.getConnection(connectionString);
			 PreparedStatement pStmt = con.prepareStatement(sql)) {
									
			for (Calzado p : calzados) {
				pStmt.setInt(1, p.getCodigo());
				pStmt.setString(2, p.getNombre());
				pStmt.setDouble(3, p.getPrecio());
				pStmt.setString(4, p.getColor());
				
				if (pStmt.executeUpdate() != 1) {					
					logger.warning(String.format("No se ha insertado el Calzado: %s", p));
				} else {				
					logger.info(String.format("Se ha insertado el Calzado: %s", p));
				}
			}
			
			logger.info(String.format("%d Calzados insertados en la BBDD", calzados.size()));
		} catch (Exception ex) {
			logger.warning(String.format("Error al insertar calzados: %s", ex.getMessage()));
		}			
	}
	
	
	public List<Calzado> getCalzadoList() {
		List<Calzado> c = new ArrayList<>();
		String sql = "SELECT * FROM Calzado";

		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			ResultSet rs = pStmt.executeQuery();			
			Calzado cz;
			
			while (rs.next()) {
				cz = new Calzado(rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getDouble("precio"),
						rs.getString("color") );
				c.add(cz);
			}
			
			rs.close();
			logger.info(String.format("Se han recuperado %d calzados.", c.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los calzados: %s", ex.getMessage()));						
		}		
		
		return c;
	}
	
	public boolean buscarUsuarioRegistrado (String correo, String contrasena) {
		boolean busqueda_usuario = false;
		final ResultSet resultado;

		String sql="SELECT CORREO, CONTRASENA FROM CLIENTE WHERE CORREO = ? AND CONTRASEÑA = ?";
		
		try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt = con.prepareStatement(sql)) {
			pStmt.setString(1, correo);
			pStmt.setString(2, contrasena);
			
			resultado = pStmt.executeQuery();
			
			if(resultado.next()) {
				busqueda_usuario = true;
			}else {
				busqueda_usuario = false;
			}
			
			con.close();
			
		} catch (Exception ex) {
			logger.warning(String.format("Error, cliente no encontrado: %s", ex.getMessage()));						
		}	
		
		return busqueda_usuario;
	}
	
	
	
	
	
	
	
}
