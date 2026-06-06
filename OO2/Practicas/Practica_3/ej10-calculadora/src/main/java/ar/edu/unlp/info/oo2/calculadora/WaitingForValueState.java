package ar.edu.unlp.info.oo2.calculadora;

public class WaitingForValueState extends CalculadoraState {
    private Operacion operacion;

    public WaitingForValueState(Operacion operacion) {
        this.operacion = operacion;
    }

    @Override
    public void setValor(Calculadora calc, double unValor) {
        try {
            double resultado = this.operacion.aplicar(calc.getAcumulado(), unValor);
            calc.setAcumulado(resultado);
            calc.setState(new IdleState());
        } catch (ArithmeticException e) {
            calc.setState(new ErrorState());
        }
    }

    @Override
    public String getResultado(Calculadora calc) {
        calc.setState(new ErrorState());
        return "Error";
    }
}