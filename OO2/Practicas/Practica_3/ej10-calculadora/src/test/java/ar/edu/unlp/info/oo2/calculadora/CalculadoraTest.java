package ar.edu.unlp.info.oo2.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    void testOperacionesSimples() {
        calc.setValor(5);
        assertEquals("5.0", calc.resultado());

        calc.mas();
        calc.setValor(3);
        assertEquals("8.0", calc.resultado());

        calc.por();
        calc.setValor(2);
        assertEquals("16.0", calc.resultado());

        calc.menos();
        calc.setValor(4);
        assertEquals("12.0", calc.resultado());

        calc.dividido();
        calc.setValor(3);
        assertEquals("4.0", calc.resultado());
    }

    @Test
    void testDivisionPorCero() {
        calc.setValor(10);
        calc.dividido();
        calc.setValor(0);
        assertEquals("Error", calc.resultado());
    }

    @Test
    void testErrorTransicionInvalidaOperacionSeguida() {
        calc.setValor(5);
        calc.mas();
        calc.por(); // Operación en lugar de setValor() -> Error
        assertEquals("Error", calc.resultado());
    }

    @Test
    void testErrorTransicionInvalidaPedirResultado() {
        calc.setValor(5);
        calc.mas();
        // Pedir el resultado mientras espera un valor -> Error
        assertEquals("Error", calc.resultado());
    }

    @Test
    void testBorrarYRecuperar() {
        // Provocar un error
        calc.setValor(10);
        calc.dividido();
        calc.setValor(0);
        assertEquals("Error", calc.resultado());

        // Llamar a borrar para recuperar
        calc.borrar();
        assertEquals("0.0", calc.resultado());

        // Verificar que vuelve a funcionar
        calc.setValor(7);
        assertEquals("7.0", calc.resultado());
    }
}
