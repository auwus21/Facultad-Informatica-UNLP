package tp3.ejercicio6;

import tp3.ejercicio1.GeneralTree;

public class RedDeAguaPotable {

	private GeneralTree<Character> arbol;

	public RedDeAguaPotable(GeneralTree<Character> arbol) {
		this.arbol = arbol;
	}

	public double minimoCaudal(double caudal) {
		if (!arbol.isEmpty()) {
			double minimo = calcularMinimo(arbol, caudal);
			return minimo;
		}
		return 0;
	}

	private double calcularMinimo(GeneralTree<Character> nodo, double caudalActual) {
		if (nodo.isLeaf()) {
			return caudalActual;
		}
		double minimo = Double.MAX_VALUE;

		double caudalParaHijos = caudalActual / nodo.getChildren().size();
		if (nodo.hasChildren()) {
			for (GeneralTree<Character> hijo : nodo.getChildren()) {
				double minDelHijo = calcularMinimo(hijo, caudalParaHijos);

				if (minimo > minDelHijo) {
					minimo = minDelHijo;
				}

			}
		}

		return minimo;
	}

}
