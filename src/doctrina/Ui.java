package doctrina;

import utopia.player.Player;

import java.awt.*;

public class Ui extends StaticEntity {

    private final Image[] healthBar;
    private final String healthBarPath = "image/ui/health_bar.png";

    private final Player player;

    public Ui(Player player) {
        this.player = player;
        setDimension(800, 600);
        teleport(0, 0);

        healthBar = SpriteSheetSlicer.getSprites(0, 0, 104, 28, 5, healthBarPath);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        int indexPv = (player.getPv() == 0) ? 0 : player.getPv() - 1;
        canvas.drawImage(healthBar[indexPv], 10, 10);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 760, 20, Color.WHITE);

        if (GameConfig.isDebugEnabled()) {
            canvas.drawString("FPS " + GameTime.getCurrentFps(), 760, 20, Color.WHITE);
        }
    }
}
