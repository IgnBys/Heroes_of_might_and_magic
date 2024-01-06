package pl.psi;

import java.util.List;

import pl.psi.creatures.Creature;
import pl.psi.spells.Spell;
import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;
    private final List<Spell> spellBook;

    public Hero( final List< Creature > aCreatures, final List<Spell> aSpellBook )
    {
        creatures = aCreatures;
        spellBook = aSpellBook;
    }
}
