package doctrina;

import java.awt.*;

public abstract class AnimationHandler {

    private static int ANIMATION_SPEED = 6;
    private int currentAnimationFrame = 1; // idle
    private int nextFrame = ANIMATION_SPEED;
    private Animation currentAnimation;
    private Animation upMovementAnimation;
    private Animation downMovementAnimation;
    private Animation leftMovementAnimation;
    private Animation rightMovementAnimation;
    private Animation resetAnimation;
    private Animation upAttackAnimation;
    private Animation downAttackAnimation;
    private Animation leftAttackAnimation;
    private Animation rightAttackAnimation;
    private Animation upIdleAnimation;
    private Animation downIdleAnimation;
    private Animation leftIdleAnimation;
    private Animation rightIdleAnimation;

    private MovableEntity entity;

    public AnimationHandler(MovableEntity entity) {
        this.entity = entity;
    }

    private Animation getAnimationByDirection(State state) {
        Animation directionAnimation = switch (entity.getDirection()) {
            case RIGHT -> (state == State.ATTACK) ? rightAttackAnimation :
                    (state == State.IDLE) ? rightIdleAnimation : rightMovementAnimation;
            case LEFT -> (state == State.ATTACK) ? leftAttackAnimation :
                    (state == State.IDLE) ? leftIdleAnimation : leftMovementAnimation;
            case UP -> (state == State.ATTACK) ? upAttackAnimation :
                    (state == State.IDLE) ? upIdleAnimation : upMovementAnimation;
            case DOWN -> (state == State.ATTACK) ? downAttackAnimation :
                    (state == State.IDLE) ? downIdleAnimation : downMovementAnimation;
        };
        return (directionAnimation != null) ? directionAnimation : downMovementAnimation;
    }

    public Image getDirectionFrame() {
        return getAnimationByDirection(State.MOVE).getSprite(currentAnimationFrame);
    }

    public Animation getDirectionAnimation() {
        return getAnimationByDirection(State.MOVE);
    }

    public Animation getAttackAnimation() {
        return getAnimationByDirection(State.ATTACK);
    }

    public Image getAttackFrame() {
        return getAnimationByDirection(State.ATTACK).getSprite(currentAnimationFrame);
    }

    public Animation getIdleAnimation() {
        return getAnimationByDirection(State.IDLE);
    }

    public Image getIdleFrame() {
        return getAnimationByDirection(State.IDLE).getSprite(currentAnimationFrame);
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
            case IDLE -> currentAnimation = getIdleAnimation();
            case MOVE -> currentAnimation = getDirectionAnimation();
            case ATTACK -> currentAnimation = getAttackAnimation();
        }
    }

    public void setResetAnimation(Animation animation) {
        this.resetAnimation = animation;
    }

    public void reset() {
        currentAnimationFrame = 0;
    }

    public void setUpMovementAnimation(Animation animation) {
        this.upMovementAnimation = animation;
    }

    public void setDownMovementAnimation(Animation animation) {
        this.downMovementAnimation = animation;
    }

    public void setLeftMovementAnimation(Animation animation) {
        this.leftMovementAnimation = animation;
    }

    public void setRightMovementAnimation(Animation animation) {
        this.rightMovementAnimation = animation;
    }

    public void setDownAttackAnimation(Animation downAttackAnimation) {
        this.downAttackAnimation = downAttackAnimation;
    }

    public void setUpAttackAnimation(Animation upAttackAnimation) {
        this.upAttackAnimation = upAttackAnimation;
    }

    public void setLeftAttackAnimation(Animation leftAttackAnimation) {
        this.leftAttackAnimation = leftAttackAnimation;
    }

    public void setRightAttackAnimation(Animation rightAttackAnimation) {
        this.rightAttackAnimation = rightAttackAnimation;
    }

    public void setUpIdleAnimation(Animation upIdleAnimation) {
        this.upIdleAnimation = upIdleAnimation;
    }

    public void setDownIdleAnimation(Animation downIdleAnimation) {
        this.downIdleAnimation = downIdleAnimation;
    }

    public void setLeftIdleAnimation(Animation leftIdleAnimation) {
        this.leftIdleAnimation = leftIdleAnimation;
    }

    public void setRightIdleAnimation(Animation rightIdleAnimation) {
        this.rightIdleAnimation = rightIdleAnimation;
    }
}
