package doctrina;

import java.awt.*;

public class  AnimationHandler {

    private static final int ANIMATION_SPEED = 8;
    private int currentAnimationFrame = 1; // idle
    private int nextFrame = ANIMATION_SPEED;

    private Animation upAnimation;
    private Animation downAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;

    public Image getDirectionSprite(Direction direction) {
        if (direction == Direction.RIGHT) {
            return rightAnimation.getSprite(currentAnimationFrame);
        } else if (direction == Direction.LEFT) {
            return leftAnimation.getSprite(currentAnimationFrame);
        } else if (direction == Direction.UP) {
            return upAnimation.getSprite(currentAnimationFrame);
        } else if (direction == Direction.DOWN) {
            return downAnimation.getSprite(currentAnimationFrame);
        }
        return downAnimation.getSprite(1);
    }

    public void update() {
        nextFrame--;
        if (nextFrame == 0) {
            currentAnimationFrame++;
            if (currentAnimationFrame >= upAnimation.getLengthSprites()) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    public void reset() {
        currentAnimationFrame = 1;
    }

    public Animation getUpAnimation() {
        return upAnimation;
    }

    public void setUpAnimation(Animation upAnimation) {
        this.upAnimation = upAnimation;
    }

    public Animation getDownAnimation() {
        return downAnimation;
    }

    public void setDownAnimation(Animation downAnimation) {
        this.downAnimation = downAnimation;
    }

    public Animation getLeftAnimation() {
        return leftAnimation;
    }

    public void setLeftAnimation(Animation leftAnimation) {
        this.leftAnimation = leftAnimation;
    }

    public Animation getRightAnimation() {
        return rightAnimation;
    }

    public void setRightAnimation(Animation rightAnimation) {
        this.rightAnimation = rightAnimation;
    }
}
