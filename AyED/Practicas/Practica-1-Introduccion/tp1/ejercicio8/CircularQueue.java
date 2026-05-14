package tp1.ejercicio8;


public class CircularQueue<T> extends Queue<T>{
	

	public CircularQueue() {
		super();
	}
	
	public T shift() {
		T elemento = this.dequeue();
		this.enqueue(elemento);
		
		return elemento;
	}

}
