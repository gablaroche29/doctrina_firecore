package utopia.player;

import doctrina.Camera;
import doctrina.Canvas;
import utopia.missile.Missile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Projectiles {

    private final Player player;
    private final List<Missile> missiles;
    private final int MISSILES_COUNT = 5;
    private int counter = 0;

    public Projectiles(Player player) {
        this.player = player;
        this.missiles = new ArrayList<>();
        setupMissiles();
    }

    public void update() {
        for (Missile missile : missiles) {
            if (missile.isRender()) {
                missile.update();
            }
            if (!missile.isStillValid()) {
                missile.remove();
            }
        }
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Missile missile : missiles) {
            if (missile.isRender()) {
                missile.draw(canvas, camera);
            }
        }
    }

    public void shoot() {
        missiles.get(counter).generate(player);
        updateCounter();
    }

    public List<Missile> getMissiles() {
        return missiles.stream()
                .filter(Missile::isRender)
                .collect(Collectors.toList());
    }

    private void setupMissiles() {
        for (int i = 0; i < MISSILES_COUNT; i++) {
            missiles.add(new Missile());
        }
    }

    private void updateCounter() {
        counter++;
        if (counter >= MISSILES_COUNT) {
            counter = 0;
        }
    }
}
