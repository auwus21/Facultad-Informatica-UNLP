package ar.edu.unlp.info.oo2.decodificador;

import java.util.List;
import java.util.stream.Collectors;

public class Novedad implements CriterioSugerencia {
	
	@Override
	public List<Pelicula> obtenerSugerencias(Decodificador decodificador) {
        return decodificador.getGrilla().stream()
            .filter(p -> !decodificador.getReproducidas().contains(p))
            .sorted((p1, p2) -> Integer.compare(p2.getAnioEstreno(), p1.getAnioEstreno()))
            .limit(3)
            .collect(Collectors.toList());
    }


}
