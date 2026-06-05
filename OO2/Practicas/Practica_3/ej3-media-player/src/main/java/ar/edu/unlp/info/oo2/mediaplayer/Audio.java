package ar.edu.unlp.info.oo2.mediaplayer;

public class Audio implements Media {
    @Override
    public void play() {
        System.out.println("Reproduciendo archivo de audio...");
    }
}
