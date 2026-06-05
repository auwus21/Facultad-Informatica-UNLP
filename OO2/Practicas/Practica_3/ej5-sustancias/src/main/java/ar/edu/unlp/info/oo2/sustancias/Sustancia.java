package ar.edu.unlp.info.oo2.sustancias;

public abstract class Sustancia {
    
    public abstract String getNombre();
    
    public abstract String formula();
    
    public abstract int pesoMolecular();
    
    public abstract int carga();
    
    public abstract boolean esMetal();
    
    public abstract boolean esValida();
    
    public abstract boolean esPura();

    public boolean esMolecular() {
        return this.carga() == 0;
    }

    public boolean esIonica() {
        return !this.esMolecular();
    }
}
