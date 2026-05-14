package tp1.ejercicio7;

import java.util.Objects;

public class Estudiante {
	private int legajo;
	private String nombre;
	
	
	
	public Estudiante(int legajo,String nombre) {
		this.legajo = legajo;
		this.nombre = nombre;
	}



	public int getLegajo() {
		return legajo;
	}



	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return "Estudiante [legajo=" + legajo + ", nombre=" + nombre + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(legajo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return legajo == other.legajo;
	}
	
	
	
}
