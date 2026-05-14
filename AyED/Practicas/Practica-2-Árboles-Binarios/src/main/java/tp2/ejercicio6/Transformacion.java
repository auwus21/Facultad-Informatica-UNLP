package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

public class Transformacion {

	private BinaryTree<Integer> arbol;

	public Transformacion(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}

	public BinaryTree<Integer> suma() {
		if (!arbol.isEmpty()) {
			transformar(arbol);
		}
		return arbol;
	}

	private int transformar(BinaryTree<Integer> nodo) {
		int valorOriginal = nodo.getData();
		int sumaDerecha = 0;
		int sumaIzquierda = 0;

		if (nodo.hasLeftChild()) {
			sumaIzquierda = transformar(nodo.getLeftChild());
		}

		if (nodo.hasRightChild()) {
			sumaDerecha = transformar(nodo.getRightChild());
		}
		nodo.setData(sumaIzquierda + sumaDerecha);

		return valorOriginal + sumaIzquierda + sumaDerecha;
	}

}
