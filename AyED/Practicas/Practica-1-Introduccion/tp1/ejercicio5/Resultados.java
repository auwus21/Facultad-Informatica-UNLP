package tp1.ejercicio5;

public class Resultados {
	private static int[] arregloGlobal;
	private static DatosArreglo datosGlobales = new DatosArreglo();

	public static DatosArreglo MetodoA(int[] arreglo) {
		DatosArreglo datos = new DatosArreglo();
		double suma = 0;

		for (int i = 0; i < arreglo.length; i++) {
			suma += arreglo[i];
			if (arreglo[i] < datos.getMin()) {
				datos.setMin(arreglo[i]);
			}
			if (arreglo[i] > datos.getMax()) {
				datos.setMax(arreglo[i]);
			}
		}

		datos.setPromedio(suma / arreglo.length);

		return datos;

	}

	public static void MetodoB(int[] arreglo, DatosArreglo datos) {
		double suma = 0;
		for (int i = 0; i < arreglo.length; i++) {
			suma += arreglo[i];
			if (arreglo[i] < datos.getMin()) {
				datos.setMin(arreglo[i]);
			}
			if (arreglo[i] > datos.getMax()) {
				datos.setMax(arreglo[i]);
			}
		}

		datos.setPromedio(suma / arreglo.length);

	}

	public static void MetodoC() {
		double suma = 0;

		for (int i = 0; i < arregloGlobal.length; i++) {
			suma += arregloGlobal[i];
			if (arregloGlobal[i] < datosGlobales.getMin()) {
				datosGlobales.setMin(arregloGlobal[i]);
			}
			if (arregloGlobal[i] > datosGlobales.getMax()) {
				datosGlobales.setMax(arregloGlobal[i]);
			}
		}

		datosGlobales.setPromedio(suma / arregloGlobal.length);

	}
	
	public static void main(String[] args) {
	    int[] numeros = { 10, 5, 20, 2, 8, 15 };

	    // --- Prueba Inciso A ---
	    DatosArreglo resultadoA = MetodoA(numeros);
	    System.out.println("A - Min: " + resultadoA.getMin() + ", Max: " + resultadoA.getMax());

	    // --- Prueba Inciso B ---
	    DatosArreglo resultadoB = new DatosArreglo();
	    MetodoB(numeros, resultadoB);
	    System.out.println("B - Min: " + resultadoB.getMin() + ", Max: " + resultadoB.getMax());

	    // --- Prueba Inciso C ---
	    arregloGlobal = numeros; // Le asignamos el arreglo a la variable global
	    MetodoC();
	    System.out.println("C - Min: " + datosGlobales.getMin() + ", Max: " + datosGlobales.getMax());
	}

	
	

}
