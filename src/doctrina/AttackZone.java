package doctrina;

import java.awt.*;

public class AttackZone {

    private final MovableEntity entity;
    private final int attackRadius;

    public AttackZone(MovableEntity entity, int attackRadius) {
        this.entity = entity;
        this.attackRadius = attackRadius;
    }

    public Bounds getAttackZone() {
        return switch (entity.getDirection()) {
            case UP -> getUpperAttackZone();
            case DOWN -> getLowerAttackZone();
            case LEFT -> getLeftAttackZone();
            case RIGHT -> getRightAttackZone();
        };
    }

    public void draw(Canvas canvas, Camera camera) {
        Bounds bounds = getAttackZone();
        Color color = new Color(0, 255, 0, 200);
        canvas.drawRectangle(bounds.getX() - camera.getX(), bounds.getY() - camera.getY(), bounds.getWidth(), bounds.getHeight(), color);
    }

    private Bounds getUpperAttackZone() {
        return new Bounds(entity.x, (entity.y - attackRadius + (entity.height / 2)), entity.width, attackRadius);
    }

    private Bounds getLowerAttackZone() {
        return new Bounds(entity.x, entity.y + entity.height, entity.width, attackRadius);
    }

    private Bounds getLeftAttackZone() {
        return new Bounds((entity.x - attackRadius), entity.y + (entity.height / 2), attackRadius, entity.height / 2);
    }

    private Bounds getRightAttackZone() {
        return new Bounds(entity.x + entity.width, entity.y + (entity.height / 2), attackRadius, entity.height / 2);
    }
}
