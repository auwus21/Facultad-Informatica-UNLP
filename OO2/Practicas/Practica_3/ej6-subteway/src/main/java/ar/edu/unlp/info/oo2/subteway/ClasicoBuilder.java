package ar.edu.unlp.info.oo2.subteway;

public class ClasicoBuilder extends SandwichBuilder {

	@Override
	public void buildPan() {
		this.sandwich.setPan("Pan Brioche", 100);
	}

	@Override
	public void buildAderezo() {
		this.sandwich.setAderezo("Mayonesa", 20);
	}

	@Override
	public void buildPrincipal() {
		this.sandwich.setPrincipal("Carne de Ternera", 300);
	}

	@Override
	public void buildAdicional() {
		this.sandwich.setAdicional("Tomate", 80);
	}

}
