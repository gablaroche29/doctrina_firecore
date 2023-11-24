package doctrina;

import doctrina.SpriteSheetSlicer;

import java.awt.*;

public class Animation {

    private final Image[] sprites;

    public Animation(int x, int y,
                     int spriteWidth, int spriteHeight, int numberOfSprite, String path) {
        sprites = SpriteSheetSlicer.getSprites(x, y, spriteWidth, spriteHeight, numberOfSprite, path);
    }

    public Image getSprite(int i) {
        if (i >= getLengthSprites()) {
            return sprites[0];
        }
        return sprites[i];
    }

    public int getLengthSprites() {
        return sprites.length;
    }
}
