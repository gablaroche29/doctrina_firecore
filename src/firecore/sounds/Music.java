package firecore.sounds;

import doctrina.Sound;

public enum Music {

    BG_AMBIENT("music/bg/TheLoomingBattle-bg.wav", true);

    private final Sound sound;

    Music(String path, boolean loop) {
        sound = new Sound(path);
        if (loop) {
            sound.loop();
        }
    }

    public void play() {
        sound.start();
    }

    public void stop() {
        sound.stop();
    }
}
