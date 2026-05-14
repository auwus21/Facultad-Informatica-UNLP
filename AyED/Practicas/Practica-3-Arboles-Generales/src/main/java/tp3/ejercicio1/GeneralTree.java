package tp3.ejercicio1;

import java.util.*;
import java.util.List;
import java.util.Queue;

public class GeneralTree<T> {

	private T data;
	private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();

	public GeneralTree() {

	}

	public GeneralTree(T data) {
		this.data = data;
	}

	public GeneralTree(T data, List<GeneralTree<T>> children) {
		this(data);
		this.children = children;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<GeneralTree<T>> getChildren() {
		return this.children;
	}

	public void setChildren(List<GeneralTree<T>> children) {
		if (children != null)
			this.children = children;
	}

	public void addChild(GeneralTree<T> child) {
		this.getChildren().add(child);
	}

	public boolean isLeaf() {
		return !this.hasChildren();
	}

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	public boolean isEmpty() {
		return this.data == null && !this.hasChildren();
	}

	public void removeChild(GeneralTree<T> child) {
		if (this.hasChildren())
			children.remove(child);
	}

	public int altura() {

		if (this.isEmpty()) {
			return -1;
		}
		if (this.isLeaf()) {
			return 0;
		}

		int max = -1;

		if (this.hasChildren()) {

			for (GeneralTree<T> hijo : this.getChildren()) {
				int alturaHijo = hijo.altura();

				if (alturaHijo > max) {
					max = alturaHijo;
				}
			}

		}

		return max + 1;
	}

	public int nivel(T dato) {
		if (this.getData().equals(dato)) {
			return 0;
		}
		if (this.hasChildren()) {
			for (GeneralTree<T> hijo : this.getChildren()) {
				int nivelHijo = hijo.nivel(dato);
				if (nivelHijo >= 0) {
					nivelHijo += 1;
					return nivelHijo;
				}

			}
			return -1;
		}
		return -1;
	}

	public int ancho() {
		Queue<GeneralTree<T>> cola = new LinkedList<>();
		int maxAncho = 0;

		cola.add(this);

		while (!cola.isEmpty()) {
			int nodosEnNivel = cola.size();

			if (nodosEnNivel > maxAncho) {
				maxAncho = nodosEnNivel;
			}

			for (int i = 0; i < nodosEnNivel; i++) {
				GeneralTree<T> nodoActual = cola.remove();
				if (nodoActual.hasChildren()) {
					for (GeneralTree<T> hijo : nodoActual.getChildren()) {
						cola.add(hijo);
					}

				}

			}

		}

		return maxAncho;
	}

	public boolean esAncestro(T a, T b) {
		GeneralTree<T> nodoA = this.buscarNodo(a);
		if(nodoA == null) {
			return false;
		}
		GeneralTree<T> nodoB = nodoA.buscarNodo(b);
		if(nodoB != null) {
			return true;
		}

		return false;

	}

	private GeneralTree<T> buscarNodo(T datoBuscado) {
		// 1. ¿Soy yo? (Usar .equals)
		if (this.getData().equals(datoBuscado)) {
			return this;
		}
		// 2. Si no soy, for por mis hijos
		if (this.hasChildren()) {
			for (GeneralTree<T> hijo : this.getChildren()) {
				GeneralTree<T> datoEncontrado = hijo.buscarNodo(datoBuscado);
				if (datoEncontrado != null) {
					return datoEncontrado;
				}
			}

		}

		return null;
	}

}