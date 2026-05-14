package tp1.ejercicio7;

import java.util.LinkedList;
import java.util.List;

public class TestLinkedList {
	private static int[] numeros = { 10, 5, 20, 2, 8, 15 };

	public static void main(String[] args) {
		List<Integer> linkedList = new LinkedList<>();
		System.out.println("Enunciado B:");
		for (int i = 0; i < numeros.length; i++) {
			linkedList.add(numeros[i]);
		}

		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("Elemento " + i + " :" + linkedList.get(i));
		}

	}
}
