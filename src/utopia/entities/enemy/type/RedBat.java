package utopia.entities.enemy.type;

import doctrina.Animation;
import doctrina.AnimationHandler;
import utopia.entities.enemy.ai.Ai;

public class RedBat extends Ai {

    public RedBat(int x, int y) {
        super(x, y, 1.5f);
        animationHandler = new BlueBatAnimationHandler(this);
    }

    public static class BlueBatAnimationHandler extends AnimationHandler {

        private static final String SPRITE_PATH = "image/characters/monsters.png";

        public BlueBatAnimationHandler(Ai ai) {
            super(ai);
            setAnimationSpeed(10);
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
}
