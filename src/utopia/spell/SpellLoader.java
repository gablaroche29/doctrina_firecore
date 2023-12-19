package utopia.spell;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.MovableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SpellLoader {

    private final MovableEntity entity;
    protected final List<Spell> spells;
    protected final int SPELLS_COUNT = 5;
    private int counter = 0;
    protected abstract void setupSpells();

    public SpellLoader(MovableEntity entity) {
        this.entity = entity;
        this.spells = new ArrayList<>();
        setupSpells();
    }

    public void update() {
        for (Spell spell : spells) {
            if (spell.isRender()) {
                spell.update();
            }
            if (!spell.isStillValid()) {
                spell.remove();
            }
        }
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Spell spell : spells) {
            if (spell.isRender()) {
                spell.draw(canvas, camera);
            }
        }
    }

    public void shoot() {
        spells.get(counter).generate(entity);
        updateCounter();
    }

    public List<Spell> getSpells() {
        return spells.stream()
                .filter(Spell::isRender)
                .collect(Collectors.toList());
    }

    private void updateCounter() {
        counter++;
        if (counter >= SPELLS_COUNT) {
            counter = 0;
        }
    }
}
