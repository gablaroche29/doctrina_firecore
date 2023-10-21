package doctrina;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private final BufferedImage spriteSheet;

    private final int startingX;
    private final int startingY;
    private final int endX;
    private final int endY;
    private final int numberOfSpritePerRow;
    private final int numberOfSpritePerColumn;

    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;

    private final int spriteWidth;
    private final int spriteHeight;

    private static final int ANIMATION_SPEED = 8;
    private int currentAnimationFrame = 1; // idle
    private int nextFrame = ANIMATION_SPEED;

    public Animation(StaticEntity entity, BufferedImage spriteSheet,
                     int startingX, int startingY, int endX, int endY) {
        spriteWidth = entity.getWidth();
        spriteHeight = entity.getHeight();
        this.spriteSheet = spriteSheet;

        this.startingX = startingX;
        this.startingY = startingY;
        this.endX = endX;
        this.endY = endY;

        this.numberOfSpritePerRow = (endX - startingX) / spriteWidth;
        this.numberOfSpritePerColumn = (endY - startingY) / spriteHeight;

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
        nextFrame--;
        if (nextFrame == 0) {
            currentAnimationFrame++;
            if (currentAnimationFrame >= leftFrames.length) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    public void reset() {
        currentAnimationFrame = 1;
    }

    private void loadAnimationFrames() {

        downFrames = setSprites(startingY);
        leftFrames = setSprites(startingY + spriteHeight);
        rightFrames = setSprites(startingY + (spriteHeight * 2));
        upFrames = setSprites(startingY + (spriteHeight * 3));
    }

    private Image[] setSprites(int height) {
        int currentX = startingX;
        Image[] sprites = new Image[numberOfSpritePerRow];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = spriteSheet.getSubimage(currentX, height, spriteWidth, spriteHeight);
            currentX += spriteWidth;
        }
        return sprites;
    }
}
