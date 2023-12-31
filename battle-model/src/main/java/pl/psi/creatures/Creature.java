package pl.psi.creatures;//  ******************************************************************

//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import pl.psi.spells.*;
import lombok.Setter;
import pl.psi.TurnQueue;

import com.google.common.collect.Range;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature implements PropertyChangeListener {
    private CreatureStatisticIf stats;
    @Setter
    private int currentArmor;
    private int currentAttack;
    private int currentMagicResistance;
    private int amount;
    private int currentHp;
    private int counterAttackCounter = 1;
    private DamageCalculatorIf calculator;



    Creature() {
    }

    private Creature(final CreatureStatisticIf aStats, final DamageCalculatorIf aCalculator,
                     final int aAmount) {
        stats = aStats;
        currentArmor = stats.getBasicArmor();
        currentAttack = stats.getBasicAttack();
        currentMagicResistance = stats.getBasicMagicResistance();
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
    }

    public void attack(final Creature aDefender, final Spell aSpell) {
        if (isAlive()) {
//            applySpellChange();
            final int damage = getCalculator().calculateDamage(this,aSpell, aDefender);
            applyDamage(aDefender, damage);
            if (canCounterAttack(aDefender)) {
                counterAttack(aDefender);
            }
        }
    }


    public boolean isAlive() {
        return getAmount() > 0;
    }

    private void applyDamage(final Creature aDefender, final int aDamage) {
        int hpToSubstract = aDamage % aDefender.getMaxHp();
        int amountToSubstract = Math.round(aDamage / aDefender.getMaxHp());

        int hp = aDefender.getCurrentHp() - hpToSubstract;
        if (hp <= 0) {
            aDefender.setCurrentHp(aDefender.getMaxHp() - hp);
            aDefender.setAmount(aDefender.getAmount() - 1);
        }
        else{
            aDefender.setCurrentHp(hp);
        }
        aDefender.setAmount(aDefender.getAmount() - amountToSubstract);
    }

    public int getMaxHp() {
        return stats.getMaxHp();
    }
    public void setCurrentAttack(final int aCurrentAttack) {
        currentAttack = aCurrentAttack;
    }

    public void setCurrentArmor(final int aCurrentArmor, final int aCurrentMagicResistance) {
        currentArmor = aCurrentArmor;
        currentMagicResistance = aCurrentMagicResistance;
    }

    public void useCreatureCalculator(final Creature aCreature, final Spell aSpell){
        getCalculator().calculateCreatureAttack(aSpell, aCreature);
        getCalculator().calculateCreatureArmor(aSpell, aCreature);
    }
//    protected void setCurrentMagicResistance(final int aCurrentMagicResistance) {
//        currentMagicResistance = aCurrentMagicResistance;
//    }

    protected void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }
    public void setAmount(final int aAmount) {
        amount = aAmount;
    }
    private boolean canCounterAttack(final Creature aDefender) {
        return aDefender.getCounterAttackCounter() > 0 && aDefender.getCurrentHp() > 0;
    }

    private void counterAttack(final Creature aAttacker) {
        final int damage = aAttacker.getCalculator()
                .calculateDamage(aAttacker, null, this);
        applyDamage(this, damage);
        aAttacker.counterAttackCounter--;
    }

    Range<Integer> getDamage() {
        return stats.getDamage();
    }

    int getAttack() {
        return stats.getBasicAttack();
    }

    int getArmor() {
        return stats.getBasicArmor();
    }

    int getMagicResistance(){
        return stats.getBasicMagicResistance();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            counterAttackCounter = 1;
        }
    }

    protected void restoreCurrentHpToMax() {
        currentHp = stats.getMaxHp();
    }

    public String getName() {
        return stats.getName();
    }

    public int getMoveRange() {
        return stats.getMoveRange();
    }

    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public Creature build() {
            return new Creature(statistic, calculator, amount);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
