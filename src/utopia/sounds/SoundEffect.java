package utopia.sounds;

import doctrina.Sound;

public enum SoundEffect {

    CLICK("audio/sound/menu/click.wav"),
    INTERACTION("audio/sound/interaction.wav"),
    MELEE_SWORD("audio/sound/sword_effect.wav"),
    BROKEN_CRATE("audio/sound/wooden_crate.wav"),
    MONSTER_ATTACK("audio/sound/monster_attack.wav");

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
