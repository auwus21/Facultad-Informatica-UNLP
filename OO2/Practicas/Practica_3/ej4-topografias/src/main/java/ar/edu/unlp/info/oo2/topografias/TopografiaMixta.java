package ar.edu.unlp.info.oo2.topografias;

import java.util.*;

public class TopografiaMixta extends Topografia{
	
	private List<Topografia> lista = new LinkedList<>();
	
	
	public TopografiaMixta(Topografia t1,Topografia t2,Topografia t3,Topografia t4) {
		this.lista.add(t1);
		this.lista.add(t2);
		this.lista.add(t3);
		this.lista.add(t4);
	}

	public double getProporcionAgua() {
		double total =0;
		for (Topografia l : lista) {
			total+= l.getProporcionAgua();
		}
		return total / 4;
	}
	
	public List<Topografia> getComponents(){
		return this.lista;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TopografiaMixta)) {
			return false;
		}
		TopografiaMixta otra = (TopografiaMixta) obj;
		return this.lista.equals(otra.getComponents());
	}
}
