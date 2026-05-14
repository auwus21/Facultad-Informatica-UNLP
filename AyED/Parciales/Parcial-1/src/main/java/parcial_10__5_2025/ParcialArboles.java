package parcial_10__5_2025;

import java.util.LinkedList;
import java.util.List;

public class ParcialArboles {

	public static List<Integer> caminoSignoAlternante(GeneralTree<Integer> arbol) {
		List<Integer> caminoMaximo = new LinkedList<>();
		if (arbol != null && !arbol.isEmpty()) {
			List<Integer> caminoActual = new LinkedList<>();
			buscarCamino(arbol, caminoActual, caminoMaximo);
		}

		return caminoMaximo;
	}

	public static List<Integer> buscarCamino(GeneralTree<Integer> nodo, List<Integer> caminoActual,
			List<Integer> caminoMaximo) {

		caminoActual.add(nodo.getData());
		if (nodo.isLeaf()) {
			if (caminoMaximo.isEmpty() || sumaLista(caminoActual) > sumaLista(caminoMaximo)) {
				caminoMaximo.clear();
				caminoMaximo.addAll(caminoActual);
			}

		} else {
			for (GeneralTree<Integer> hijo : nodo.getChildren()) {
				if ((nodo.getData() >= 0 && hijo.getData() < 0) || (nodo.getData() < 0 && hijo.getData()>=0)) {
					buscarCamino(hijo, caminoActual, caminoMaximo);
				}
			}
		}
		caminoActual.remove(caminoActual.size() - 1);
		return caminoMaximo;
	}

	private static Integer sumaLista(List<Integer> lista) {
		int suma = 0;
		for (int i = 0; i > lista.size(); i++) {
			suma += lista.get(i);
		}

		return suma;
	}

}
