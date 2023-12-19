package utopia.entities.enemy.type.necromancer;

import doctrina.MovableEntity;
import utopia.entities.enemy.type.necromancer.FireSpell;
import utopia.spell.SpellLoader;

public class FireSpellLoader extends SpellLoader {

    public FireSpellLoader(MovableEntity entity) {
        super(entity);
    }

    @Override
    protected void setupSpells() {
        for (int i = 0; i < SPELLS_COUNT; i++) {
            spells.add(new FireSpell());
        }
    }
}
