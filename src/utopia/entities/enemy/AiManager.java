package utopia.entities.enemy;

import doctrina.*;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.List;

public class AiManager {

    private final List<Ai> enemies;
    private final List<Ai> deadEnemies;

    public AiManager(Player player) {
        enemies = new ArrayList<>();
        enemies.add(new Ai(1150, 2518, 1.5f, player));
        enemies.add(new Ai(1130, 2538, 1.5f, player));

        deadEnemies = new ArrayList<>();
    }

    public void update() {
        deadEnemies.clear();
        for (Ai ai : enemies) {
            ai.update();
            if (!ai.isAlive()) {
                deadEnemies.add(ai);
            }
        }
        enemies.removeAll(deadEnemies);
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Ai ai : enemies) {
            ai.draw(canvas, camera);
        }
    }

    public List<Ai> getDeadEnemies() {
        return deadEnemies;
    }

    public List<Ai> getEnemies() {
        return enemies;
    }
}
