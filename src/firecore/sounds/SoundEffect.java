package firecore.sounds;

import doctrina.Sound;

public enum SoundEffect {

    FIRE("audios/fire.wav"),
    FOOTSTEPS("audio/sound/footsteps_grass/footsteps_grass.wav");

    private final Sound sound;

    SoundEffect(String path) {
        sound = new Sound(path);
    }

    public void play() {
        sound.start();
    }

    public void reset() {
        sound.reset();
    }

    public void loop() {
        sound.loop();
    }

    public void stop() {
        sound.stop();
    }
}
