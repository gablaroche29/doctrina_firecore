package utopia.menu.option;

import doctrina.Bounds;
import doctrina.Canvas;

import java.awt.*;
import java.math.BigDecimal;

public class AudioLevelIcon {

    private final Bounds dimension;
    private final AudioControl audioControl;
    private final boolean plus;

    public AudioLevelIcon(int x, int y, int width, int height, AudioControl audioControl, boolean plus) {
        dimension = new Bounds(x, y, width, height);
        this.audioControl = audioControl;
        this.plus = plus;
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRectangle(dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight(), 10, 10, Color.WHITE);
        if (plus) {
            canvas.drawString("+", dimension.getX() + 6, dimension.getY() + 23, Color.BLACK);
        } else {
            canvas.drawString("-", dimension.getX() + 10, dimension.getY() + 20, Color.BLACK);
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
}
