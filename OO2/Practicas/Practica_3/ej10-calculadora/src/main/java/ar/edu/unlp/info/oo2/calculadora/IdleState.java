package ar.edu.unlp.info.oo2.calculadora;

public class IdleState extends CalculadoraState {

    @Override
    public void setValor(Calculadora calc, double unValor) {
        calc.setAcumulado(unValor);
    }

    @Override
    public void mas(Calculadora calc) {
        calc.setState(new WaitingForValueState(Operacion.SUMA));
    }

    @Override
    public void menos(Calculadora calc) {
        calc.setState(new WaitingForValueState(Operacion.RESTA));
    }

    @Override
    public void por(Calculadora calc) {
        calc.setState(new WaitingForValueState(Operacion.MULTIPLICACION));
    }

    @Override
    public void dividido(Calculadora calc) {
        calc.setState(new WaitingForValueState(Operacion.DIVISION));
    }

    @Override
    public String getResultado(Calculadora calc) {
        return String.valueOf(calc.getAcumulado());
    }
}