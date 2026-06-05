package ar.edu.unlp.info.oo2.sueldos;

public class Temporario extends Empleado {
    private int cantidadHijos;
    private double horasTrabajadas;
    private boolean casado;

    public Temporario(String nombre, int cantidadHijos, double horasTrabajadas, boolean casado) {
        super(nombre);
        this.cantidadHijos = cantidadHijos;
        this.horasTrabajadas = horasTrabajadas;
        this.casado = casado;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public boolean isCasado() {
        return casado;
    }

    @Override
    protected double sueldoBasico() {
        return 20000 + (this.getHorasTrabajadas() * 300);
    }

    @Override
    protected double sueldoAdicional() {
        double adicional = 0;
        if (this.isCasado()) {
            adicional += 5000;
        }
        return adicional + (this.getCantidadHijos() * 2000);
    }
}
