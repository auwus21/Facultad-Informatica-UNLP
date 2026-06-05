package ar.edu.unlp.info.oo2.topografias;

public class Agua extends Topografia {

	@Override
	public double getProporcionAgua() {
		
		return 1.0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Agua;
	}

}
