package doctrina;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheetSlicer {

    public static Image getSprite(int startX, int startY, int spriteWidth, int spriteHeight, String path) {
        BufferedImage spriteSheet = loadSpriteSheet(path);
        return spriteSheet.getSubimage(startX, startY, spriteWidth, spriteHeight);
    }

    public static Image[] getSprites(int x, int y,
                                     int spriteWidth, int spriteHeight, int numberOfSprite, String path) {
        BufferedImage spriteSheet = loadSpriteSheet(path);
        Image[] sprites = new Image[numberOfSprite];

        int currentX = x;
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = spriteSheet.getSubimage(currentX, y, spriteWidth, spriteHeight);
            currentX += spriteWidth;
        }
        return sprites;
    }

//    public static Image[] getHorizontalySprites(int x, int y,
//                                              int spriteWidth, int spriteHeight, int numberOfSprite, String path) {
//        BufferedImage spriteSheet = loadSpriteSheet(path);
//        Image[] sprites = new Image[numberOfSprite];
//
//        int currentY = y;
//        for (int i = 0; i < sprites.length; i++) {
//            sprites[i] = spriteSheet.getSubimage(x, currentY, spriteWidth, spriteHeight);
//            currentY += spriteHeight;
//        }
//        return sprites;
//    }

    private static BufferedImage loadSpriteSheet(String path) {
        try {
            return ImageIO.read(SpriteSheetSlicer.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
