package ar.edu.unlp.info.oo2.subteway;

public class SinTaccBuilder extends SandwichBuilder{

	@Override
	public void buildPan() {
		this.sandwich.setPan("Pan de Chipa", 150);
	}

	@Override
	public void buildAderezo() {
		this.sandwich.setAderezo("Salsa Tartara", 18);
	}

	@Override
	public void buildPrincipal() {
		this.sandwich.setPrincipal("Pollo", 250);
	}

	@Override
	public void buildAdicional() {
		this.sandwich.setAdicional("Verduras Grilladas", 200);
	}
	

}
