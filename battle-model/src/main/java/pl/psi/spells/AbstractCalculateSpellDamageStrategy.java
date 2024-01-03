//package pl.psi.spells;
//
//import pl.psi.creatures.*;
//import pl.psi.creatures.Creature;
//import pl.psi.creatures.DamageCalculatorIf;
//
//import java.util.Random;
//
//abstract class AbstractCalculateSpellDamageStrategy implements SpellDamageCalculatorIf
//{
//
//    public static final int MAX_ATTACK_DIFF = 60;
//    public static final int MAX_DEFENCE_DIFF = 12;
//    public static final double DEFENCE_BONUS = 0.025;
//    public static final double ATTACK_BONUS = 0.05;
//
//    @Override
//    public int calculateDamage(final Creature aAttacker, final Spell aSpell, final Creature aDefender )
//    {
//        final int magicResist = getMagicResistance( aDefender );
//
//
//
//        double oneCreatureDamageToDeal;
//        if( aSpell.getStats().getAttack() >= magicResist )
//        {
//            int attackPoints = aSpell.getStats().getAttack() - magicResist;
//            if( attackPoints > MAX_ATTACK_DIFF )
//            {
//                attackPoints = MAX_ATTACK_DIFF;
//            }
//            oneCreatureDamageToDeal = randValue * (1 + attackPoints * ATTACK_BONUS);
//        }
//        else
//        {
//            int defencePoints = magicResist - aSpell.getStats().getAttack();
//            if( defencePoints > MAX_DEFENCE_DIFF )
//            {
//                defencePoints = MAX_DEFENCE_DIFF;
//            }
//            oneCreatureDamageToDeal = randValue * (1 - defencePoints * DEFENCE_BONUS);
//        }
//
//        if( oneCreatureDamageToDeal < 0 )
//        {
//            oneCreatureDamageToDeal = 0;
//        }
//        return (int)(aAttacker.getAmount() * oneCreatureDamageToDeal);
//    }
//
//    protected int getMagicResistance( final Creature aDefender )
//    {
//        return aDefender.getStats().getMagicResistance();
//    }
//}
//
