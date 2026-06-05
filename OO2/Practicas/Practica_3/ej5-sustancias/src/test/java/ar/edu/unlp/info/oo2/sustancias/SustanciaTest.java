package ar.edu.unlp.info.oo2.sustancias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SustanciaTest {
    private Atomo hidrogeno;
    private Atomo oxigeno;
    private Atomo sodio;
    private Atomo cloro;
    private Atomo calcio;

    private UnionQuimica agua; // H2O
    private UnionQuimica sal;  // NaCl
    private UnionQuimica hidroxilo; // OH
    private UnionQuimica hidroxidoCalcio; // Ca(OH)2
    private UnionQuimica unionInvalidaMetalMetal; // Na + Ca

    @BeforeEach
    void setUp() {
        // Inicializar átomos (nombre, simbolo, peso, carga, esMetal)
        hidrogeno = new Atomo("Hidrógeno", "H", 1, 1, false);
        oxigeno = new Atomo("Oxígeno", "O", 16, -2, false);
        sodio = new Atomo("Sodio", "Na", 23, 1, true);
        cloro = new Atomo("Cloro", "Cl", 35, -1, false);
        calcio = new Atomo("Calcio", "Ca", 40, 2, true);

        // Agua: H2O (dos H y un O)
        agua = new UnionQuimica("Agua");
        agua.agregarSustancia(hidrogeno);
        agua.agregarSustancia(hidrogeno);
        agua.agregarSustancia(oxigeno);

        // Sal de mesa: NaCl (un Na y un Cl)
        sal = new UnionQuimica("Sal de mesa");
        sal.agregarSustancia(sodio);
        sal.agregarSustancia(cloro);

        // Hidroxilo: OH (un O y un H)
        hidroxilo = new UnionQuimica("Hidroxilo");
        hidroxilo.agregarSustancia(oxigeno);
        hidroxilo.agregarSustancia(hidrogeno);

        // Hidróxido de calcio: Ca(OH)2 (un Ca y dos OH)
        hidroxidoCalcio = new UnionQuimica("Hidróxido de calcio");
        hidroxidoCalcio.agregarSustancia(calcio);
        hidroxidoCalcio.agregarSustancia(hidroxilo);
        hidroxidoCalcio.agregarSustancia(hidroxilo);

        // Unión inválida Metal + Metal: Na + Ca
        unionInvalidaMetalMetal = new UnionQuimica("Sodio y Calcio");
        unionInvalidaMetalMetal.agregarSustancia(sodio);
        unionInvalidaMetalMetal.agregarSustancia(calcio);
    }

    @Test
    void testPropiedadesSustanciasSimples() {
        assertTrue(hidrogeno.esPura());
        assertFalse(hidrogeno.esMetal());
        assertTrue(sodio.esMetal());
        assertEquals(1, hidrogeno.pesoMolecular());
        assertEquals(1, hidrogeno.carga());
        assertTrue(hidrogeno.esIonica());
        assertFalse(hidrogeno.esMolecular());
        assertTrue(hidrogeno.esValida());
        assertEquals("H", hidrogeno.formula());
    }

    @Test
    void testPropiedadesSustanciasCompuestas() {
        // Agua (H2O)
        assertFalse(agua.esPura());
        assertEquals(18, agua.pesoMolecular()); // 1 + 1 + 16 = 18
        assertEquals(0, agua.carga()); // 1 + 1 - 2 = 0
        assertTrue(agua.esMolecular());
        assertFalse(agua.esIonica());
        assertTrue(agua.esValida());
        assertEquals("H2O", agua.formula());

        // Sal (NaCl)
        assertEquals(58, sal.pesoMolecular()); // 23 + 35 = 58
        assertEquals(0, sal.carga()); // 1 - 1 = 0
        assertTrue(sal.esMolecular());
        assertEquals("NaCl", sal.formula());

        // Hidroxilo (OH) - Es un ion
        assertEquals(17, hidroxilo.pesoMolecular()); // 16 + 1 = 17
        assertEquals(-1, hidroxilo.carga()); // -2 + 1 = -1
        assertTrue(hidroxilo.esIonica());
        assertEquals("OH", hidroxilo.formula());

        // Hidróxido de calcio (Ca(OH)2)
        assertEquals(74, hidroxidoCalcio.pesoMolecular()); // 40 + (17 * 2) = 74
        assertEquals(0, hidroxidoCalcio.carga()); // 2 + (-1 * 2) = 0
        assertTrue(hidroxidoCalcio.esMolecular());
        assertEquals("Ca(OH)2", hidroxidoCalcio.formula());
    }

    @Test
    void testReglasCombinacion() {
        // H2O (No metal + No metal + No metal): Válido
        assertTrue(agua.esValida());

        // NaCl (Metal + No metal): Válido
        assertTrue(sal.esValida());

        // Ca(OH)2 (Metal + Subunión no metálica + Subunión no metálica): Válido
        assertTrue(hidroxidoCalcio.esValida());

        // Na + Ca (Metal + Metal): Inválido
        assertFalse(unionInvalidaMetalMetal.esValida());

        // Recursividad: Una unión que contiene una subunión inválida debe ser inválida
        UnionQuimica unionComplejaInvalida = new UnionQuimica("Union Compleja Inválida");
        unionComplejaInvalida.agregarSustancia(unionInvalidaMetalMetal);
        unionComplejaInvalida.agregarSustancia(oxigeno);
        assertFalse(unionComplejaInvalida.esValida());
    }
}
