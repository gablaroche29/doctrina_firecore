package doctrina;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;

    public Sound(String path) {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream(path));
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void start() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }
}
