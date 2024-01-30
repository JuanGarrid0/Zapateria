package Clases;

public class CalzadoB extends Calzado{

	int nivel;

	public CalzadoB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalzadoB(int codigo, String nombre, double precio, String color) {
		super(codigo, nombre, precio, color);
		// TODO Auto-generated constructor stub
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
