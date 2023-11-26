package heavenly.enemy;

import doctrina.*;
import heavenly.Player;
import heavenly.World;

import java.util.ArrayList;
import java.util.List;

public class Enemies {

    private final List<Ia> enemies;
    private final List<Ia> deadEnemies;
    private World world;

    public Enemies(World world, Player player) {
        this.world = world;
        enemies = new ArrayList<>();
        enemies.add(new Ia(1150, 2518, 1.5f, player));
        enemies.add(new Ia(1130, 2538, 1.5f, player));

        deadEnemies = new ArrayList<>();
    }

    public void update() {
        for (Ia ia : enemies) {
            ia.update();
            if (!ia.isAlive()) {
                deadEnemies.add(ia);
            }
        }
        enemies.removeAll(deadEnemies);
        deadEnemies.clear();
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Ia ia : enemies) {
            ia.draw(canvas, camera);
        }
    }
}
