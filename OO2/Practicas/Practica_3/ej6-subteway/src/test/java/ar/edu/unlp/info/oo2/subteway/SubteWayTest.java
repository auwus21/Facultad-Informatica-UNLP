package ar.edu.unlp.info.oo2.subteway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubteWayTest {
    private SubteWayDirector director;

    @BeforeEach
    void setUp() {
        // Inicializamos el director. Le pasaremos el builder específico en cada test.
        director = new SubteWayDirector(null);
    }

    @Test
    void testSandwichClasico() {
        SandwichBuilder clasicoBuilder = new ClasicoBuilder();
        director.setBuilder(clasicoBuilder);
        director.construirSandwich();
        
        Sandwich clasico = director.getSandwich();
        assertNotNull(clasico);
        assertEquals(500.0, clasico.getPrecio());
        assertEquals("Pan Brioche", clasico.getPan());
        assertEquals("Mayonesa", clasico.getAderezo());
        assertEquals("Carne de Ternera", clasico.getPrincipal());
        assertEquals("Tomate", clasico.getAdicional());
    }

    @Test
    void testSandwichVegetariano() {
        SandwichBuilder vegetarianoBuilder = new VegetarianoBuilder();
        director.setBuilder(vegetarianoBuilder);
        director.construirSandwich();
        
        Sandwich vegetariano = director.getSandwich();
        assertNotNull(vegetariano);
        assertEquals(420.0, vegetariano.getPrecio());
        assertEquals("Pan con Semillas", vegetariano.getPan());
        assertNull(vegetariano.getAderezo());
        assertEquals("Provoleta Grillada", vegetariano.getPrincipal());
        assertEquals("Berenjenas al Escabeche", vegetariano.getAdicional());
    }

    @Test
    void testSandwichVegano() {
        SandwichBuilder veganoBuilder = new VeganoBuilder();
        director.setBuilder(veganoBuilder);
        director.construirSandwich();
        
        Sandwich vegano = director.getSandwich();
        assertNotNull(vegano);
        assertEquals(620.0, vegano.getPrecio());
        assertEquals("Pan Integral", vegano.getPan());
        assertEquals("Salsa Criolla", vegano.getAderezo());
        assertEquals("Milansea de girgolas", vegano.getPrincipal());
        assertNull(vegano.getAdicional());
    }

    @Test
    void testSandwichSinTacc() {
        SandwichBuilder sinTaccBuilder = new SinTaccBuilder();
        director.setBuilder(sinTaccBuilder);
        director.construirSandwich();
        
        Sandwich sinTacc = director.getSandwich();
        assertNotNull(sinTacc);
        assertEquals(618.0, sinTacc.getPrecio());
        assertEquals("Pan de Chipa", sinTacc.getPan());
        assertEquals("Salsa Tartara", sinTacc.getAderezo());
        assertEquals("Pollo", sinTacc.getPrincipal());
        assertEquals("Verduras Grilladas", sinTacc.getAdicional());
    }
}
