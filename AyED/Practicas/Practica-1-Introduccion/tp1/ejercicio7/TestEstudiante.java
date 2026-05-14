package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class TestEstudiante {

	public static void main(String[] args) {
		Estudiante estudiante = new Estudiante(1, "Pepe");
		Estudiante estudiante2 = new Estudiante(2, "Juan");
		Estudiante estudiante3 = new Estudiante(3, "Agus");
		List<Estudiante> listaOriginal = new ArrayList<>();

		listaOriginal.add(estudiante);
		listaOriginal.add(estudiante2);
		listaOriginal.add(estudiante3);

		List<Estudiante> listaCopia = new ArrayList<>(listaOriginal);

		System.out.println("Imprimiendo Lista Original");
		for (Estudiante est : listaOriginal) {
			System.out.println(est.toString());
		}
		System.out.println("Imprimiendo Lista Copia");
		for (Estudiante est2 : listaCopia) {
			System.out.println(est2.toString());
		}

		listaOriginal.get(0).setNombre("Pepe Modificado");

		System.out.println("Imprimiendo Despues de modificar ");
		System.out.println("Imprimiendo Lista Original");

		for (Estudiante est : listaOriginal) {
			System.out.println(est.toString());
		}
		System.out.println("Imprimiendo Lista Copia");
		for (Estudiante est2 : listaCopia) {
			System.out.println(est2.toString());
		}
		System.out.println("-----------Punto E--------------");
		Estudiante estudiante4 = new Estudiante(4, "Pedrito");
		if (listaOriginal.contains(estudiante4)) {
			System.out.println("El numero de legajo ya esta utilizado");
		} else {
			System.out.println("Estudiante Agregado");
			listaOriginal.add(estudiante4);
		}
	}

}
