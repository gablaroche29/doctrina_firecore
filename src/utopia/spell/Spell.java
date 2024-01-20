package utopia.spell;

import doctrina.*;
import doctrina.Canvas;

public class Spell extends MovableEntity {

    protected AnimationHandler animationHandler;
    private int distance = 0;
    protected int maxDistance = 125;

    public Spell(float speed) {
        super(2);
        teleport(0, 0);
        setState(State.MOVE);
        setSpeed(speed);
    }

    public void update() {
        super.update();
        move();
        distance += (int) getSpeed();
        animationHandler.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(animationHandler.getDirectionFrame(), x - camera.getX(), y - camera.getY());
    }

    public void generate(MovableEntity entity) {
        setDirection(entity.getDirection());
        generateByDirection(entity);
        setRender(true);
        distance = 0;
    }

    public void remove() {
        teleport(0, 0);
        setRender(false);
    }

    public boolean isStillValid() {
        return !isOutOfRange();
    }

    private void generateByDirection(MovableEntity entity) {
        switch (getDirection()) {
            case UP -> generateUp(entity);
            case RIGHT -> generateRight(entity);
            case LEFT -> generateLeft(entity);
            case DOWN -> generateDown(entity);
        }
    }

    private void generateUp(MovableEntity entity) {
        setDimension(9, 54);
        int posX = entity.getX() + (entity.getWidth() / 2) - (width / 2);
        int posY = entity.getY() - (height / 2);
        teleport(posX, posY);
    }

    private void generateRight(MovableEntity entity) {
        setDimension(54, 9);
        int posX = entity.getX() + (entity.getWidth() / 2);
        int posY = entity.getY() + (entity.getHeight() / 2);
        teleport(posX, posY);
    }

    private void generateLeft(MovableEntity entity) {
        setDimension(54, 9);
        int posX = entity.getX() - (width / 2);
        int posY = entity.getY() + (entity.getHeight() / 2);
        teleport(posX, posY);
    }

    private void generateDown(MovableEntity entity) {
        setDimension(9, 54);
        int posX = entity.getX() + (entity.getWidth() / 2) - (width / 2);
        int posY = entity.getY() + (height / 2);
        teleport(posX, posY);
    }

    private boolean isOutOfRange() {
        return distance >= maxDistance;
    }
}
