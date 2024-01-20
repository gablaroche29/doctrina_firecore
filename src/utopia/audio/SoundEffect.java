package utopia.audio;

import doctrina.GameConfig;
import doctrina.Sound;

public enum SoundEffect {

    CLICK("audio/sound/menu/click.wav"),
    INTERACTION("audio/sound/interaction.wav"),
    MELEE_SWORD("audio/sound/sword_effect.wav"),
    BROKEN_CRATE("audio/sound/wooden_crate.wav"),
    MONSTER_HIT("audio/sound/monster/monster_hit.wav"),
    MONSTER_DEAD("audio/sound/monster/monster_dead.wav"),
    HIT_DAMAGE("audio/sound/hit_damage.wav"),
    HEAL_SPELL("audio/sound/heal_spell.wav"),
    SPAWN("audio/sound/spawn.wav"),
    ICE_BALL("audio/sound/ice_ball.wav"),
    FIRE_BALL("audio/sound/fire_ball.wav"),
    MONSTER_ATTACK("audio/sound/monster_attack.wav"),
    NECROMANCER_SCREAM("audio/sound/necromancer/scream.wav"),
    NECROMANCER_DEATH("audio/sound/necromancer/death.wav"),
    NECROMANCER_TELEPORT("audio/sound/necromancer/teleport.wav");

    private final Sound sound;

    SoundEffect(String path) {
        sound = new Sound(path);
    }

    public void play() {
        sound.setVolume(GameConfig.getSoundVolume());
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
