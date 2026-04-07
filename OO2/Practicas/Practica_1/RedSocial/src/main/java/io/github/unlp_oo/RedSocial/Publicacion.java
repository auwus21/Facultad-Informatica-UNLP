package io.github.unlp_oo.RedSocial;

import java.time.LocalDate;

public abstract class Publicacion {
	private LocalDate FechaCracion;

	public Publicacion() {
		super();
		FechaCracion = LocalDate.now();
	}

	public LocalDate getFechaCracion() {
		return FechaCracion;
	}
	

	
	
	

}
