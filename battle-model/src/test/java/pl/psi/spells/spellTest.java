package pl.psi.spells;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
//import pl.psi.creatures.SelfHealAfterTurnCreature;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class spellTest {

    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    void creatureShouldTakeDamageFromSpell() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(10)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder().attack(50)
                .changeArmor(6).build()).build();
        // when
        AVADAKEDAVRA.cast(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(60);
    }

    @Test
    void checkCreaturesArmorAfterBuff() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(10)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder()
                .changeArmor(6).build()).build();
        // when
        AVADAKEDAVRA.cast(dragon);
        // then
        assertThat(dragon.getCurrentAttack()).isEqualTo(10);
    }

    @Test
    void checkCreaturesAttackAfterBuff() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(5)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder()
                .changeAttack(6).build()).build();
        // when
        AVADAKEDAVRA.cast(dragon);
        // then

        assertThat(dragon.getCurrentAttack()).isEqualTo(11);
    }

    @Test
    void checkCreaturesHpAfterUsingSpellBuff() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(5)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder()
                .changeArmor(6).build()).build();
        final Spell AVADAKEDAVRA2 = new Spell.Builder().statistic(SpellStats.builder().attack(10)
                .build()).build();
        // when
        AVADAKEDAVRA.cast(dragon);
        AVADAKEDAVRA2.cast(dragon);

        // then

        assertThat(dragon.getCurrentHp()).isEqualTo(93);
    }

    @Test
    void attackTwoCreaturesWhereTheFirstHasChangesAndTheSecondHasNot() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(10)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(10)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Spell changeArmor = new Spell.Builder().statistic(SpellStats.builder()
                .changeArmor(20).build()).build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder()
                .attack(10).build()).build();
        // when
        changeArmor.cast(dragon);
        AVADAKEDAVRA.cast(dragon);
        AVADAKEDAVRA.cast(angel);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(94);
        assertThat(angel.getCurrentHp()).isEqualTo(92);

    }


    @Test
    void creatureShouldAttackAnotherWithChangedArmor() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(50)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(50)
                        .basicMagicResistance(20).basicArmor(10)
                        .build())
                .build();
        final Spell changeArmor = new Spell.Builder().statistic(SpellStats.builder()
                .changeArmor(6).build()).build();
//        final Spell changeAttack = new Spell.Builder().statistic(SpellStats.builder()
//                .changeAttack(10).build()).build();
        // when
        changeArmor.cast(dragon);
//        if we want check dragons damage when it counterAttacks
//        changeAttack.cast(dragon);
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(73);
//        if dragon counterAttack angel
//        assertThat(angel.getCurrentHp()).isEqualTo(60);



    }

}
