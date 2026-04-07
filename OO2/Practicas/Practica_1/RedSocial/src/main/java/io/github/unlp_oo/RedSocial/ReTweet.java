package io.github.unlp_oo.RedSocial;

public class ReTweet extends Publicacion {
    
    private Tweet origen;

    public ReTweet(Tweet origen) {
        super(); 
        this.origen = origen;
    }

    public Tweet getOrigen() {
        return this.origen;
    }
}
2