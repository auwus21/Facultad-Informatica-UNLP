package io.github.unlp_oo.RedSocial;

public class Tweet extends Publicacion {
	public String text;

	
	
	
	public Tweet(String text) {
		super();
		if (text.length() < 1 || text.length() > 280) {
			System.out.println("El Tweet tiene mas de 280 caracteres");
		}
		this.text = text;
	}


	public String getText() {
		return text;
	}



	
	
	
	
	

}
