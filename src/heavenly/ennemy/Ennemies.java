package heavenly.ennemy;

import doctrina.*;
import heavenly.Player;
import heavenly.World;

import java.util.ArrayList;
import java.util.List;

public class Ennemies {

    private List<Ia> ennemies;
    private List<Ia> deadEnnemies;
    private World world;

    private final Ia ia;
    private final Ia ia2;

    public Ennemies(World world, Player player) {
        this.world = world;
        ennemies = new ArrayList<>();
        ia = new Ia(1150, 2518, 1.5f, player);
        ia2 = new Ia(1130, 2538, 1.5f, player);
        ennemies.add(ia);
        ennemies.add(ia2);

        deadEnnemies = new ArrayList<>();
    }

    public void update() {
        for (Ia ia : ennemies) {
            ia.update();
            if (!ia.isAlive()) {
                deadEnnemies.add(ia);
            }
        }

        ennemies.removeAll(deadEnnemies);
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Ia ia : ennemies) {
            ia.draw(canvas, camera);
        }
    }

    public List<Ia> getEnnemies() {
        return ennemies;
    }
}
