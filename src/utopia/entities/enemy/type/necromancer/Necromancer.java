package utopia.entities.enemy.type.necromancer;

import doctrina.Animation;
import doctrina.AnimationHandler;
import utopia.entities.enemy.Ai;

public class Necromancer extends Ai {

    public Necromancer(int x, int y) {
        super(x, y, 1.f);
        setDimension(80, 80);
        animationHandler = new NecromancerAnimationHandler(this);
    }

    public static class NecromancerAnimationHandler extends AnimationHandler {

        private static final String SPRITE_PATH = "image/characters/Necromancer/necromancer.png";

        public NecromancerAnimationHandler(Ai ai) {
            super(ai);
            setAnimationSpeed(10);
            setDownMovementAnimation(new Animation(0, 80,
                    ai.getWidth(), ai.getHeight(), 8, SPRITE_PATH));
            setLeftMovementAnimation(new Animation(0, 480,
                    ai.getWidth(), ai.getHeight(), 8, SPRITE_PATH));
            setRightMovementAnimation(new Animation(0, 80,
                    ai.getWidth(), ai.getHeight(), 8, SPRITE_PATH));
            setUpMovementAnimation(new Animation(0, 80,
                    ai.getWidth(), ai.getHeight(), 8, SPRITE_PATH));
        }
    }
}
