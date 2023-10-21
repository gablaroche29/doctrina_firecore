package firecore;

import doctrina.Canvas;
import doctrina.Animation;
import doctrina.ControllableEntity;
import doctrina.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/characters/characters.png";
    private BufferedImage spriteSheet;
    private final Animation animation;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        loadSpriteSheet();
        animation = new Animation(this, spriteSheet, 192, 0, 288, 128);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        if (hasMoved()) {
            animation.update();
        } else {
            animation.reset();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Image sprite = animation.getSprite(getDirection());
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
