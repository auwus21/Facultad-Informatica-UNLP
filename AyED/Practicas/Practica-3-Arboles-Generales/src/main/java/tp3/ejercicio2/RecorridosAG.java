package tp3.ejercicio2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tp3.ejercicio1.GeneralTree;

public class RecorridosAG {

	public List<Integer> numerosImparesMayoresQuePreOrden(GeneralTree<Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<>();

		if (!a.isEmpty()) {

			recorridoPreOrden(a,n,lista);
		}

		return lista;
	}
	
	
	private void recorridoPreOrden(GeneralTree<Integer> nodo, Integer n , List<Integer> lista) {
		if((nodo.getData() % 2 != 0) && (nodo.getData() > n)) {
			lista.add(nodo.getData());
		}
		if(nodo.hasChildren()) {
			for (GeneralTree<Integer> hijo : nodo.getChildren()) {
				recorridoPreOrden(hijo,n,lista);
			}
		}
	}
	
	

	public List<Integer> numerosImparesMayoresQueInOrden(GeneralTree<Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<>();
		if(!a.isEmpty()) {
			recorridoInOrden(a,n,lista);
		}
		return lista;
	}
	
	private void recorridoInOrden(GeneralTree<Integer> nodo , Integer n ,List<Integer> lista) {
		if(nodo.hasChildren()) {
			GeneralTree<Integer> primerHijo = nodo.getChildren().get(0);
			recorridoInOrden(primerHijo,n,lista);
		}
		
		if((nodo.getData() % 2 != 0) && (nodo.getData() > n)) {
			lista.add(nodo.getData());
		}
		
		if(nodo.hasChildren()) {
	        List<GeneralTree<Integer>> hijos = nodo.getChildren();
			for (int i = 1; i<hijos.size();i++) {
				recorridoInOrden(hijos.get(i),n,lista);
			}
		}
	}
	

	public List<Integer> numerosImparesMayoresQuePostOrden(GeneralTree<Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<>();
		
		if(a.isEmpty()) {
			recorridoPostOrden(a,n,lista);
		}
		return lista;
	}
	
	
	private void recorridoPostOrden(GeneralTree<Integer> nodo , Integer n, List<Integer> lista) {
		if(nodo.hasChildren()) {
			for(GeneralTree<Integer> hijo : nodo.getChildren()) {
				recorridoPostOrden(hijo,n,lista);
			}
		}
		
		if((nodo.getData() % 2 != 0) &&(nodo.getData()>n) ) {
			lista.add(nodo.getData());
	
		}
	}

	public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree<Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<>();
		if(!a.isEmpty()) {
			Queue<GeneralTree<Integer>> cola = new LinkedList<>();
			cola.add(a);
			while(!cola.isEmpty()) {
				GeneralTree<Integer> dato  = cola.remove();
				if((dato.getData() % 2 != 0)&& dato.getData() > n) {
					lista.add(dato.getData());
				}
				if(dato.hasChildren()) {
					for(GeneralTree<Integer> hijo : dato.getChildren()) {
						cola.add(hijo);
						
					}
				}
			}
			
			
		}
		return lista;
	}

}
