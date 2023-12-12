package doctrina;

import utopia.player.Player;

import java.awt.*;

public class Ui {

    private final int x = 0;
    private final int y = 0;
    private final int width = 800;
    private final int height = 600;
    private final Image[] healthBar;
    private final String healthBarPath = "image/ui/health_bar.png";
    private final FontLoader fontLoader;

    private static String[] texts;

    private final Player player;

    public Ui(Player player) {
        this.player = player;
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 25.0f);
        healthBar = SpriteSheetSlicer.getSprites(0, 0, 104, 28, 5, healthBarPath);
    }

    public void update(GameState gameState) {
        switch (gameState) {
            case GAME -> updateGame();
        }
    }

    public void draw(Canvas canvas, GameState gameState) {
        switch (gameState) {
            case GAME -> drawGame(canvas);
            case DIALOGUE -> drawDialogue(canvas);
        }

    }

    private void updateGame() {
        // TODO: 2023-12-12
    }

    private void drawDialogue(Canvas canvas) {
        canvas.drawRoundRectangle(150, 32, 500, 200, 35, 35, Color.BLACK);
        int startY = 70;
        for (String string : texts) {
            canvas.drawString(string, 180, startY, Color.WHITE, fontLoader.getFont());
            startY += 40;
        }
    }

    private void drawGame(Canvas canvas) {
        int indexPv = (player.getPv() == 0) ? 0 : player.getPv() - 1;
        canvas.drawImage(healthBar[indexPv], 10, 10);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 760, 20, Color.WHITE);
        if (GameConfig.isDebugEnabled()) {
            canvas.drawString("FPS " + GameTime.getCurrentFps(), 760, 20, Color.WHITE);
        }
    }

    public static void setTexts(String texts) {
        Ui.texts = Ui.stringCutter(texts, "\n");
    }

    private static String[] stringCutter(String text, String separator) {
        return text.split(separator);
    }
}
