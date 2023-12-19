package utopia.player.spell;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.MovableEntity;
import utopia.spell.Spell;

public class IceSpell extends Spell {

    public IceSpell() {
        super(2.7f);
        animationHandler = new IceSpellAnimationHandler(this);
    }

    public static class IceSpellAnimationHandler extends AnimationHandler {

        private static final String ICE_SPELL_X = "image/items/ice_spell_x.png";
        private static final String ICE_SPELL_Y = "image/items/ice_spell_y.png";

        public IceSpellAnimationHandler(MovableEntity entity) {
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
