package utopia.sounds;

import doctrina.Sound;

public enum SoundEffect {

    CLICK("audio/sound/menu/click.wav"),
    MELEE_SWORD("audio/sound/sword_effect.wav"),
    MONSTER_ATTACK("audio/sound/monster_attack.wav");
//    FOOTSTEPS("audio/sound/footsteps_grass/footsteps_grass.wav");

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

    public void loop(int count) {
        sound.loop(count);
    }

    public void stop() {
        sound.stop();
    }
}
