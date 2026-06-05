package ar.edu.unlp.info.oo2.topografias;

public abstract class Topografia {
    
    public abstract double getProporcionAgua();
    
    public double getProporcionTierra() {
        return 1.0 - this.getProporcionAgua();
    }
    
    
}
