package ar.edu.unlp.info.oo2.sueldos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    private Temporario temporarioCasadoConHijos;
    private Pasante pasanteConExamenes;
    private Planta plantaCasadoConHijoYAntiguedad;

    @BeforeEach
    void setUp() {
        // Temporario: nombre, cantidadHijos, horasTrabajadas, casado
        temporarioCasadoConHijos = new Temporario("Juan", 2, 10, true);

        // Pasante: nombre, cantidadExamenesRendidos
        pasanteConExamenes = new Pasante("Ana", 3);

        // Planta: nombre, cantidadHijos, antiguedad, casado
        plantaCasadoConHijoYAntiguedad = new Planta("Maria", 1, 5, true);
    }

    @Test
    void testSueldoTemporario() {
        // Básico = 20000 + (10 * 300) = 23000
        // Adicional = 5000 + (2 * 2000) = 9000
        // Descuento = (23000 * 0.13) + (9000 * 0.05) = 2990 + 450 = 3440
        // Neto = 23000 + 9000 - 3440 = 28560
        assertEquals(28560.0, temporarioCasadoConHijos.sueldo());
    }

    @Test
    void testSueldoPasante() {
        // Básico = 20000
        // Adicional = 3 * 2000 = 6000
        // Descuento = (20000 * 0.13) + (6000 * 0.05) = 2600 + 300 = 2900
        // Neto = 20000 + 6000 - 2900 = 23100
        assertEquals(23100.0, pasanteConExamenes.sueldo());
    }

    @Test
    void testSueldoPlanta() {
        // Básico = 50000
        // Adicional = 5000 + (1 * 2000) + (5 * 2000) = 17000
        // Descuento = (50000 * 0.13) + (17000 * 0.05) = 6500 + 850 = 7350
        // Neto = 50000 + 17000 - 7350 = 59650
        assertEquals(59650.0, plantaCasadoConHijoYAntiguedad.sueldo());
    }
}
