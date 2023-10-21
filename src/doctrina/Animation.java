package doctrina;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private final BufferedImage spriteSheet;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;

    private final int spriteWidth;
    private final int spriteHeight;

    private static final int ANIMATION_SPEED = 8;
    private int currentAnimationFrame = 1; // idle
    private int nexFrame = ANIMATION_SPEED;

    public Animation(StaticEntity entity, BufferedImage spriteSheet) {
        spriteWidth = entity.getWidth();
        spriteHeight = entity.getHeight();
        this.spriteSheet = spriteSheet;
        loadAnimationFrames();
    }

    public Image getSprite(Direction direction) {
        if (direction == Direction.RIGHT) {
            return rightFrames[currentAnimationFrame];
        } else if (direction == Direction.LEFT) {
            return leftFrames[currentAnimationFrame];
        } else if (direction == Direction.UP) {
            return upFrames[currentAnimationFrame];
        } else if (direction == Direction.DOWN) {
            return downFrames[currentAnimationFrame];
        }
        return upFrames[1];
    }

    public void update() {
        nexFrame--;
        if (nexFrame == 0) {
            currentAnimationFrame++;
            if (currentAnimationFrame >= leftFrames.length) {
                currentAnimationFrame = 0;
            }
            nexFrame = ANIMATION_SPEED;
        }
    }

    public void reset() {
        currentAnimationFrame = 1;
    }

    private void loadAnimationFrames() {
        downFrames = setSprites(0);
        leftFrames = setSprites(32);
        rightFrames = setSprites(64);
        upFrames = setSprites(96);
    }

    private Image[] setSprites(int height) {
        Image[] sprites = new Image[3];
        int width = 192;
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = spriteSheet.getSubimage(width, height, spriteWidth, spriteHeight);
            width += spriteWidth;
        }
        return sprites;
    }
}
