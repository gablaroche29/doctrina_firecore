package utopia.environment;

import doctrina.Camera;
import doctrina.Canvas;
import utopia.player.Player;

public class EnvironmentManager {

    private final Lighting lighting;

    public EnvironmentManager(Player player) {
        lighting = new Lighting(player, 300);
    }

    public void draw(Canvas canvas, Camera camera) {
        //lighting.draw(canvas, camera);
    }
}
