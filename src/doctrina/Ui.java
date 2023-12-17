package doctrina;

import utopia.entities.chest.Chest;
import utopia.event.ChestEvent;
import utopia.event.EnemyKilledEvent;
import utopia.player.Player;

import java.awt.*;

public class Ui {
    private final Image[] healthBar;
    private final Image crystal;
    private final FontLoader fontLoader;

    private static String[] dialogueText;

    private final Player player;

    private int hurtEventCoooldown = 50;

    private static ChestEvent chestEvent;
    private static EnemyKilledEvent enemyKilledEvent;

    public Ui(Player player) {
        this.player = player;
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 15.f);
        String HEALTH_BAR_PATH = "image/ui/health_bar.png";
        healthBar = SpriteSheetSlicer.getSprites(0, 0, 104, 28, 5, HEALTH_BAR_PATH);
        String CRYSTAL_PATH = "image/ui/crystal.png";
        crystal = SpriteSheetSlicer.getSprite(0, 0, 64, 64, CRYSTAL_PATH);
        chestEvent = new ChestEvent(100);
        enemyKilledEvent = new EnemyKilledEvent(100);
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
        for (String string : dialogueText) {
            canvas.drawString(string, 180, startY, Color.WHITE, fontLoader.getFont());
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
        drawEvent(canvas);
    }

    private void drawHurtDamage(Canvas canvas) {
        canvas.drawRectangle(0, 0, 800, 600, new Color(0.1f, 0, 0, 0.2f));
    }

    private void hurtEvent(Canvas canvas) {
        drawHurtDamage(canvas);
        hurtEventCoooldown--;
        if (hurtEventCoooldown <= 0) {
            hurtEventCoooldown = 50;
            player.setHurt(false);
        }
    }

    public static void openChest(int crystal) {
        chestEvent.setText("Tu as ouvert un coffre!\nIl y avait " + crystal + " crystal(s)!");
        chestEvent.setActive(true);
    }

    public static void enemyKilled(int crystal) {
        enemyKilledEvent.setText("Tu as obtenus:\n" + crystal + " crystal(s)!");
        enemyKilledEvent.setActive(true);
    }

    public static void setDialogueText(String dialogueText) {
        Ui.dialogueText = dialogueText.split("\n");
    }

    private void drawEvent(Canvas canvas) {
        if (chestEvent.isActive()) {
            chestEvent.draw(canvas);
        }
        if (enemyKilledEvent.isActive()) {
            enemyKilledEvent.draw(canvas);
        }
    }
}
