package tp2.ejercicio7;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {

	private BinaryTree<Integer> arbol;

	public ParcialArboles(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}

	public boolean isLeftTree(int num) {

		if (this.arbol.isEmpty()) {
			return false;
		}
		BinaryTree<Integer> arbolBuscado = null;
		int izq = -1;
		int der = -1;

		arbolBuscado = buscarNodo(this.arbol, num);

		if (arbolBuscado == null) {
			return false;
		}

		if (arbolBuscado.hasLeftChild()) {
			izq = contarUnicosHijos(arbolBuscado.getLeftChild());
		}
		if (arbolBuscado.hasRightChild()) {
			der = contarUnicosHijos(arbolBuscado.getRightChild());
		}
		if (izq > der) {
			return true;
		} else {
			return false;
		}
	}

	public BinaryTree<Integer> buscarNodo(BinaryTree<Integer> nodo, int num) {

		BinaryTree<Integer> resultado = null;

		if (nodo.getData() == num) {
			return nodo;
		}
		if (nodo.hasLeftChild()) {
			resultado = buscarNodo(nodo.getLeftChild(), num);
		}
		if (resultado != null) {
			return resultado;
		}
		if (nodo.hasRightChild()) {
			resultado = buscarNodo(nodo.getRightChild(), num);
		}
		return resultado;
	}

	private int contarUnicosHijos(BinaryTree<Integer> nodo) {

		int cantidad = 0;

		if (nodo.hasLeftChild() ^ nodo.hasRightChild()) {
			cantidad = 1;
		}

		if (nodo.hasLeftChild()) {
			cantidad += contarUnicosHijos(nodo.getLeftChild());
		}

		if (nodo.hasRightChild()) {
			cantidad += contarUnicosHijos(nodo.getRightChild());
		}

		return cantidad;
	}
}
