package ar.edu.unlp.info.oo2.mediaplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MediaPlayerTest {
    private MediaPlayer mediaPlayer;
    private Audio audio;
    private Video video;

    @BeforeEach
    void setUp() {
        mediaPlayer = new MediaPlayer();
        audio = new Audio();
        video = new Video();
    }

    @Test
    void testPlayAudioYVideo() {
        mediaPlayer.agregarMedia(audio);
        mediaPlayer.agregarMedia(video);
        assertEquals(2, mediaPlayer.getMediaList().size());
        
        // Ejecuta playAll para ver la salida en consola
        mediaPlayer.playAll();
    }

    @Test
    void testPlayVideoStreamConAdapter() {
        // 1. Crear el objeto que queremos adaptar (Adaptee)
        VideoStream stream = new VideoStream();
        
        // 2. Crear el adaptador pasándole el objeto adaptado (Adapter)
        VideoStreamAdapter adapter = new VideoStreamAdapter(stream);
        
        // 3. Agregarlo al reproductor
        mediaPlayer.agregarMedia(adapter);
        
        // Verificaciones
        assertEquals(1, mediaPlayer.getMediaList().size());
        
        // 4. Reproducir todo para verificar que delega correctamente sin fallos
        mediaPlayer.playAll();
    }
}
