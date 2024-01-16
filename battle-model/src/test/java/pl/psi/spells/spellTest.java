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
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder().attack(10)
                .changeArmor(6).build()).build();
        // when
        AVADAKEDAVRA.cast(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(94);
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
        assertThat(dragon.getCurrentArmor()).isEqualTo(16);
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
                .changeArmor(6).build()).build();
        // when
        AVADAKEDAVRA.cast(dragon);
        // then

        assertThat(dragon.getCurrentMagicResistance()).isEqualTo(26);
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

        assertThat(dragon.getCurrentHp()).isEqualTo(96);
    }

}
