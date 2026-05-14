package tp1.ejercicio9;

import java.util.Stack;

public class TestBalanceo {

    public static boolean isBalanced(String s) {
        // Creamos nuestra Pila (Stack) que va a guardar caracteres
        Stack<Character> pila = new Stack<>();

        // Recorremos el String letra por letra
        for (int i = 0; i < s.length(); i++) {
            char letra = s.charAt(i);

            // 1. Si es un símbolo de APERTURA, lo metemos en la pila (push)
            if (letra == '(' || letra == '[' || letra == '{') {
                pila.push(letra);
            } 
            // 2. Si es un símbolo de CIERRE, analizamos
            else if (letra == ')' || letra == ']' || letra == '}') {
                
                // Si la pila está vacía y llega un cierre, está desbalanceado
                if (pila.isEmpty()) {
                    return false;
                }

                // Sacamos el último elemento que entró a la pila (pop)
                char tope = pila.pop();

                // 3. Verificamos que el cierre haga "pareja" con la apertura que sacamos
                if (letra == ')' && tope != '(') return false;
                if (letra == ']' && tope != '[') return false;
                if (letra == '}' && tope != '{') return false;
            }
        }

        // 4. Si terminamos de leer todo y la pila quedó vacía, 
        // significa que todo se cerró correctamente.
        return pila.isEmpty();
    }

    public static void main(String[] args) {
        // Pruebas del enunciado
        String caso1 = "{( ) [ ( ) ] }";
        String caso2 = "( [ ) ]";
        
        System.out.println("¿'" + caso1 + "' está balanceado? " + isBalanced(caso1)); // Esperado: true
        System.out.println("¿'" + caso2 + "' está balanceado? " + isBalanced(caso2)); // Esperado: false
    }
}
