package utopia.audio;

import doctrina.GameConfig;
import doctrina.Sound;

public enum Music {
    BG_MENU("audio/music/game/bg_ambiance.wav"),
    RAIN_AMBIANCE("audio/music/game/rain_ambiance.wav"),
    BOSS_BATTLE("audio/music/game/boss_battle.wav"),
    WIND("audio/music/game/wind.wav"),
    BG_GAME("audio/music/menu/menu.wav");

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
