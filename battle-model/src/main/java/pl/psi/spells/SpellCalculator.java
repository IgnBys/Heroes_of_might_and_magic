package pl.psi.spells;

import pl.psi.creatures.Creature;
 class SpellCalculator {
    public static void calculateCreatureArmor(final Spell aSpell, final Creature aCreature) {
        aCreature.setCurrentArmor(changeArmor(aCreature.getCurrentArmor(), aSpell.getChangeArmor()),changeArmor(aCreature.getCurrentMagicResistance(), aSpell.getChangeArmor()));

    }

    public static void calculateCreatureAttack(final Spell aSpell, final Creature aCreature) {
        aCreature.setCurrentAttack(changeAttack(aCreature.getCurrentAttack(), aSpell.getChangeAttack()));
    }



    protected static int changeArmor(final int targetArmor, final int changeArmor) {
        int effectiveArmor = targetArmor + changeArmor;
        return Math.max(effectiveArmor, 0);
    }

    protected static int changeAttack(final int targetAttack, final int changeAttack) {
        int effectiveDamage = targetAttack + changeAttack;
        return Math.max(effectiveDamage, 0);
    }
}
