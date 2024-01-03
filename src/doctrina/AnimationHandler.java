package doctrina;

import java.awt.*;

public abstract class AnimationHandler {

    private int ANIMATION_SPEED;
    public int currentAnimationFrame = 0;
    private int nextFrame = ANIMATION_SPEED;
    private Animation currentAnimation;
    private Animation upMovementAnimation;
    private Animation downMovementAnimation;
    private Animation leftMovementAnimation;
    private Animation rightMovementAnimation;
    private Animation upAttackAnimation;
    private Animation downAttackAnimation;
    private Animation leftAttackAnimation;
    private Animation rightAttackAnimation;
    private Animation upIdleAnimation;
    private Animation downIdleAnimation;
    private Animation leftIdleAnimation;
    private Animation rightIdleAnimation;
    private Animation upDashAnimation;
    private Animation downDashAnimation;
    private Animation leftDashAnimation;
    private Animation rightDashAnimation;
    private Animation leftHurtAnimation;
    private Animation rightHurtAnimation;
    private Animation leftDeadAnimation;
    private Animation rightDeadAnimation;

    private final MovableEntity entity;

    public AnimationHandler(MovableEntity entity) {
        this.entity = entity;
    }

    private Animation getAnimationByDirection(State state) {
        Animation directionAnimation = switch (entity.getDirection()) {
            case RIGHT -> (state == State.ATTACK) ? rightAttackAnimation :
                    (state == State.IDLE) ? rightIdleAnimation :
                            (state == State.HURT) ? rightHurtAnimation :
                                    (state == State.DEAD) ? rightDeadAnimation :
                                            (state == State.DASHING) ? rightDashAnimation : rightMovementAnimation;
            case LEFT -> (state == State.ATTACK) ? leftAttackAnimation :
                    (state == State.IDLE) ? leftIdleAnimation :
                            (state == State.HURT) ? leftHurtAnimation :
                                    (state == State.DEAD) ? leftDeadAnimation :
                                            (state == State.DASHING) ? leftDashAnimation : leftMovementAnimation;
            case UP -> (state == State.ATTACK) ? upAttackAnimation :
                    (state == State.IDLE) ? upIdleAnimation :
                            (state == State.HURT) ? rightHurtAnimation :
                                    (state == State.DEAD) ? rightDeadAnimation :
                                            (state == State.DASHING) ? upDashAnimation : upMovementAnimation;
            case DOWN -> (state == State.ATTACK) ? downAttackAnimation :
                    (state == State.IDLE) ? downIdleAnimation :
                            (state == State.HURT) ? leftHurtAnimation :
                                    (state == State.DEAD) ? leftDeadAnimation :
                                            (state == State.DASHING) ? downDashAnimation : downMovementAnimation;
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

    public Image getHurtFrame() {
        return getAnimationByDirection(State.HURT).getSprite(currentAnimationFrame);
    }

    public Image getDeadFrame() {
        return getAnimationByDirection(State.DEAD).getSprite(currentAnimationFrame);
    }

    public Image getDashFrame() {
        return getAnimationByDirection(State.DASHING).getSprite(currentAnimationFrame);
    }

    public Animation getIdleAnimation() {
        return getAnimationByDirection(State.IDLE);
    }

    public Image getIdleFrame() {
        return getAnimationByDirection(State.IDLE).getSprite(currentAnimationFrame);
    }

    public void setAnimationSpeed(int speed) {
        ANIMATION_SPEED = speed;
        nextFrame = ANIMATION_SPEED;
    }

    public void update() {
        updateCurrentAnimation();
        nextFrame--;
        if (nextFrame == 0) {
            currentAnimationFrame++;
            if (currentAnimationFrame > (currentAnimation.length() - 1)) {
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
            case HURT -> currentAnimation = getHurtAnimation();
            case DEAD -> currentAnimation = getDeadAnimation();
            case DASHING -> currentAnimation = getDashAnimation();
        }
    }

    public Animation getHurtAnimation() {
        return getAnimationByDirection(State.HURT);
    }

    public Animation getDeadAnimation() {
        return getAnimationByDirection(State.DEAD);
    }

    public Animation getDashAnimation() {
        return getAnimationByDirection(State.DASHING);
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

    public void setLeftHurtAnimation(Animation leftHurtAnimation) {
        this.leftHurtAnimation = leftHurtAnimation;
    }

    public void setRightHurtAnimation(Animation rightHurtAnimation) {
        this.rightHurtAnimation = rightHurtAnimation;
    }

    public void setLeftDeadAnimation(Animation leftDeadAnimation) {
        this.leftDeadAnimation = leftDeadAnimation;
    }

    public void setRightDeadAnimation(Animation rightDeadAnimation) {
        this.rightDeadAnimation = rightDeadAnimation;
    }

    public void setUpDashAnimation(Animation upDashAnimation) {
        this.upDashAnimation = upDashAnimation;
    }

    public void setDownDashAnimation(Animation downDashAnimation) {
        this.downDashAnimation = downDashAnimation;
    }

    public void setRightDashAnimation(Animation rightDashAnimation) {
        this.rightDashAnimation = rightDashAnimation;
    }

    public void setLeftDashAnimation(Animation leftDashAnimation) {
        this.leftDashAnimation = leftDashAnimation;
    }
}
