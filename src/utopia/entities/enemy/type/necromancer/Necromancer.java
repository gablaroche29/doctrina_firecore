package utopia.entities.enemy.type.necromancer;

import doctrina.Animation;
import doctrina.AnimationHandler;
import utopia.entities.enemy.boss.Boss;

public class Necromancer extends Boss {

    public Necromancer(int x, int y) {
        super(x, y, 1.5f);
        setDimension(80, 80);
        setPv(40);
        animationHandler = new NecromancerAnimationHandler(this);
    }

    public static class NecromancerAnimationHandler extends AnimationHandler {

        private static final String SPRITE_PATH = "image/characters/Necromancer/necromancer.png";

        public NecromancerAnimationHandler(Boss boss) {
            super(boss);
            setAnimationSpeed(6);
            setDownMovementAnimation(new Animation(0, 80,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));
            setLeftMovementAnimation(new Animation(0, 480,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));
            setRightMovementAnimation(new Animation(0, 80,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));
            setUpMovementAnimation(new Animation(0, 80,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));

            setDownIdleAnimation(new Animation(0, 0,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));
            setLeftIdleAnimation(new Animation(0, 400,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));
            setRightIdleAnimation(new Animation(0, 0,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));
            setUpIdleAnimation(new Animation(0, 0,
                    boss.getWidth(), boss.getHeight(), 8, SPRITE_PATH));

            setDownAttackAnimation(new Animation(0, 160,
                    boss.getWidth(), boss.getHeight(), 17, SPRITE_PATH));
            setUpAttackAnimation(new Animation(0, 160,
                    boss.getWidth(), boss.getHeight(), 17, SPRITE_PATH));
            setRightAttackAnimation(new Animation(0, 160,
                    boss.getWidth(), boss.getHeight(), 17, SPRITE_PATH));
            setLeftAttackAnimation(new Animation(0, 560,
                    boss.getWidth(), boss.getHeight(), 17, SPRITE_PATH));

            setRightHurtAnimation(new Animation(0, 240,
                    boss.getWidth(), boss.getHeight(), 5, SPRITE_PATH));
            setLeftHurtAnimation(new Animation(0, 640,
                    boss.getWidth(), boss.getHeight(), 5, SPRITE_PATH));

            setRightDeadAnimation(new Animation(0, 320,
                    boss.getWidth(), boss.getHeight(), 9, SPRITE_PATH));
            setLeftDeadAnimation(new Animation(0, 720,
                    boss.getWidth(), boss.getHeight(), 9, SPRITE_PATH));        }
    }
}
