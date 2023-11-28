package heavenly;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.Ia;

public class MonsterAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH = "image/characters/monsters.png";

    public MonsterAnimationHandler(Ia ia) {
        super(ia);
        setAnimationSpeed(10);
        setResetAnimation(new Animation(96, 0,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setDownMovementAnimation(new Animation(96, 0,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setLeftMovementAnimation(new Animation(96, 32,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setRightMovementAnimation(new Animation(96, 64,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setUpMovementAnimation(new Animation(96, 96,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
    }
}
