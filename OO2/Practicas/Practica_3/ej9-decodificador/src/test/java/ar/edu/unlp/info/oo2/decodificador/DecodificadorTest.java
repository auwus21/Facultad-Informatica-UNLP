package ar.edu.unlp.info.oo2.decodificador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DecodificadorTest {
    private Decodificador decodificador;
    private Pelicula thor;
    private Pelicula capitanAmerica;
    private Pelicula ironMan;
    private Pelicula dunkirk;
    private Pelicula rocky;
    private Pelicula rambo;

    @BeforeEach
    void setUp() {
        decodificador = new Decodificador();

        // Crear películas
        thor = new Pelicula("Thor", 7.9, 2007);
        capitanAmerica = new Pelicula("Capitan America", 7.8, 2016);
        ironMan = new Pelicula("Iron man", 7.9, 2010);
        dunkirk = new Pelicula("Dunkirk", 7.9, 2017);
        rocky = new Pelicula("Rocky", 8.1, 1976);
        rambo = new Pelicula("Rambo", 7.8, 1979);

        // Configurar similitudes (recíprocas)
        thor.agregarSimilar(capitanAmerica);
        thor.agregarSimilar(ironMan);
        rocky.agregarSimilar(rambo);

        // Cargar en la grilla del decodificador
        decodificador.agregarPelicula(thor);
        decodificador.agregarPelicula(capitanAmerica);
        decodificador.agregarPelicula(ironMan);
        decodificador.agregarPelicula(dunkirk);
        decodificador.agregarPelicula(rocky);
        decodificador.agregarPelicula(rambo);

        // Registrar reproducciones
        decodificador.reproducir(thor);
        decodificador.reproducir(rocky);
    }

    @Test
    void testSugerenciaNovedad() {
        decodificador.setCriterioSugerencia(new Novedad());
        List<Pelicula> sugeridas = decodificador.sugerencia();

        assertEquals(3, sugeridas.size());
        assertEquals("Dunkirk", sugeridas.get(0).getTitulo());
        assertEquals("Capitan America", sugeridas.get(1).getTitulo());
        assertEquals("Iron man", sugeridas.get(2).getTitulo());
    }

    @Test
    void testSugerenciaSimilaridad() {
        decodificador.setCriterioSugerencia(new Similaridad());
        List<Pelicula> sugeridas = decodificador.sugerencia();

        assertEquals(3, sugeridas.size());
        assertEquals("Capitan America", sugeridas.get(0).getTitulo());
        assertEquals("Iron man", sugeridas.get(1).getTitulo());
        assertEquals("Rambo", sugeridas.get(2).getTitulo());
    }

    @Test
    void testSugerenciaPuntaje() {
        decodificador.setCriterioSugerencia(new Puntaje());
        List<Pelicula> sugeridas = decodificador.sugerencia();

        assertEquals(3, sugeridas.size());
        assertEquals("Dunkirk", sugeridas.get(0).getTitulo());
        assertEquals("Iron man", sugeridas.get(1).getTitulo());
        assertEquals("Capitan America", sugeridas.get(2).getTitulo());
    }
}
