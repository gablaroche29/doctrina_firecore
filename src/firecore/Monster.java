package firecore;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.MovableEntity;
import doctrina.SpriteSheetSlicer;

import java.awt.*;
import java.util.Random;

public class Monster extends MovableEntity {

    private static final String SPRITE_PATH = "images/characters/monsters.png";
    private Image image;

    private int lapse = 30;

    public Monster() {
        setDimension(32, 32);
        teleport(500, 500);
        setSpeed(3);
        image = SpriteSheetSlicer.getSprite(128, 0, 32, 32, SPRITE_PATH);
    }

    @Override
    public void update() {
        super.update();
        Random random = new Random();

        if (lapse == 0) {
            int num = random.nextInt(4) + 1;
            switch (num) {
                case 1 -> {
                    moveDown();
                    lapse = 30;
                }
                case 2 -> {
                    moveLeft();
                    lapse = 30;
                }
                case 3 -> {
                    moveRight();
                    lapse = 30;
                }
                case 4 -> {
                    moveUp();
                    lapse = 30;
                }
            }
        }
        lapse--;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(image, x - camera.getX(), y - camera.getY());
        drawCollisionDetector(canvas, camera);
    }
}
