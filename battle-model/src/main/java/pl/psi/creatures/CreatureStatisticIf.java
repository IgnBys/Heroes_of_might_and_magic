package pl.psi.creatures;

import com.google.common.collect.Range;

public interface CreatureStatisticIf {
    String getName();
    int getBasicAttack();
    int getBasicArmor();
    int getBasicMagicResistance();
    int getMaxHp();
    int getMoveRange();
    Range< Integer > getDamage();
    int getTier();
    String getDescription();
    boolean isUpgraded();

}
