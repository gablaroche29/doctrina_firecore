package utopia.missile;

import doctrina.Animation;
import doctrina.AnimationHandler;
import doctrina.MovableEntity;

public class MissileAnimationHandler extends AnimationHandler {

    private static final String ICEBALL_X_PATH = "image/items/iceball_x.png";
    private static final String ICEBALL_Y_PATH = "image/items/iceball_y.png";

    public MissileAnimationHandler(MovableEntity entity) {
        super(entity);
        setAnimationSpeed(20);
        setDownMovementAnimation(new Animation(54, 0,
                9, 54, 6, ICEBALL_Y_PATH));
        setLeftMovementAnimation(new Animation(0, 0,
                54, 9, 6, ICEBALL_X_PATH));
        setRightMovementAnimation(new Animation(0, 9,
                54, 9, 6, ICEBALL_X_PATH));
        setUpMovementAnimation(new Animation(0, 0,
                9, 54, 6, ICEBALL_Y_PATH));
    }
}
