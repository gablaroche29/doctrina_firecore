package heavenly.sounds;

import doctrina.GameConfig;
import doctrina.Sound;

public enum Music {
    BG_GAME("audio/music/game/bg_ambiance.wav"),
    RAIN_AMBIANCE("audio/music/game/rain_ambiance.wav"),
    BG_MENU("audio/music/menu/menu.wav");

    private final Sound sound;

    Music(String path) {
        sound = new Sound(path);
    }

    public void play(int count) {
        sound.setVolume(GameConfig.getMusicVolume());
        sound.start();
        sound.loop(count);
    }

    public void stop() {
        sound.stop();
    }
}
