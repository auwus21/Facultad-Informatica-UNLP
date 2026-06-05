package ar.edu.unlp.info.oo2.mediaplayer;

public class VideoStreamAdapter implements Media {
    private VideoStream videoStream;

    public VideoStreamAdapter(VideoStream videoStream) {
        this.videoStream = videoStream;
    }

    @Override
    public void play() {
        this.videoStream.reproducir();
    }
}
