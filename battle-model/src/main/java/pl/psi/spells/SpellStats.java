package pl.psi.spells;

import com.google.common.collect.Range;

import lombok.Builder;
import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Builder
public class SpellStats implements SpellStatisticIf {
    private final String name;
    private final int attack;
    private final int changeAttack;
    private final int changeArmor;
    private final String description;
}