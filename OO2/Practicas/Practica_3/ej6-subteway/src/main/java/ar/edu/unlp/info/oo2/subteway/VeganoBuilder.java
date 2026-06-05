package ar.edu.unlp.info.oo2.subteway;

public class VeganoBuilder extends SandwichBuilder{

	@Override
	public void buildPan() {
		this.sandwich.setPan("Pan Integral", 100);
	}

	@Override
	public void buildAderezo() {
		this.sandwich.setAderezo("Salsa Criolla", 20);
	}

	@Override
	public void buildPrincipal() {
		this.sandwich.setPrincipal("Milansea de girgolas", 500);
	}

	@Override
	public void buildAdicional() {
		this.sandwich.setAdicional(null, 0);
	}
	

}
