package ar.edu.unlp.info.oo2.sueldos;

public class Planta extends Empleado{
	private int cantidadHijos;
    private int antiguedad;
    private boolean casado;

	public Planta(String nombre,int cantidadHijos ,int antiguedad,boolean casado) {
		super(nombre);
		this.cantidadHijos = cantidadHijos;
		this.antiguedad = antiguedad;
		this.casado = casado;
	}
	
	

	public int getCantidadHijos() {
		return cantidadHijos;
	}



	public int getAntiguedad() {
		return antiguedad;
	}



	public boolean isCasado() {
		return casado;
	}



	@Override
	protected double sueldoBasico() {
		return 50000;
	}

	@Override
	protected double sueldoAdicional() {
		int total = 0;
		if(this.casado) {
			total+= 5000;
		}
		return total+(this.getCantidadHijos()*2000)+(this.getAntiguedad()*2000);
	}

}
