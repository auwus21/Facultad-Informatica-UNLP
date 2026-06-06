package ar.edu.unlp.info.oo2.decodificador;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private String titulo;
    private int anioEstreno;
    private double puntaje;
    private List<Pelicula> similares;

    public Pelicula(String titulo, double puntaje, int anioEstreno) {
        this.titulo = titulo;
        this.puntaje = puntaje;
        this.anioEstreno = anioEstreno;
        this.similares = new ArrayList<>();
    }

    /**
     * Agrega una película similar. La relación es bidireccional (recíproca).
     */
    public void agregarSimilar(Pelicula pelicula) {
        if (!this.similares.contains(pelicula)) {
            this.similares.add(pelicula);
            pelicula.agregarSimilar(this); // Relación recíproca
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioEstreno() {
        return anioEstreno;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public List<Pelicula> getSimilares() {
        return similares;
    }
}
