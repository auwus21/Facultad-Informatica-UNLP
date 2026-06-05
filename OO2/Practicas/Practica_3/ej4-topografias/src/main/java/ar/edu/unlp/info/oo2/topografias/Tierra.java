package ar.edu.unlp.info.oo2.topografias;

public class Tierra extends Topografia {

	@Override
	public double getProporcionAgua() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Tierra;
	}
}
