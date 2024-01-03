package pl.psi.creatures;

import pl.psi.spells.Spell;

public interface DamageCalculatorIf
{
    int calculateDamage(Creature aAttacker, final Spell aSpell, Creature aDefender );
}
