package tp3.ejercicio7;

import java.util.LinkedList;
import java.util.List;

import tp3.ejercicio1.GeneralTree;

public class Caminos {

	private GeneralTree<Integer> arbol;

	public Caminos(GeneralTree<Integer> arbol) {
		this.arbol = arbol;
	}

	public List<Integer> caminoAHojaMasLejana() {

		if (!this.arbol.isEmpty()) {
			List<Integer> caminoActual = new LinkedList<>();
			List<Integer> caminoMaximo = new LinkedList<>();

			recorrer(arbol, caminoActual, caminoMaximo);
			return caminoMaximo;
		}

		return null;
	}

	private void recorrer(GeneralTree<Integer> nodo, List<Integer> caminoActual, List<Integer> caminoMaximo) {

		caminoActual.add(nodo.getData());

		if (nodo.isLeaf()) {
			if (caminoActual.size() > caminoMaximo.size()) {
				caminoMaximo.clear();
				caminoMaximo.addAll(caminoActual);
			}

		}

		if (nodo.hasChildren()) {
			for (GeneralTree<Integer> hijo : nodo.getChildren()) {
				recorrer(hijo, caminoActual, caminoMaximo);
			}

		}
		caminoActual.remove(caminoActual.size() - 1);

	}

}
