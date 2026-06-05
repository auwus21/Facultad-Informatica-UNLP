package ar.edu.unlp.info.oo2.topografias;

public class Pantano extends Topografia{

	@Override
	public double getProporcionAgua() {
		return 0.7;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Pantano;
	}

}
