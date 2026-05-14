package tp1.ejercicio7;

import java.util.ArrayList;

public class TestCapicua {

    public boolean esCapicua(ArrayList<Integer> lista) {
        int tamaño = lista.size();
        for (int i = 0; i < tamaño / 2; i++) {
            Integer elementoIzq = lista.get(i);
            Integer elementoDer = lista.get(tamaño - 1 - i);
            
            if (!elementoIzq.equals(elementoDer)) {
                return false; // Alarma! No es capicúa
            }
        }
        return true; // Terminó de revisar y son todos espejados
    }

    public static void main(String[] args) {
        // Creamos la instancia de nuestra clase para poder usar el método
        TestCapicua testeador = new TestCapicua();
        
        // --- PRUEBA 1: Capicúa verdadera ---
        ArrayList<Integer> lista1 = new ArrayList<>();
        lista1.add(2);
        lista1.add(5);
        lista1.add(2);
        
        System.out.println("La lista 1 [2,5,2] ¿es capicúa? " + testeador.esCapicua(lista1));

        // --- PRUEBA 2: Capicúa falsa ---
        ArrayList<Integer> lista2 = new ArrayList<>();
        lista2.add(4);
        lista2.add(5);
        lista2.add(6);
        lista2.add(3);
        lista2.add(4);
        
        System.out.println("La lista 2 [4,5,6,3,4] ¿es capicúa? " + testeador.esCapicua(lista2));
    }
}
