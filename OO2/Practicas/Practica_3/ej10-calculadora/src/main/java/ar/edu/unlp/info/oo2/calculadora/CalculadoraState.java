package ar.edu.unlp.info.oo2.calculadora;

public abstract class CalculadoraState {

	public void setValor(Calculadora calc , double unValor) {
		calc.setState(new ErrorState());
	}
	
	public void mas(Calculadora calc) {
		calc.setState(new ErrorState());
	}
	
	public void menos(Calculadora calc) {
		calc.setState(new ErrorState());
	}
	public void por(Calculadora calc) {
		calc.setState(new ErrorState());
	}
	public void dividido(Calculadora calc) {
		calc.setState(new ErrorState());
	}
	
	public abstract String getResultado(Calculadora calc);
	
	public void borrar(Calculadora calc) {
		calc.setAcumulado(0);
		calc.setState(new IdleState());
	}
}
