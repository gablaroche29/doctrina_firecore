package utopia.entities.enemy.type.necromancer;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.MovableEntity;
import utopia.spell.Spell;

public class FireSpell extends Spell {

    public FireSpell() {
        super(2.7f);
        maxDistance = 200;
        animationHandler = new FireSpellAnimationHandler(this);
    }

    public static class FireSpellAnimationHandler extends AnimationHandler {

        private static final String ICE_SPELL_X = "image/items/fire_spell_x.png";
        private static final String ICE_SPELL_Y = "image/items/fire_spell_y.png";

        public FireSpellAnimationHandler(MovableEntity entity) {
            super(entity);
            setAnimationSpeed(20);
            setDownMovementAnimation(new Animation(54, 0,
                    9, 54, 6, ICE_SPELL_Y));
            setLeftMovementAnimation(new Animation(0, 0,
                    54, 9, 6, ICE_SPELL_X));
            setRightMovementAnimation(new Animation(0, 9,
                    54, 9, 6, ICE_SPELL_X));
            setUpMovementAnimation(new Animation(0, 0,
                    9, 54, 6, ICE_SPELL_Y));
        }
    }
}
