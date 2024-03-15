package utopia;

import doctrina.*;
import doctrina.Canvas;
import utopia.player.Player;

import java.awt.*;

public class Inventory {

    private final Player player;
    private final Image potionVisual;
    private final Image crystalVisual;
    private final Image swordVisual;
    private final Image leftClickIcon;
    private final Image iceSpellVisual;
    private final Color bgBlack = new Color(0, 0, 0);
    private final Color bgWhite = new Color(255, 255, 255);

    public Inventory(Player player) {
        this.player = player;
        potionVisual = SpriteSheetSlicer.getSprite(0, 0, 48, 48, "image/items/potion.png");
        crystalVisual = SpriteSheetSlicer.getSprite(0, 0, 64, 64, "image/ui/crystal.png");
        swordVisual = SpriteSheetSlicer.getSprite(0, 0, 24, 96, "image/items/sword.png");
        leftClickIcon = SpriteSheetSlicer.getSprite(0, 0, 32, 32, "image/ui/keys.png");
        iceSpellVisual = SpriteSheetSlicer.getSprite(0, 0, 40, 80, "image/items/ice_spell.png");
    }

    public void update() {

    }

    public void draw(Canvas canvas, FontLoader fontLoader) {
        String crystalQuantity = "X " + player.getCrystal();
        String potionQuantity = "X " + player.getPotion() + " (1)";

        // Base
        canvas.drawRoundRectangle(497, 47, 256, 456, 35, 35, bgBlack);
        canvas.drawRoundRectangle(500, 50, 250, 450, 35, 35, bgWhite);
        canvas.drawRoundRectangle(503, 53, 244, 444, 35, 35, bgBlack);

        canvas.drawString("--- Items ---", 565, 92, Color.WHITE, fontLoader.getFont());
        // Potion
        canvas.drawImage(potionVisual, 510, 100);
        canvas.drawString(potionQuantity, 560, 132, Color.WHITE, fontLoader.getFont());
        // Crystal
        canvas.drawImage(crystalVisual, 510, 150, 48, 48);
        canvas.drawString(crystalQuantity, 560, 182, Color.WHITE, fontLoader.getFont());

        canvas.drawString("--- Équipements ---", 525, 235, Color.WHITE, fontLoader.getFont());
        // Épée
        canvas.drawImage(swordVisual, 525, 255);
        canvas.drawString("-Épée (", 560, 310, Color.WHITE, fontLoader.getFont());
        canvas.drawImage(leftClickIcon, 636, 290);
        canvas.drawString(")", 666, 310, Color.WHITE, fontLoader.getFont());

        // Ice Spell
        if (player.isIceSpellActive()) {
            canvas.drawImage(iceSpellVisual, 520, 360);
            canvas.drawString("-Sort de Glace", 560, 410, Color.WHITE, fontLoader.getFont());
            canvas.drawString("( Q )", 560, 450, Color.WHITE, fontLoader.getFont());
        }
    }
}
