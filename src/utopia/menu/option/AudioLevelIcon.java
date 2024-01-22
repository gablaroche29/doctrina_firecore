package utopia.menu.option;

import doctrina.Bounds;
import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;

import java.awt.*;
import java.math.BigDecimal;

public class AudioLevelIcon {

    private Image plusIcon;
    private Image minusIcon;
    private final Bounds dimension;
    private final AudioControl audioControl;
    private final boolean plus;

    public AudioLevelIcon(int x, int y, int width, int height, AudioControl audioControl, boolean plus) {
        dimension = new Bounds(x, y, width, height);
        this.audioControl = audioControl;
        this.plus = plus;
        load();
    }

    public void draw(Canvas canvas) {
        if (plus) {
            canvas.drawImage(plusIcon, dimension.getX(), dimension.getY());
        } else {
            canvas.drawImage(minusIcon, dimension.getX(), dimension.getY());
        }
    }

    public Bounds getDimension() {
        return dimension;
    }

    public void onClick() {
        float value = 0.1f;

        BigDecimal audioLevel = new BigDecimal(Float.toString(audioControl.getAudioLevel()));

        BigDecimal newValue;
        if (plus) {
            newValue = audioLevel.add(new BigDecimal(Float.toString(value)));
        } else {
            newValue = audioLevel.subtract(new BigDecimal(Float.toString(value)));
        }

        newValue = newValue.max(BigDecimal.ZERO).min(BigDecimal.ONE);

        audioControl.setAudioLevel(newValue.floatValue());
    }

    private void load() {
        minusIcon = SpriteSheetSlicer.getSprite(0, 0, 30, 30, "image/option/audio_level_icon.png");
        plusIcon = SpriteSheetSlicer.getSprite(30, 0, 30, 30, "image/option/audio_level_icon.png");
    }
}
