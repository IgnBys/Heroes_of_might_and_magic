package pl.psi.creatures;

import pl.psi.spells.Spell;

public interface DamageCalculatorIf
{
    int calculateDamage(Creature aAttacker,Creature aDefender );
//    void calculateCreatureArmor(final Creature aCreature);
//    void calculateCreatureAttack(final Creature aCreature);
}
