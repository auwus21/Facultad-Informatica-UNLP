package tp1.ejercicio1;

import java.util.Scanner;

public class Ejercicio1 {
	
	
	public static void metodoA(int numeroa, int numerob) {
		System.out.println("-----Metodo A-----");
		for(int i = numeroa; i<=numerob;i++) {
			System.out.print(i+" ");
			}
		}
	
	public static void metodoB(int numeroa , int numerob) {
		System.out.println("\n-----Metodo B-----");
		while (numeroa <= numerob)  {
			System.out.print(numeroa+" ");
			numeroa++;
		}
		
	}
	
	public static void metodoC(int numeroa, int numerob) {
		if(numeroa<=numerob) {
			System.out.print(numeroa+" ");
			numeroa++;
			metodoC(numeroa,numerob);
		}
	
	}
	
	
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ingrese el Numero A: ");
		int numeroa = sc.nextInt();
		
		System.out.print("Ingrese el Numero B: ");
		int numerob = sc.nextInt();
		
		metodoA(numeroa,numerob);
		metodoB(numeroa,numerob);
		System.out.println("\n----Metodo C-----");
		metodoC(numeroa,numerob);
		sc.close();
				
		
	}
}
