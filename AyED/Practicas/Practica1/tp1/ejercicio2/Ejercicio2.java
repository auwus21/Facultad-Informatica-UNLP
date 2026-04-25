package tp1.ejercicio2;
import java.util.Scanner;
import java.util.Arrays;

public class Ejercicio2 {
	
	
	
	
	public static int[] obtenerMultiplos(int n) {
		int[] arreglo = new int [n];
		for(int i=0;i<n;i++) {
			arreglo[i] = n * (i + 1);
			
		}
		return arreglo;
		
	}
	
	
	
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);		
		System.out.print("Ingrese un Numero:  ");
		int n = sc.nextInt();
		int resultado[] = obtenerMultiplos(n);
		
		System.out.println("F("+n+")= "+Arrays.toString(resultado));
		
		sc.close();
		
	}
	
	
	

}
