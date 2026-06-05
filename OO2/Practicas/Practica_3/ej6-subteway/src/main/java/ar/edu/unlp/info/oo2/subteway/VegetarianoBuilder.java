package ar.edu.unlp.info.oo2.subteway;

public class VegetarianoBuilder extends SandwichBuilder{

	@Override
	public void buildPan() {
		this.sandwich.setPan("Pan con Semillas", 120);
	}

	@Override
	public void buildAderezo() {
		this.sandwich.setAderezo(null, 0);
	}

	@Override
	public void buildPrincipal() {
		this.sandwich.setPrincipal("Provoleta Grillada", 200);
	}

	@Override
	public void buildAdicional() {
		this.sandwich.setAdicional("Berenjenas al Escabeche",100);
	}

}
