package Clases;


public class Calzado {
	private int Codigo;
	private String Nombre;
	private double Precio;
	private String Color;
	
	
	public Calzado(int codigo, String nombre,  double precio, String color) {
		super();
		Codigo = codigo;
		Nombre = nombre;
		Precio = precio;
		Color = color;
		
	}


	public Calzado() {
		// TODO Auto-generated constructor stub
	}



	public int getCodigo() {
		return Codigo;
	}


	public void setCodigo(int codigo) {
		Codigo = codigo;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public double getPrecio() {
		return Precio;
	}


	public void setPrecio(double precio) {
		Precio = precio;
	}


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
	}


	

	
	

}
