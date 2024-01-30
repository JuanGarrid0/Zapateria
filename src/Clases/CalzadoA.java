package Clases;

public class CalzadoA extends Calzado {
	
	String disenador;

	public String getDisenador() {
		return disenador;
	}

	public void setDisenador(String disenador) {
		this.disenador = disenador;
	}

	public CalzadoA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalzadoA(int codigo, String nombre, double precio, String color) {
		super(codigo, nombre, precio, color);
		// TODO Auto-generated constructor stub
	}
}
