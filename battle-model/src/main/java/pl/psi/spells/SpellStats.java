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
    private final int buffAttack;
    private final int buffDefence;
    private final int debuffAttack;
    private final int debuffDefence;
    private final String description;
}