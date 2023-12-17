package utopia.event;


import doctrina.Canvas;

import java.awt.*;

public class EnemyKilledEvent extends GameEvent {

    public EnemyKilledEvent(int cooldown) {
        super(cooldown);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRectangle(10, 300, 300, 150, 35, 35, Color.BLACK);
        int startY = 340;
        for (String string : text) {
            canvas.drawString(string, 40, startY, Color.WHITE, fontLoader.getFont());
            startY += 40;
        }
        update();
    }
}
