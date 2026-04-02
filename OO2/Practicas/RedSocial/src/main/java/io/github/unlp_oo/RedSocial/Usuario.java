package io.github.unlp_oo.RedSocial;

import java.util.*;

public class Usuario {
	private String screenName;
	private List<Publicacion> publicaciones ;
	
	
	public Usuario(String screenName) {
		this.screenName = screenName;
		this.publicaciones = new ArrayList<Publicacion>();
		
			
	}

	
	public void eliminarTodosLosTweets() {
		
		
	}

	public String getScreenName() {
		return screenName;
	}
	
	
	
	

}
