package pl.psi.spells;

import com.google.common.collect.Range;

public interface SpellStatisticIf {
    String getName();
    int getAttack();
    int getBuffAttack();
    int getBuffDefence();
    int getDebuffAttack();
    int getDebuffDefence();

}
