package ar.edu.unlp.info.oo2.mediaplayer;

public class Video implements Media {
    @Override
    public void play() {
        System.out.println("Reproduciendo archivo de video...");
    }
}
