package ar.edu.unlp.info.oo2.calculadora;

public class Calculadora {
    private double acumulado;
    private CalculadoraState state;
    
    public Calculadora() {
        this.acumulado = 0.0;
        this.state = new IdleState();
    }
    
    
    protected void setState(CalculadoraState state) {
    	this.state = state;
    }

    /**
     * Devuelve el resultado actual de la operación realizada.
     * Si no se ha realizado ninguna operación, devuelve el valor acumulado.
     * Si la calculadora se encuentra en error, devuelve “Error”.
     */
    public String getResultado() {
        return this.state.getResultado(this);
    }

    /**
     * Alias de getResultado() para soportar el caso de uso del ejemplo de la práctica.
     */
    public String resultado() {
        return this.getResultado();
    }

    /**
     * Pone en cero el valor acumulado y reinicia la calculadora.
     */
    public void borrar() {
    	this.state.borrar(this);
    }

    /**
     * Asigna un valor para operar.
     * Si hay una operación en curso, el valor será utilizado en la operación.
     */
    public void setValor(double unValor) {
        this.state.setValor(this, unValor);
    }
    /**
     * Indica que la calculadora debe esperar un nuevo valor para sumar.
     */
    public void mas() {
    	this.state.mas(this);
    }

    /**
     * Indica que la calculadora debe esperar un nuevo valor para restar.
     */
    public void menos() {
    	this.state.menos(this);
    }

    /**
     * Indica que la calculadora debe esperar un nuevo valor para multiplicar.
     */
    public void por() {
    	this.state.por(this);
    }

    /**
     * Indica que la calculadora debe esperar un nuevo valor para dividir.
     */
    public void dividido() {
        this.state.dividido(this);
    }

    // Getters y setters auxiliares para los estados si los necesitás
    public double getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(double acumulado) {
        this.acumulado = acumulado;
    }
}
