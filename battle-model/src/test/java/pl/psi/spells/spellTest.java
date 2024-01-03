//package pl.psi.spells;
//
//import com.google.common.collect.Range;
//import org.junit.jupiter.api.Test;
//import pl.psi.TurnQueue;
//import pl.psi.creatures.Creature;
//import pl.psi.creatures.CreatureStats;
////import pl.psi.creatures.SelfHealAfterTurnCreature;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
// */
//public class spellTest {
//
//    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);
//
//    @Test
//    void creatureShouldAttackProperly() {
//        // given
//        final Spell magicArrow = new Spell.Builder().statistic(SpellStats.builder()
//                        .maxHp(100)
//                        .damage(Range.closed(10, 10))
//                        .attack(50)
//                        .armor(0)
//                        .build())
//                .build();
//        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
//                        .maxHp(100)
//                        .damage(NOT_IMPORTANT_DMG)
//                        .attack(0)
//                        .armor(10)
//                        .build())
//                .build();
//        // when
//        angel.attack(dragon);
//        // then
//        assertThat(dragon.getCurrentHp()).isEqualTo(70);
//    }
//}
