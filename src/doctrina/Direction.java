package doctrina;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1);

    private final int velocityX;
    private final int velocityY;

    Direction(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public float calculateVelocityX(float speed) {
        return velocityX * speed;
    }

    public float calculateVelocityY(float speed) {
        return velocityY * speed;
    }
}
