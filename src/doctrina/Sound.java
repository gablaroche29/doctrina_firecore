package doctrina;

import javax.sound.sampled.*;
import java.util.Objects;

public class Sound {

    private Clip clip;
    private static String path;
    private static boolean playing;

    public Sound(String path) {
        this.path = path;
        SoundLineListener lineListener = new SoundLineListener();
        try {
            clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(path)));
            clip.open(stream);

            clip.addLineListener(lineListener);
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

    public void reset() {
        clip.setMicrosecondPosition(0);
    }

    public void stop() {
        clip.stop();
    }

    public boolean isPlaying() {
        return playing;
    }

    private static class SoundLineListener implements LineListener {
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.START) {
                System.out.println("Le son (" + path + ") a commencé.");
                playing = true;
            } else if (event.getType() == LineEvent.Type.STOP) {
                System.out.println("Le son (" + path + ") a été arrêté.");
                playing = false;
            }
        }
    }
}
