package ar.edu.unlp.info.oo2.subteway;

public class SubteWayDirector {
    private SandwichBuilder builder;

    public SubteWayDirector(SandwichBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(SandwichBuilder builder) {
        this.builder = builder;
    }

    public void construirSandwich() {
        this.builder.crearNuevoSandwich();
        this.builder.buildPan();
        this.builder.buildAderezo();
        this.builder.buildPrincipal();
        this.builder.buildAdicional();
    }

    public Sandwich getSandwich() {
        return this.builder.getSandwich();
    }
}
