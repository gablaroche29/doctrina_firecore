package doctrina;

import utopia.GamePad;
import utopia.player.Player;

import java.awt.*;
import java.util.Arrays;

public class Ui {
    private final Image[] healthBar;
    private final String healthBarPath = "image/ui/health_bar.png";
    private final FontLoader fontLoader;

    private static boolean openChest;
    private static String[] texts;
    private static String[] eventText;

    private final Player player;

    private int eventCooldown = 100;

    public Ui(Player player) {
        this.player = player;
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 25.0f);
        healthBar = SpriteSheetSlicer.getSprites(0, 0, 104, 28, 5, healthBarPath);
    }

    public void draw(Canvas canvas, GameState gameState) {
        switch (gameState) {
            case GAME -> drawGame(canvas);
            case DIALOGUE -> drawDialogue(canvas);
        }

    }

    private void drawDialogue(Canvas canvas) {
        canvas.drawRoundRectangle(150, 30, 500, 200, 35, 35, Color.BLACK);
        int startY = 70;
        for (String string : texts) {
            canvas.drawString(string, 180, startY, Color.WHITE, fontLoader.getFont());
            startY += 40;
        }
    }

    private void drawEvent(Canvas canvas) {
        canvas.drawRoundRectangle(450, 400, 300, 150, 35, 35, Color.BLACK);
        int startY = 440;
        for (String string : eventText) {
            canvas.drawString(string, 480, startY, Color.WHITE, fontLoader.getFont());
            startY += 40;
        }
    }

    private void drawGame(Canvas canvas) {
        int indexPv = (player.getPv() == 0) ? 0 : player.getPv() - 1;
        canvas.drawImage(healthBar[indexPv], 10, 10);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 760, 20, Color.WHITE);
        if (openChest) {
            chestEvent(canvas);
        }
    }

    public static void openChest() {
        Ui.openChest = true;
    }

    public static void setTexts(String texts) {
        Ui.texts = texts.split("\n");
    }

    private void setEventText(String text) {
        eventText = text.split("\n");
    }

    private void chestEvent(Canvas canvas) {
        String text = "Tu as ouvert un coffre!\nIl y avait deux lingots!";
        setTexts(text);
        setEventText(text);
        drawEvent(canvas);
        eventCooldown--;
        if (eventCooldown <= 0) {
            eventCooldown = 100;
            openChest = false;
        }

    }
}
