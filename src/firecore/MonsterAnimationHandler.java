package firecore;

import doctrina.Animation;
import doctrina.AnimationHandler;

public class MonsterAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH = "images/characters/monsters.png";

    public MonsterAnimationHandler(Monster monster) {
        setAnimationSpeed(10);
        setResetAnimation(new Animation(96, 0,
                monster.getWidth(), monster.getHeight(), 3, SPRITE_PATH));
        setDownAnimation(new Animation(96, 0,
                monster.getWidth(), monster.getHeight(), 3, SPRITE_PATH));
        setLeftAnimation(new Animation(96, 32,
                monster.getWidth(), monster.getHeight(), 3, SPRITE_PATH));
        setRightAnimation(new Animation(96, 64,
                monster.getWidth(), monster.getHeight(), 3, SPRITE_PATH));
        setUpAnimation(new Animation(96, 96,
                monster.getWidth(), monster.getHeight(), 3, SPRITE_PATH));
    }
}
