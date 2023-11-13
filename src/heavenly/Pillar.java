package heavenly;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;
import doctrina.StaticEntity;

import java.awt.*;

public class Pillar extends StaticEntity {

    private static final String PROP_PATH = "image/props/Props.png";
    private Image pillarImg;

    public Pillar() {
        teleport(800, 2400);
        load();
    }

    private void load() {
        pillarImg = SpriteSheetSlicer.getSprite(224, 0, 32, 64, PROP_PATH);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(pillarImg, x - camera.getX(), y - camera.getY());
    }
}
