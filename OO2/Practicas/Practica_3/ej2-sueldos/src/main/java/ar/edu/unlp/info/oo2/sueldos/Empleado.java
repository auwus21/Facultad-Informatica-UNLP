package ar.edu.unlp.info.oo2.sueldos;

public abstract class Empleado {
    private String nombre;
    
    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public final double sueldo() {
        double basico = this.sueldoBasico();
        double adicional = this.sueldoAdicional();
        return basico + adicional - this.descuento(basico, adicional);
    }

    private double descuento(double basico, double adicional) {
        return (basico * 0.13) + (adicional * 0.05);
    }
    
    public String getNombre() {
        return nombre;
    }

    protected abstract double sueldoBasico();
    protected abstract double sueldoAdicional();
}
