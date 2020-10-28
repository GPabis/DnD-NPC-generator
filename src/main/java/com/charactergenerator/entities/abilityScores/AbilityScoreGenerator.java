package com.charactergenerator.entities.abilityScores;

import lombok.Data;

import java.util.List;
@Data
public class AbilityScoreGenerator {
    public static List<Integer> generateAbilityScores(AbilityGeneratorMethods methods){
        switch (methods){
            case THREE_DICE_SIX:
                return AbilityGeneratorMethods.THREE_DICE_SIX.getAbilityValuesArray();
            case FOUR_DICE_SIX:
                return AbilityGeneratorMethods.FOUR_DICE_SIX.getAbilityValuesArray();
            case FIVE_DICE_SIX:
                return AbilityGeneratorMethods.FIVE_DICE_SIX.getAbilityValuesArray();
            case STANDARD_ARRAY:
                return AbilityGeneratorMethods.STANDARD_ARRAY.getAbilityValuesArray();
            default:
                throw new IllegalArgumentException();
        }
    }
}
