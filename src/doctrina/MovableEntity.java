package doctrina;

import java.awt.*;

public abstract class MovableEntity extends StaticEntity {

    private float speed = 1;
    private AttackZone attackZone;
    private Direction direction = Direction.UP;
    private final Collision collision;
    private int lastX = Integer.MIN_VALUE;
    private int lastY = Integer.MIN_VALUE;
    private boolean moved = false;
    private final static int collisionDetectorBound = 100;
    private final static int collisionDetectorPos = 50;

    public MovableEntity(int attackRadius) {
        collision = new Collision(this);
        attackZone = new AttackZone(this, attackRadius);
    }

    public void update() {
        moved = false;
    }

    public void move() {
        float allowedSpeed = collision.getAllowedSpeed(direction);
        x += (int) direction.calculateVelocityX(allowedSpeed);
        y += (int) direction.calculateVelocityY(allowedSpeed);
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void move(Direction direction) {
        this.direction = direction;
        move();
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public Rectangle getHitBox() {
        return switch (direction) {
            case UP -> getUpperHitBox();
            case DOWN -> getLowerHitBox();
            case LEFT -> getLeftHitBox();
            case RIGHT -> getRightHitBox();
        };
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean hitBoxIntersectWidth(StaticEntity other) {
        if (other == null) {
            return false;
        }
        return getHitBox().intersects(other.getBounds());
    }

    public void drawHitBox(Canvas canvas, Camera camera) {
        Rectangle rectangle = getHitBox();
        Color color = new Color(255, 0, 0, 200);
        canvas.drawRectangle(rectangle.x - camera.getX(), rectangle.y - camera.getY(), rectangle.width, rectangle.height, color);
    }

    public void drawAttackZone(Canvas canvas, Camera camera) {
        attackZone.draw(canvas, camera);
    }

    public Bounds getAttackZone() {
        return attackZone.getAttackZone();
    }

    public Rectangle getCollisionDetector() {
        return new Rectangle(x - collisionDetectorPos, y - collisionDetectorPos, width + collisionDetectorBound, height + collisionDetectorBound);
    }

    public void drawCollisionDetector(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x - camera.getX() - collisionDetectorPos, y - camera.getY() - collisionDetectorPos, width + collisionDetectorBound, height + collisionDetectorBound, new Color(0, 0, 255, 100));
    }

    private Rectangle getUpperHitBox() {
        return new Rectangle(x + 3, (int) (y - speed + (height / 2)), width - 6, (int) speed);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x + 3, y + height, width - 6, (int) speed);
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle((int) (x - speed), y + (height / 2), (int) speed, height / 2);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y + (height / 2), (int) speed, height / 2);
    }
}
