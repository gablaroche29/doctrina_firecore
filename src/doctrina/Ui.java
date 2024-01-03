package doctrina;

import utopia.event.BossEvent;
import utopia.event.ChestEvent;
import utopia.event.DeathEvent;
import utopia.event.EnemyKilledEvent;
import utopia.player.Player;

import javax.sound.sampled.Clip;
import java.awt.*;

public class Ui {
    private final Image[] healthBar;
    private final Image crystal;
    private final Image potion;
    private final FontLoader fontLoader;

    private static String[] dialogueText;

    private final Player player;

    private int hurtEventCoooldown = 30;

    private static ChestEvent chestEvent;
    private static BossEvent bossEvent;
    private static EnemyKilledEvent enemyKilledEvent;
    private static DeathEvent deathEvent;

    public Ui(Player player) {
        this.player = player;
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 25.f);
        String HEALTH_BAR_PATH = "image/ui/health_bar.png";
        healthBar = SpriteSheetSlicer.getSprites(0, 0, 104, 28, 5, HEALTH_BAR_PATH);
        String CRYSTAL_PATH = "image/ui/crystal.png";
        crystal = SpriteSheetSlicer.getSprite(0, 0, 64, 64, CRYSTAL_PATH);
        String POTION_PATH = "image/items/potion.png";
        potion = SpriteSheetSlicer.getSprite(0, 0, 48, 48, POTION_PATH);
        chestEvent = new ChestEvent(100);
        enemyKilledEvent = new EnemyKilledEvent(100);
        deathEvent = new DeathEvent(Clip.LOOP_CONTINUOUSLY);
        bossEvent = new BossEvent(0);
    }

    public void draw(Canvas canvas, GameState gameState) {
        switch (gameState) {
            case GAME -> drawGame(canvas);
            case INTERACTION -> drawDialogue(canvas);
            case DEAD_PLAYER -> drawEvent(canvas);
        }
    }

    private void drawDialogue(Canvas canvas) {
        canvas.drawRoundRectangle(147, 27, 506, 206, 35, 35, Color.BLACK);
        canvas.drawRoundRectangle(150, 30, 500, 200, 35, 35, Color.WHITE);
        canvas.drawRoundRectangle(153, 33, 494, 194, 35, 35, Color.BLACK);
        int startY = 70;
        for (String string : dialogueText) {
            canvas.drawString(string, 180, startY, Color.WHITE, fontLoader.getFont());
            startY += 40;
        }
    }

    private void drawGame(Canvas canvas) {
        int indexPv = (player.getPv() == 0) ? 0 : player.getPv() - 1;
        String crystalQuantity = "X " + player.getCrystal();
        String potionQuantity = "X " + player.getPotion();
        canvas.drawImage(healthBar[indexPv], 10, 10);

        canvas.drawImage(crystal, 0, 60, 48, 48);
        canvas.drawString(crystalQuantity, 50, 92, Color.WHITE, fontLoader.getFont());

        canvas.drawImage(potion, 0, 120);
        canvas.drawString(potionQuantity, 50, 152, Color.WHITE, fontLoader.getFont());

        if (player.isHurt()) {
            hurtEvent(canvas);
        }

        drawEvent(canvas);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 700, 20, Color.WHITE);
        if (GameConfig.isDebugEnabled()) {
            canvas.drawString("FPS " + GameTime.getCurrentFps(), 700, 20, Color.WHITE);
        }
    }

    private void drawHurtDamage(Canvas canvas) {
        canvas.drawRectangle(0, 0, 800, 600, new Color(0.1f, 0, 0, 0.2f));
    }

    private void hurtEvent(Canvas canvas) {
        drawHurtDamage(canvas);
        hurtEventCoooldown--;
        if (hurtEventCoooldown <= 0) {
            hurtEventCoooldown = 30;
            player.setHurt(false);
        }
    }

    public static void openChest(int potion) {
        chestEvent.setText("Tu as ouvert un coffre!\nIl y avait " + potion + " potion(s)!");
        chestEvent.setActive(true);
    }

    public static void bossEventDetails(float hp, String name) {
        bossEvent.setText(name);
        bossEvent.setPvProp(hp);
        bossEvent.setActive(true);
    }

    public static void enemyKilled(int crystal) {
        enemyKilledEvent.setText("Tu as obtenus:\n" + crystal + " crystal(s)!");
        enemyKilledEvent.setActive(true);
    }

    public static void death(boolean value) {
        deathEvent.setActive(value);
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
        if (deathEvent.isActive()) {
            deathEvent.draw(canvas);
        }
        if (bossEvent.isActive()) {
            bossEvent.draw(canvas);
        }
    }
}
