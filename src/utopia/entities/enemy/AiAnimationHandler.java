package utopia.entities.enemy;

import doctrina.Animation;
import doctrina.AnimationHandler;

public class AiAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH = "image/characters/monsters.png";

    public AiAnimationHandler(Ai ai) {
        super(ai);
        setAnimationSpeed(10);
        setResetAnimation(new Animation(96, 0,
                ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        setDownMovementAnimation(new Animation(96, 0,
                ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        setLeftMovementAnimation(new Animation(96, 32,
                ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        setRightMovementAnimation(new Animation(96, 64,
                ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        setUpMovementAnimation(new Animation(96, 96,
                ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
    }
}
