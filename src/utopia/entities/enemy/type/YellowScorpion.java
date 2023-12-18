package utopia.entities.enemy.type;

import doctrina.Animation;
import doctrina.AnimationHandler;
import utopia.entities.enemy.Ai;

public class YellowScorpion extends Ai {

    public YellowScorpion(int x, int y) {
        super(x, y, 1.0f);
        animationHandler = new YellowScorpionAnimationHandler(this);
    }

    public static class YellowScorpionAnimationHandler extends AnimationHandler {

        private static final String SPRITE_PATH = "image/characters/monsters.png";

        public YellowScorpionAnimationHandler(Ai ai) {
            super(ai);
            setAnimationSpeed(10);
            setDownIdleAnimation(new Animation(288, 128,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setDownMovementAnimation(new Animation(288, 128,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setLeftIdleAnimation(new Animation(288, 160,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setLeftMovementAnimation(new Animation(288, 160,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setRightIdleAnimation(new Animation(288, 192,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setRightMovementAnimation(new Animation(288, 192,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setUpIdleAnimation(new Animation(288, 224,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setUpMovementAnimation(new Animation(288, 224,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        }
    }
}
