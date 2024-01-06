package pl.psi.creatures;

import pl.psi.spells.Spell;

public interface DamageCalculatorIf
{
    int calculateDamage(Creature aAttacker, final Spell aSpell, Creature aDefender );
    void calculateCreatureArmor(final Spell aSpell, final Creature aCreature);
    void calculateCreatureAttack(final Spell aSpell, final Creature aCreature);
}
