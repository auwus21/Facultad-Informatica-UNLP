package tp3.ejercicio4;

import java.util.*;

import tp3.ejercicio1.GeneralTree;

public class AnalizadorArbol {

	public double devolverMaximoPromedio(GeneralTree<AreaEmpresa> arbol) {
			
		if(arbol.isEmpty()) {
			return -1;
		}
		
		if(arbol.isLeaf()) {
			return arbol.getData().getTardanza();
		}
		
		double maximoPromedio = 0;
		
		Queue<GeneralTree<AreaEmpresa>> cola = new LinkedList<>();	
		cola.add(arbol);
		
		while(!cola.isEmpty()) {
			
			int nodosEnNivel = cola.size();
			double sumaNivel= 0;
			
		
			for(int i = 0;i<nodosEnNivel;i++) {
				GeneralTree<AreaEmpresa> nodoActual = cola.remove();
				
		        sumaNivel += nodoActual.getData().getTardanza();

				if (nodoActual.hasChildren()) {
					
					for(GeneralTree<AreaEmpresa> hijo : nodoActual.getChildren()) {
						cola.add(hijo); 

					}

				}
			}
			if(sumaNivel/nodosEnNivel >maximoPromedio) {
				maximoPromedio = sumaNivel / nodosEnNivel;
			}
			
		}
		return maximoPromedio;
	}

}
