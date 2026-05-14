package tp2.ejercicio5;

import tp2.ejercicio1.BinaryTree;

public class ProfundidadDeArbolBinario {

	private BinaryTree<Integer> arbol;

	public ProfundidadDeArbolBinario(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}

	public int sumaElementosProfundidad(int p) {
		if (!arbol.isEmpty()) {
			return sumarNodos(arbol, p, 0);
		}
		return 0;
	}

	private int sumarNodos(BinaryTree<Integer> nodo, int p, int nivelActual) {
		int suma = 0;
		if (p == nivelActual) {
			return nodo.getData();
		}
		else if (nivelActual<p) {
			if(nodo.hasLeftChild()) {
				suma += sumarNodos(nodo.getLeftChild(),p,nivelActual+1);
			}
			if(nodo.hasRightChild()) {
				suma+= sumarNodos(nodo.getRightChild(),p,nivelActual+1);
			}
			
		}
	
		return suma;
	}

}
