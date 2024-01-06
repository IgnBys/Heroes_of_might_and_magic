package pl.psi.spells;

import com.google.common.collect.Range;

import lombok.Getter;

@Getter
public enum SpellStatistic implements SpellStatisticIf
{
    // NECROPILIS FRACTION
    LIGHTNING_BOLT( "Lightning_Bolt", 40, 0,0,
            "Causes a bolt of lightning to strike the selected unit."),

    BLOODLUST("Bloodlust", 0, 6, 0,
            "Increases the hand-to-hand damage inflicted by the selected unit."),

    STONE_SKIN("Stone_Skin", 0, 0,6,
            "Increases the defense of the selected/all friendly unit." ),

    DISRUPTING_RAY("Disrapting_Ray", 0,0,-5,
            "Reduces the selected enemy unit's defense strength. A single enemy may be targeted multiple times by this spell."),

//    FORTUNE("Fortune", 0,0,0,0,0,"Increases the luck of the selected/all friendly unit."),
//    CURE("Cure", 0,0,10, "Heals."),
//    PRECISION("Precision", 0,6,0,0,0, "Increases the ranged attack of the selected/all friendly unit."),
    SHIELD("Shield", 0, -6,0,
        "Shields a selected unit, reducing the amount of damage received from hand-to-hand attacks.");


    private final String name;
    private final int attack;
    private final int changeAttack;
    private final int changeArmor;
    private final String description;

    SpellStatistic( final String aName, final int aAttack, final int aChangeAttack,
                    final int aChangeArmor, final String aDescription)
    {
        name = aName;
        attack = aAttack;
        changeAttack = aChangeAttack;
        changeArmor = aChangeArmor;
        description = aDescription;
    }


    String getTranslatedName()
    {
        return name;
    }
}
