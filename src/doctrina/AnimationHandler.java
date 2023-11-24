package doctrina;

import java.awt.*;

public class  AnimationHandler {

    private static int ANIMATION_SPEED = 6;
    private int currentAnimationFrame = 1; // idle
    private int nextFrame = ANIMATION_SPEED;
    private Animation currentAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation resetAnimation;
    private Animation upAttackAnimation;
    private Animation downAttackAnimation;
    private Animation leftAttackAnimation;
    private Animation rightAttackAnimation;

    private final MovableEntity entity;

    public AnimationHandler(MovableEntity entity) {
        this.entity = entity;
    }

    public Image getDirectionSprite() {
        if (entity.getDirection() == Direction.RIGHT) {
            return rightAnimation.getSprite(currentAnimationFrame);
        } else if (entity.getDirection() == Direction.LEFT) {
            return leftAnimation.getSprite(currentAnimationFrame);
        } else if (entity.getDirection() == Direction.UP) {
            return upAnimation.getSprite(currentAnimationFrame);
        } else if (entity.getDirection() == Direction.DOWN) {
            return downAnimation.getSprite(currentAnimationFrame);
        }
        return (resetAnimation != null) ? resetAnimation.getSprite(currentAnimationFrame) : downAnimation.getSprite(1);
    }

    public Animation getDirectionAnimation() {
        if (entity.getDirection() == Direction.RIGHT) {
            return rightAnimation;
        } else if (entity.getDirection() == Direction.LEFT) {
            return leftAnimation;
        } else if (entity.getDirection() == Direction.UP) {
            return upAnimation;
        } else if (entity.getDirection() == Direction.DOWN) {
            return downAnimation;
        }
        return (resetAnimation != null) ? resetAnimation : downAnimation;
    }

    public Animation getAttackAnimation() {
        if (entity.getDirection() == Direction.RIGHT) {
            return rightAttackAnimation;
        } else if (entity.getDirection() == Direction.LEFT) {
            return leftAttackAnimation;
        } else if (entity.getDirection() == Direction.UP) {
            return upAttackAnimation;
        } else if (entity.getDirection() == Direction.DOWN) {
            return downAttackAnimation;
        }
        return downAttackAnimation;
    }

    public Image getAttackFrame() {
        if (entity.getDirection() == Direction.RIGHT) {
            return rightAttackAnimation.getSprite(currentAnimationFrame);
        } else if (entity.getDirection() == Direction.LEFT) {
            return leftAttackAnimation.getSprite(currentAnimationFrame);
        } else if (entity.getDirection() == Direction.UP) {
            return upAttackAnimation.getSprite(currentAnimationFrame);
        } else if (entity.getDirection() == Direction.DOWN) {
            return downAttackAnimation.getSprite(currentAnimationFrame);
        }
        return downAttackAnimation.getSprite(currentAnimationFrame);
    }

    public Image getIdleSprite() {
        return resetAnimation.getSprite(currentAnimationFrame);
    }

    public void setAnimationSpeed(int speed) {
        ANIMATION_SPEED = speed;
    }

    public void update() {
        updateCurrentAnimation();
        nextFrame--;
        if (nextFrame == 0) {
            currentAnimationFrame++;
            if (currentAnimationFrame >= (currentAnimation.getLengthSprites())) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    private void updateCurrentAnimation() {
        switch (entity.getState()) {
            case IDLE -> currentAnimation = downAnimation;
            case MOVE -> currentAnimation = getDirectionAnimation();
            case ATTACK -> currentAnimation = getAttackAnimation();
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

    public Animation getDownAttackAnimation() {
        return downAttackAnimation;
    }

    public void setDownAttackAnimation(Animation downAttackAnimation) {
        this.downAttackAnimation = downAttackAnimation;
    }

    public Animation getUpAttackAnimation() {
        return upAttackAnimation;
    }

    public void setUpAttackAnimation(Animation upAttackAnimation) {
        this.upAttackAnimation = upAttackAnimation;
    }

    public Animation getLeftAttackAnimation() {
        return leftAttackAnimation;
    }

    public void setLeftAttackAnimation(Animation leftAttackAnimation) {
        this.leftAttackAnimation = leftAttackAnimation;
    }

    public Animation getRightAttackAnimation() {
        return rightAttackAnimation;
    }

    public void setRightAttackAnimation(Animation rightAttackAnimation) {
        this.rightAttackAnimation = rightAttackAnimation;
    }
}
