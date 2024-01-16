package pl.psi.spells;

import lombok.Getter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import lombok.Setter;
import pl.psi.TurnQueue;

import com.google.common.collect.Range;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.DamageCalculatorIf;
import pl.psi.creatures.DefaultDamageCalculator;

import java.beans.PropertyChangeListener;


@Getter
public class Spell {
    private SpellStatisticIf stats;

    Spell() {

    }

    private Spell(final SpellStatisticIf aStats) {
        stats = aStats;

    }

    public void cast(final Creature aCreature) {
        if (aCreature.isAlive()) {
            aCreature.applySpell(aCreature, this);

        }
    }

    //    public void spellAttack(final Creature aCreature, final Spell aSpell){
//        final int attack = aSpell.getAttack();
//        currentHp = attack * aCreature.getMagicResistance();
//    }

    public void useSpellCalculator(final Creature aCreature, final Spell aSpell){
        SpellCalculator.calculateCreatureAttack(aSpell, aCreature);
        SpellCalculator.calculateCreatureArmor(aSpell, aCreature);
    }
    public String getName() {
        return stats.getName();
    }

    public int getAttack() {
        return stats.getAttack();
    }

    public int getChangeAttack() {
        return stats.getChangeAttack();
    }

    public int getChangeArmor() {
        return stats.getChangeArmor();
    }

    public static class Builder {
        private SpellStatisticIf statistic;


        public Spell.Builder statistic(final SpellStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }



        public Spell build() {
            return new Spell(statistic);
        }
    }
}