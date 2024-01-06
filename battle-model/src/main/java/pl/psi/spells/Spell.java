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
            applySpell(aCreature, stats);
        }
    }

    public void applySpell(final Creature aCreature, final SpellStatisticIf stats) {
        if (stats.getAttack() != 0) {
            aCreature.attack(aCreature, this);
        }
        else {
            aCreature.useCreatureCalculator(aCreature,this);
//            aCreature.setCurrentArmor(aCreature.getCurrentArmor(),);
        }
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