package tp2.ejercicio4;

import tp2.ejercicio1.BinaryTree;

public class RedBinariaLlena {

	private BinaryTree<Integer> arbol;

	public RedBinariaLlena(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}

	public int retardoReenvio() {

		if (!arbol.isEmpty()) {
			return this.calcularRetardoMaximo(arbol);
		}
		return 0;
	}

	private int calcularRetardoMaximo(BinaryTree<Integer> nodo) {

		if (nodo.isLeaf()) {
			return nodo.getData();
		} else {
			int retardoIzquierdo = calcularRetardoMaximo(nodo.getLeftChild());
			int retardoDerecho = calcularRetardoMaximo(nodo.getRightChild());

			return nodo.getData() + Math.max(retardoIzquierdo, retardoDerecho);

		}

	}
}
