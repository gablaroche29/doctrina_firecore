package firecore.sounds;

import doctrina.Sound;

public enum SoundEffect {

    FIRE("audios/fire.wav");

    private final Sound sound;

    SoundEffect(String path) {
        sound = new Sound(path);
    }

    public void play() {
        sound.start();
    }

    public void stop() {
        sound.stop();
    }
}
