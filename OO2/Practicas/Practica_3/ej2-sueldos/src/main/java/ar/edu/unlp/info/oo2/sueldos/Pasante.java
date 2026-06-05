package ar.edu.unlp.info.oo2.sueldos;

public class Pasante extends Empleado {
	
	private int cantidadExamenesRendidos;
	
	public Pasante(String nombre,int cantidadExamenesRendidos) {
		super(nombre);
		this.cantidadExamenesRendidos = cantidadExamenesRendidos;
	}
	
	
	

	public int getCantidadExamenesRendidos() {
		return cantidadExamenesRendidos;
	}




	@Override
	protected double sueldoBasico() {
		return 20000;
	}

	@Override
	protected double sueldoAdicional() {
		return this.getCantidadExamenesRendidos()*2000;
	}

	

}
