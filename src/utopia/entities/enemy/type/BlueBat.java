package utopia.entities.enemy.type;

import doctrina.Animation;
import doctrina.AnimationHandler;
import utopia.entities.enemy.Ai;

public class BlueBat extends Ai {

    public BlueBat(int x, int y) {
        super(x, y, 1.8f);
        animationHandler = new RedBatAnimationHandler(this);
    }

    public static class RedBatAnimationHandler extends AnimationHandler {

        private static final String SPRITE_PATH = "image/characters/monsters.png";

        public RedBatAnimationHandler(Ai ai) {
            super(ai);
            setAnimationSpeed(10);
            setResetAnimation(new Animation(96, 128,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setDownMovementAnimation(new Animation(96, 128,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setLeftMovementAnimation(new Animation(96, 160,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setRightMovementAnimation(new Animation(96, 192,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
            setUpMovementAnimation(new Animation(96, 224,
                    ai.getWidth(), ai.getHeight(), 3, SPRITE_PATH));
        }
    }
}
