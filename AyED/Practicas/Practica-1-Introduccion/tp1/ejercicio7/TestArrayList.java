package tp1.ejercicio7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayList {
	private static int[] numeros = { 10, 5, 20, 2, 8, 15 };

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		System.out.println("Enunciado A:");
		for (int i = 0; i < numeros.length; i++) {
			arrayList.add(numeros[i]);
		}

		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println("Elemento " + i + " :" + arrayList.get(i));
		}

	}
}
