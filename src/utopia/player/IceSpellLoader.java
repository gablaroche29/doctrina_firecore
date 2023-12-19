package utopia.player;

import utopia.spell.SpellLoader;

public class IceSpellLoader extends SpellLoader {

    public IceSpellLoader(Player player) {
        super(player);
    }

    @Override
    protected void setupSpells() {
        for (int i = 0; i < SPELLS_COUNT; i++) {
            spells.add(new IceSpell());
        }
    }
}