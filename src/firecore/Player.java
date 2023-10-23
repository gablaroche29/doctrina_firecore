package firecore;

import doctrina.Canvas;
import doctrina.AnimationHandler;
import doctrina.ControllableEntity;
import doctrina.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/characters/characters.png";
    private BufferedImage spriteSheet;
    private final AnimationHandler animationHandler;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        loadSpriteSheet();
        animationHandler = new AnimationHandler(this, spriteSheet, 192, 0, 288, 128);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        if (hasMoved()) {
            animationHandler.update();
        } else {
            animationHandler.reset();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Image sprite = animationHandler.getSprite(getDirection());
        canvas.drawImage(sprite, x, y);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
