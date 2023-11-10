package doctrina;

import java.awt.*;

public class Collision {

    private final MovableEntity entity;

    public Collision(MovableEntity entity) {
        this.entity = entity;
    }

    public float getAllowedSpeed(Direction direction) {
        return switch (direction) {
            case UP -> getAllowedUpSpeed();
            case DOWN -> getAllowedDownSpeed();
            case LEFT -> getAllowedLeftSpeed();
            case RIGHT -> getAllowedRightSpeed();
        };
    }

    private float getAllowedUpSpeed() {
        return distance(other -> entity.y - (other.y + (other.height / 2)));
    }

    private float getAllowedDownSpeed() {
        return distance(other -> other.y - (entity.y + entity.height));
    }

    private float getAllowedLeftSpeed() {
        return distance(other -> entity.x - (other.x + other.width));
    }

    private float getAllowedRightSpeed() {
        return distance(other -> other.x - (entity.x + entity.width));
    }

    private float distance(DistanceCalculator calculator) {
        Rectangle collisionBound = entity.getHitBox();
        float allowedDistance = entity.getSpeed();
        for (StaticEntity other : CollidableRepository.getInstance()) {
            if (collisionBound.intersects(other.getBounds())) {
                allowedDistance = Math.min(allowedDistance, calculator.calculateWith(other));
            }
        }
        return allowedDistance;
    }

    private interface DistanceCalculator {
        int calculateWith(StaticEntity other);
    }

}
