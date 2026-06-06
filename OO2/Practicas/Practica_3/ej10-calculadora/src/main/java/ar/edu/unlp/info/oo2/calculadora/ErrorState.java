package ar.edu.unlp.info.oo2.calculadora;

public class ErrorState extends CalculadoraState {

    @Override
    public String getResultado(Calculadora calc) {
        return "Error";
    }
}