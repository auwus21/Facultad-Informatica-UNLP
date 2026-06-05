package ar.edu.unlp.info.oo2.topografias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopografiaTest {
    private Topografia agua;
    private Topografia tierra;
    private Topografia pantano;
    private Topografia mixtaSimple;
    private Topografia mixtaCompleja;
    private Topografia mixtaConPantano;

    @BeforeEach
    void setUp() {
        agua = new Agua();
        tierra = new Tierra();
        pantano = new Pantano();
        
        // Mixta simple: 3 aguas, 1 tierra. Proporción agua = 3/4 = 0.75
        mixtaSimple = new TopografiaMixta(agua, agua, agua, tierra);
        
        // Mixta compleja: agua, tierra, agua, y una mixta (agua, tierra, tierra, tierra = 0.25)
        // Proporción agua = (1.0 + 0.0 + 1.0 + 0.25) / 4 = 2.25 / 4 = 0.5625
        Topografia mixtaInterna = new TopografiaMixta(agua, tierra, tierra, tierra);
        mixtaCompleja = new TopografiaMixta(agua, tierra, agua, mixtaInterna);
        
        // Mixta con pantano: agua, tierra, pantano, pantano.
        // Proporción agua = (1.0 + 0.0 + 0.7 + 0.7) / 4 = 2.4 / 4 = 0.6
        mixtaConPantano = new TopografiaMixta(agua, tierra, pantano, pantano);
    }

    @Test
    void testProporciones() {
        assertEquals(1.0, agua.getProporcionAgua());
        assertEquals(0.0, agua.getProporcionTierra());

        assertEquals(0.0, tierra.getProporcionAgua());
        assertEquals(1.0, tierra.getProporcionTierra());

        assertEquals(0.7, pantano.getProporcionAgua());
        assertEquals(0.3, pantano.getProporcionTierra());

        assertEquals(0.75, mixtaSimple.getProporcionAgua());
        assertEquals(0.25, mixtaSimple.getProporcionTierra());

        assertEquals(0.5625, mixtaCompleja.getProporcionAgua());
        assertEquals(0.4375, mixtaCompleja.getProporcionTierra());

        assertEquals(0.6, mixtaConPantano.getProporcionAgua());
        assertEquals(0.4, mixtaConPantano.getProporcionTierra());
    }

    @Test
    void testEquals() {
        // Hojas iguales
        assertEquals(new Agua(), agua);
        assertEquals(new Tierra(), tierra);
        assertEquals(new Pantano(), pantano);
        assertNotEquals(agua, tierra);
        assertNotEquals(agua, pantano);
        assertNotEquals(tierra, pantano);

        // Mixtas iguales
        Topografia otraMixtaSimple = new TopografiaMixta(new Agua(), new Agua(), new Agua(), new Tierra());
        assertEquals(mixtaSimple, otraMixtaSimple);

        // Mixtas distintas (mismos elementos pero distinto orden)
        Topografia mixtaDiferenteOrden = new TopografiaMixta(new Tierra(), new Agua(), new Agua(), new Agua());
        assertNotEquals(mixtaSimple, mixtaDiferenteOrden);

        // Mixtas complejas iguales
        Topografia otraMixtaCompleja = new TopografiaMixta(
            new Agua(), 
            new Tierra(), 
            new Agua(), 
            new TopografiaMixta(new Agua(), new Tierra(), new Tierra(), new Tierra())
        );
        assertEquals(mixtaCompleja, otraMixtaCompleja);

        // Mixtas con pantano iguales
        Topografia otraMixtaConPantano = new TopografiaMixta(new Agua(), new Tierra(), new Pantano(), new Pantano());
        assertEquals(mixtaConPantano, otraMixtaConPantano);

        // Comparar tipos distintos
        assertNotEquals(agua, mixtaSimple);
    }
}
