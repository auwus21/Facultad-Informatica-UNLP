package ar.edu.unlp.info.oo2.mediaplayer;

import java.util.ArrayList;
import java.util.List;

public class MediaPlayer {
    private List<Media> mediaList;

    public MediaPlayer() {
        this.mediaList = new ArrayList<>();
    }

    public void agregarMedia(Media media) {
        this.mediaList.add(media);
    }

    public void playAll() {
        for (Media media : mediaList) {
            media.play();
        }
    }

    public List<Media> getMediaList() {
        return mediaList;
    }
}
