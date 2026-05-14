package tp1.ejercicio6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ejer6 {

    public static void main(String[] args) {
        
        /* 
         * a. ¿En qué casos ArrayList ofrece un mejor rendimiento que LinkedList?
         * 
         * RESPUESTA: 
         * 1. En acceso aleatorio (acceder por índice con get()). En ArrayList es O(1) 
         *    porque es un arreglo contiguo en memoria. En LinkedList es O(N).
         * 2. Iterar elementos secuencialmente es más veloz por la "localidad de caché".
         */
        System.out.println("--- Inciso A: Ventaja de ArrayList ---");
        List<Integer> arrayListA = new ArrayList<>();
        List<Integer> linkedListA = new LinkedList<>();
        
        // Llenamos las listas con 100.000 elementos
        for (int i = 0; i < 100000; i++) {
            arrayListA.add(i);
            linkedListA.add(i);
        }
        
        // EJEMPLO: Acceder a la posición 80.000
        // ArrayList: va directo a la memoria. (Rapidísimo)
        int dato1 = arrayListA.get(80000); 
        
        // LinkedList: empieza desde el nodo 0 y salta 80.000 veces hasta llegar. (Lento)
        int dato2 = linkedListA.get(80000); 
        System.out.println("ArrayList accedió directo a: " + dato1);


        /* 
         * b. ¿Cuándo LinkedList puede ser más eficiente que ArrayList?
         * 
         * RESPUESTA: 
         * 1. Al realizar inserciones o borrados frecuentes al principio o en el medio.
         *    LinkedList solo cambia dos punteros O(1). ArrayList tiene que empujar o 
         *    desplazar todos los elementos restantes una posición O(N).
         */
        System.out.println("\n--- Inciso B: Ventaja de LinkedList ---");
        
        // EJEMPLO: Agregar un elemento en la posición 0
        // LinkedList: Simplemente crea un nodo y lo enlaza al principio.
        linkedListA.add(0, 9999);
        
        // ArrayList: Tiene que empujar los 100.000 elementos un lugar a la derecha 
        // para hacer espacio en el índice 0.
        arrayListA.add(0, 9999);
        System.out.println("LinkedList agregó rápido al principio.");


        /* 
         * c. ¿Qué diferencia encuentra en el uso de la memoria en ArrayList y LinkedList?
         * 
         * RESPUESTA:
         * - ArrayList: Usa menos memoria por elemento (solo guarda la referencia). 
         *   Sin embargo, "desperdicia" espacio. Si el arreglo interno crece a 150 
         *   pero solo usás 100, hay 50 espacios vacíos en memoria.
         * 
         * - LinkedList: No desperdicia espacio nunca (100 elementos = 100 nodos). 
         *   Pero cada elemento es más "pesado" porque además de guardar el dato, 
         *   guarda 2 punteros (al nodo siguiente y al nodo anterior).
         */
        System.out.println("\n--- Inciso C: Uso de memoria ---");
        
        // EJEMPLO: 
        ArrayList<String> arrayC = new ArrayList<>(100); 
        // Acá ArrayList ya reservó memoria para 100 elementos, aunque le agreguemos 1.
        arrayC.add("Hola");
        
        LinkedList<String> linkedC = new LinkedList<>();
        // Acá LinkedList solo reservó memoria para 1 Nodo exacto.
        linkedC.add("Hola");
        System.out.println("ArrayList puede tener capacidad ociosa, LinkedList no.");


        /* 
         * d. ¿En qué casos sería preferible usar un ArrayList o un LinkedList?
         * 
         * RESPUESTA:
         * - ArrayList: Es la opción por DEFECTO (95% de los casos). Úsalo cuando tu 
         *   programa se la pasa leyendo datos por índice o agregando al final.
         * 
         * - LinkedList: Úsalo SOLO cuando tengas listas inmensas y el objetivo principal 
         *   de tu código sea insertar y borrar constantemente elementos en el medio 
         *   o en las puntas (ej: implementando una Pila o una Cola FIFO).
         */
        System.out.println("\n--- Inciso D: Cuál elegir ---");
        System.out.println("Por defecto usamos ArrayList, salvo casos muy específicos de inserción/borrado.");
    }
}
