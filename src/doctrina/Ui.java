package doctrina;

import utopia.player.Player;

import java.awt.*;

public class Ui {
    private final Image[] healthBar;
    private final Image crystal;
    private final String HEALTHBAR_PATH = "image/ui/health_bar.png";
    private final String CRYSTAL_PATH = "image/ui/crystal.png";
    private final FontLoader fontLoader;

    private static boolean openChest;
    private static String[] texts;
    private static String[] eventText;

    private final Player player;

    private int chestEventCooldown = 100;
    private int hurtEventCoooldown = 50;

    public Ui(Player player) {
        this.player = player;
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 25.0f);
        healthBar = SpriteSheetSlicer.getSprites(0, 0, 104, 28, 5, HEALTHBAR_PATH);
        crystal = SpriteSheetSlicer.getSprite(0, 0, 64, 64, CRYSTAL_PATH);
    }

    public void draw(Canvas canvas, GameState gameState) {
        switch (gameState) {
            case GAME -> drawGame(canvas);
            case DIALOGUE -> drawDialogue(canvas);
        }

    }

    public static void openChest(int crystal) {
        String text = "Tu as ouvert un coffre!\nIl y avait " + crystal + " crystal(s)!";
        setEventText(text);
        Ui.openChest = true;
    }

    private static void setEventText(String text) {
        eventText = text.split("\n");
    }

    public static void setTexts(String texts) {
        Ui.texts = texts.split("\n");
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
        String crystalQuantity = "X " + player.getCrystal();
        canvas.drawImage(healthBar[indexPv], 10, 10);
        canvas.drawImage(crystal, 0, 60, 48, 48);
        canvas.drawString(crystalQuantity, 50, 92, Color.WHITE, fontLoader.getFont());

        if (player.isHurt()) {
            hurtEvent(canvas);
        }
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 700, 20, Color.WHITE);
        if (openChest) {
            chestEvent(canvas);
        }
    }

    private void drawHurtDamage(Canvas canvas) {
        canvas.drawRectangle(0, 0, 800, 600, new Color(0.1f, 0, 0, 0.2f));
    }

    private void chestEvent(Canvas canvas) {
        drawEvent(canvas);
        chestEventCooldown--;
        if (chestEventCooldown <= 0) {
            chestEventCooldown = 100;
            openChest = false;
        }
    }

    private void hurtEvent(Canvas canvas) {
        drawHurtDamage(canvas);
        hurtEventCoooldown--;
        if (hurtEventCoooldown <= 0) {
            hurtEventCoooldown = 50;
            player.setHurt(false);
        }
    }
}
