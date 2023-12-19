package utopia.event;

import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;

import java.awt.*;

public class DeathEvent extends GameEvent {

    private Image enterKey;
    private int enterKeyCooldownEnable;
    private int enterKeyCooldownDisable;
    private boolean canDisplay;

    private int valueCooldown = 0;

    public DeathEvent(int cooldown) {
        super(cooldown);
        enterKeyCooldownEnable = 20;
        enterKeyCooldownDisable = 10;
        load();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(0, 0, 800, 600, new Color(0, 0, 0, 150));
        canvas.drawString("Vous êtes mort...", 250, 250, Color.RED, fontLoader.getFont());

        if (canDisplay) {
            canvas.drawString("[Continuer]", 250, 300, Color.WHITE, fontLoader.getFont());
            canvas.drawImage(enterKey, 380, 280);
        }

        updateEnterKey();
        update();
    }

    private void updateEnterKey() {
        valueCooldown--;
        if (valueCooldown <= 0) {
            valueCooldown = 30;
            canDisplay = !canDisplay;
        }

    }

    private void load() {
        enterKey = SpriteSheetSlicer.getSprite(96, 0, 32, 32, "image/ui/keys.png");
    }
}
