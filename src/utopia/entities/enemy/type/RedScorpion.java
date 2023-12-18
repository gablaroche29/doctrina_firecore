package utopia.entities.enemy.type;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.State;
import utopia.entities.enemy.Ai;

public class RedScorpion extends Ai {

    public RedScorpion(int x, int y) {
        super(x, y, 1.0f);
        state = State.IDLE;
        animationHandler = new RedScorpionAnimationHandler(this);
    }

    public static class RedScorpionAnimationHandler extends AnimationHandler {

        private static final String SPRITE_PATH = "image/characters/monsters.png";

        public RedScorpionAnimationHandler(Ai ai) {
            super(ai);
            setAnimationSpeed(10);
            setDownIdleAnimation(new Animation(288, 0,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setDownMovementAnimation(new Animation(288, 0,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setLeftIdleAnimation(new Animation(288, 32,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setLeftMovementAnimation(new Animation(288, 32,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setRightIdleAnimation(new Animation(288, 64,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setRightMovementAnimation(new Animation(288, 64,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setUpIdleAnimation(new Animation(288, 96,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setUpMovementAnimation(new Animation(288, 96,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        }
    }
}
