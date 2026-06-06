package ar.edu.unlp.info.oo2.decodificador;

import java.util.ArrayList;
import java.util.List;

public class Decodificador {
    private List<Pelicula> grilla;
    private List<Pelicula> reproducidas;
    private CriterioSugerencia sugerencia;
    
    public Decodificador() {
        this.grilla = new ArrayList<>();
        this.reproducidas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        this.grilla.add(pelicula);
    }

    public void reproducir(Pelicula pelicula) {
        if (!this.reproducidas.contains(pelicula)) {
            this.reproducidas.add(pelicula);
        }
    }

    public void setCriterioSugerencia(CriterioSugerencia sugerencia) {
    	this.sugerencia = sugerencia;
    }
    
    public List<Pelicula> sugerencia(){
    	return sugerencia.obtenerSugerencias(this);
    }
    
    public List<Pelicula> getGrilla() {
        return grilla;
    }

    public List<Pelicula> getReproducidas() {
        return reproducidas;
    }
}
