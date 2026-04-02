package io.github.unlp_oo.RedSocial;

import java.util.*;

public class Sistema {
	private List<Usuario> usuarios;
	
	
	public Sistema() {
		this.usuarios = new ArrayList<Usuario>();
		
	}
	
	public void CrearUsuario(String ScreenName){
		for(Usuario user : usuarios) {
			if(user.getScreenName().equals(ScreenName)) {
				System.out.println("El Nombre De Usuario Ya Esta Usado");
				break;
			}
		}
		Usuario usuario = new Usuario(ScreenName);
		usuarios.add(usuario);
	}
	

}
