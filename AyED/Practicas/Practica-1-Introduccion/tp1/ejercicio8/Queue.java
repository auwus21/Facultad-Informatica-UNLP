package tp1.ejercicio8;

import java.util.*;

public class Queue<T> {
	
	protected List<T> data;

	public Queue() {
		this.data = new LinkedList<>();
	}
	
	
	public void enqueue(T dato) {
		data.add(dato);
	}
	
	public T dequeue() {
		return data.remove(0);
	}
	
	public T head() {
		return data.get(0);
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	public int size() {
		return data.size();
	}
	
	public String toString() {
		return data.toString();
	}
}
