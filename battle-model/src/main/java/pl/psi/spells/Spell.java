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



    public String getName() {
        return stats.getName();
    }

    public int getAttack() {
        return stats.getAttack();
    }
    public int getBuffAttack() {
        return stats.getBuffAttack();
    }
    public int getBuffDefence() {
        return stats.getBuffDefence();
    }
    public int getDebuffAttack() {
        return stats.getDebuffAttack();
    }
    public int getDebuffDefence() {
        return stats.getDebuffDefence();
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