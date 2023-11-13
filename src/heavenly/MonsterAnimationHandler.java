package heavenly;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.Ia;

public class MonsterAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH = "images/characters/monsters.png";

    public MonsterAnimationHandler(Ia ia) {
        setAnimationSpeed(10);
        setResetAnimation(new Animation(96, 0,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setDownAnimation(new Animation(96, 0,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setLeftAnimation(new Animation(96, 32,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setRightAnimation(new Animation(96, 64,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
        setUpAnimation(new Animation(96, 96,
                ia.getWidth(), ia.getHeight(), 3, SPRITE_PATH));
    }
}
