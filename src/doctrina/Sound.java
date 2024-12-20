package doctrina;

import javax.sound.sampled.*;
import java.sql.PreparedStatement;
import java.util.Objects;

public class Sound {

    private Clip clip;

    public Sound(String path) {
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

    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
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
            if (event.getType() == LineEvent.Type.STOP) {
                sound.reset();
            }
        }
    }
}
