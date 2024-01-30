package Clases;

public class Cliente {
	  private String Nombre;
	  private String Correo;
	  private String Contrasena;
	 
	  
	public Cliente(String nombre, String correo, String contrasena) {
		super();
		Nombre = nombre;
		Correo = correo;
		Contrasena = contrasena;
		
	}


	public Cliente() {
		// TODO Auto-generated constructor stub
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getCorreo() {
		return Correo;
	}


	public void setCorreo(String correo) {
		Correo = correo;
	}


	public String getContrasena() {
		return Contrasena;
	}


	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}


	@Override
	public String toString() {
		return "Usuario [Nombre=" + Nombre + ", Correo=" + Correo + ", Contraseña=" + Contrasena + "]";
	}


	  
		  
}
