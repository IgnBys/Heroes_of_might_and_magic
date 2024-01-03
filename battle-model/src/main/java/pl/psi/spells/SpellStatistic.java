package pl.psi.spells;

import com.google.common.collect.Range;

import lombok.Getter;

@Getter
public enum SpellStatistic implements SpellStatisticIf
{
    // NECROPILIS FRACTION
    MAGIC_ARROW( "Magic_Arrow", 40, 4
//            1,
//            false
    ), //
    BLOODLUST("Bloodlust", 0, 4),
    STONE_SKIN("Stone_Skin", 0, 4);


    private final String name;
    private final int attack;
    private final int manaCost;
//    private final int castRange;
//    private final Range< Integer > damage;
//    private final int tier;
//    private final boolean isUpgraded;

    SpellStatistic( final String aName, final int aAttack, final int aManaCost
//                    final int aCastRange,
//                       final Range< Integer > aDamage,
//                        final int aTier,
//                        final boolean aIsUpgraded
    )
    {
        name = aName;
        attack = aAttack;
        manaCost = aManaCost;
//        castRange = aCastRange;
//        damage = aDamage;
//        tier = aTier;
//        isUpgraded = aIsUpgraded;
    }


    String getTranslatedName()
    {
        return name;
    }
}
