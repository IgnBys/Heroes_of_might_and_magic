package pl.psi.creatures;

import java.util.Random;
import pl.psi.spells.*;
abstract class AbstractCalculateDamageStrategy implements DamageCalculatorIf
{

    public static final int MAX_ATTACK_DIFF = 60;
    public static final int MAX_DEFENCE_DIFF = 12;
    public static final double DEFENCE_BONUS = 0.025;
    public static final double ATTACK_BONUS = 0.05;
    private final Random rand;

    protected AbstractCalculateDamageStrategy( final Random aRand )
    {
        rand = aRand;
    }


    public void calculateCreatureArmor(final Spell aSpell, final Creature aCreature){
        aCreature.setCurrentArmor(getChangeArmor(aCreature.getCurrentArmor(), aSpell.getChangeArmor()));
    }
    public void calculateCreatureAttack(final Spell aSpell, final Creature aCreature){
        aCreature.setCurrentAttack(getChangeAttack(aCreature.getCurrentAttack(), aSpell.getChangeAttack()));
    }

    @Override
    public int calculateDamage( final Creature aAttacker,final Spell aSpell, final Creature aDefender )
    {
        final int armor, attack;
        if (aSpell==null){
            armor = getArmor( aDefender);
            attack = getAttack (aAttacker);
        }
        else {
            armor = getMagicResistance(aDefender);
            attack = getMagicAttack (aSpell);
        }

        final int randValue = rand.nextInt( aAttacker.getDamage()
            .upperEndpoint()
            - aAttacker.getDamage()
                .lowerEndpoint()
            + 1 ) + aAttacker.getDamage()
                .lowerEndpoint();

        double oneCreatureDamageToDeal;
        //TODO: make spell cast and fisical damage when cheking difference between attack and armor
        if( attack >= armor )
        {
            int attackPoints = attack - armor;
            if( attackPoints > MAX_ATTACK_DIFF )
            {
                attackPoints = MAX_ATTACK_DIFF;
            }
            oneCreatureDamageToDeal = randValue * (1 + attackPoints * ATTACK_BONUS);
        }
        else
        {
            int defencePoints = armor - attack;
            if( defencePoints > MAX_DEFENCE_DIFF )
            {
                defencePoints = MAX_DEFENCE_DIFF;
            }
            oneCreatureDamageToDeal = randValue * (1 - defencePoints * DEFENCE_BONUS);
        }

        if( oneCreatureDamageToDeal < 0 )
        {
            oneCreatureDamageToDeal = 0;
        }
        return (int)(aAttacker.getAmount() * oneCreatureDamageToDeal);
    }

    protected int getChangeArmor(final int targetArmor, final int changeArmor) {
        int effectiveArmor = targetArmor + changeArmor;
        return Math.max(effectiveArmor, 0);
    }

    protected int getChangeAttack(final int targetAttack, final int changeAttack) {
        int effectiveDamage = targetAttack + changeAttack;
        return Math.max(effectiveDamage, 0);
    }


    protected int getAttack(final Creature aAttacker){
//        return calculateEffectiveDamage(aAttacker.getCurrentAttack(), aSpell.getChangeAttack());
        return aAttacker.getAttack();
    }

    protected int getMagicAttack(final Spell aSpell){

        return aSpell.getAttack();
    }
    protected int getMagicResistance( final Creature aDefender )
    {

        return aDefender.getMagicResistance();
    }

    protected int getArmor( final Creature aDefender )
    {
        return aDefender.getArmor();
    }
}
