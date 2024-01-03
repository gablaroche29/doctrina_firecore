package utopia.entities.png;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.MovableEntity;

public class BlackSmithAnimationHandler extends AnimationHandler {

    private static final String PATH = "image/characters/BlackSmith/blacksmith.png";

    public BlackSmithAnimationHandler(MovableEntity movableEntity) {
        super(movableEntity);
        setAnimationSpeed(10);
        setDownIdleAnimation(new Animation(0, 0, 32, 32, 8, PATH));
    }
}
