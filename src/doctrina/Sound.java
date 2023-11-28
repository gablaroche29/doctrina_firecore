package doctrina;

import javax.sound.sampled.*;
import java.util.Objects;

public class Sound {

    private Clip clip;
    private final String path;

    public Sound(String path) {
        this.path = path;
        SoundLineListener lineListener = new SoundLineListener(this);
        try {
            clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(path)));
            clip.open(stream);
            clip.addLineListener(lineListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop(int count) {
        clip.loop(count);
    }

    public void start() {
        clip.start();
    }

    public void reset() {
        clip.setMicrosecondPosition(0);
    }

    public void stop() {
        clip.stop();
    }

    private record SoundLineListener(Sound sound) implements LineListener {
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.START) {
                System.out.println("Le son (" + sound.path + ") a commencé.");
            } else if (event.getType() == LineEvent.Type.STOP) {
                System.out.println("Le son (" + sound.path + ") a arrêté.");
                sound.reset();
            }
        }
    }
}
