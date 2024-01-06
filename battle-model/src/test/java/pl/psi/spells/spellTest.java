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
    void creatureShouldAttackWithSpellProperly() {
        // given
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(50)
                        .basicArmor(0)
                        .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(0)
                        .basicMagicResistance(10)
                        .build())
                .build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder().attack(50).build()).build();
        // when
        angel.attack(dragon, AVADAKEDAVRA);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(70);
    }

    @Test
    void creatureShouldTakeSomeChangesFromSpell() {
        // given
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(10)
                        .basicMagicResistance(10).basicArmor(10)
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
    void creatureShouldAttackWithSpellAndOpponentShouldCounterAttackProperly() {
        // given
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(50)
                        .basicArmor(10)
                        .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .basicAttack(50)
                        .basicMagicResistance(10)
                        .build())
                .build();
        final Spell AVADAKEDAVRA = new Spell.Builder().statistic(SpellStats.builder().attack(50).build()).build();
        // when
        angel.attack(dragon, AVADAKEDAVRA);
        // then
        assertThat(angel.getCurrentHp()).isEqualTo(70);
    }
}
