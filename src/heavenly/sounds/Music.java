package heavenly.sounds;

import doctrina.Sound;

public enum Music {
    BG_AMBIENT("audio/music/TheLoomingBattle-bg.wav"),
    BG_MENU("audio/music/menu/bg.wav");

    private final Sound sound;

    Music(String path) {
        sound = new Sound(path);
    }

    public void play(int count) {
        sound.start();
        sound.loop(count);
    }

    public void stop() {
        sound.stop();
    }
}
