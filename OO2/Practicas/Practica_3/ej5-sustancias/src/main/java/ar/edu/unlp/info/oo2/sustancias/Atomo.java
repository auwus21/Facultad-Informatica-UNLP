package ar.edu.unlp.info.oo2.sustancias;

public class Atomo extends Sustancia {
	private String nombre;
	private String simbolo;
	private int pesoAtomico;
	private int carga;
	private Boolean esMetal;
	
	
	public Atomo(String nombre,String simbolo,int pesoAtomico,int carga,Boolean esMetal) {
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.pesoAtomico = pesoAtomico;
		this.carga = carga;
		this.esMetal = esMetal;
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}
	@Override
	public String formula() {
		return this.simbolo;
	}

	@Override
	public int pesoMolecular() {
		return this.pesoAtomico;
	}
	@Override
	public int carga() {
		return this.carga;
	}
	@Override
	public boolean esMetal() {
		return this.esMetal;
	}
	@Override
	public boolean esValida() {
		return true;
	}
	@Override
	public boolean esPura() {
		return true;
	}
	
	
	
	

}
