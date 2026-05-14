package tp2.ejercicio3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tp2.ejercicio1.BinaryTree;

public class ContadorArbol {

	private BinaryTree<Integer> arbol;

	public ContadorArbol(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}

	// a) Recorrido InOrden
	public List<Integer> numerosParesInOrden() {
		List<Integer> pares = new LinkedList<Integer>();
		if (!arbol.isEmpty()) {
			this.contarParesInOrden(arbol, pares);
		}

		return pares;
	}

	private void contarParesInOrden(BinaryTree<Integer> nodo, List<Integer> pares) {
		if (nodo.hasLeftChild()) {
			contarParesInOrden(nodo.getLeftChild(), pares);
		}
		if (nodo.getData() % 2 == 0) {
			pares.add(nodo.getData());
		}
		if (nodo.hasRightChild()) {
			contarParesInOrden(nodo.getRightChild(), pares);
		}
	}

	// b) Recorrido PostOrden
	public List<Integer> numerosParesPostOrden() {
		List<Integer> pares = new LinkedList<Integer>();
		if (!arbol.isEmpty()) {
			this.contarParesPostOrden(arbol, pares);
		}
		return pares;
	}

	private void contarParesPostOrden(BinaryTree<Integer> nodo, List<Integer> pares) {
		if(nodo.hasLeftChild()) {
			contarParesPostOrden(nodo.getLeftChild(),pares);
		}
		if(nodo.hasRightChild()) {
			contarParesPostOrden(nodo.getRightChild(),pares);
		}
		if(nodo.getData() % 2 == 0) {
			pares.add(nodo.getData());
		}
	}

}
	