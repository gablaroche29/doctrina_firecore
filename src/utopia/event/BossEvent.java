package utopia.event;


import doctrina.Canvas;

import java.awt.*;

public class BossEvent extends GameEvent {

    private int pvProp;

    public BossEvent(int cooldown) {
        super(cooldown);
    }

    @Override
    public void draw(Canvas canvas) {
        int barWidth = (int) (720 * (pvProp / 100.0));
        canvas.drawRoundRectangle(37, 527, 726, 36, 35, 35, Color.BLACK);
        canvas.drawRoundRectangle(40, 530, barWidth, 30, 35, 35, Color.RED);
        int startY = 500;
        for (String string : text) {
            canvas.drawString(string, 40, startY, Color.WHITE, fontLoader.getFont());
            startY += 40;
        }
        update();
    }

    public void setPvProp(float pvProp) {
        this.pvProp = (int) pvProp;
    }
}
