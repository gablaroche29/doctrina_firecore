package doctrina;

import java.awt.*;

public class  AnimationHandler {

    private static int ANIMATION_SPEED = 5;
    private int currentAnimationFrame = 1; // idle
    private int nextFrame = ANIMATION_SPEED;

    private Animation upAnimation;
    private Animation downAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation resetAnimation;

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
        return (resetAnimation != null) ? resetAnimation.getSprite(currentAnimationFrame) : downAnimation.getSprite(1);
    }

    public void setAnimationSpeed(int speed) {
        ANIMATION_SPEED = speed;
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

    public void setResetAnimation(Animation animation) {
        this.resetAnimation = animation;
    }

    public void reset() {
        currentAnimationFrame = 1;
    }

    public Animation getUpAnimation() {
        return upAnimation;
    }

    public void setUpAnimation(Animation animation) {
        this.upAnimation = animation;
    }

    public Animation getDownAnimation() {
        return downAnimation;
    }

    public void setDownAnimation(Animation animation) {
        this.downAnimation = animation;
    }

    public Animation getLeftAnimation() {
        return leftAnimation;
    }

    public void setLeftAnimation(Animation animation) {
        this.leftAnimation = animation;
    }

    public Animation getRightAnimation() {
        return rightAnimation;
    }

    public void setRightAnimation(Animation animation) {
        this.rightAnimation = animation;
    }
}
