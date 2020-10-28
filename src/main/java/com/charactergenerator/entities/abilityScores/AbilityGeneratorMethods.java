package com.charactergenerator.entities.abilityScores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public enum AbilityGeneratorMethods {
    THREE_DICE_SIX(3), FOUR_DICE_SIX(4), FIVE_DICE_SIX(5), STANDARD_ARRAY(new int[]{8, 10, 12, 13,14,15});

    List<Integer> abilityValuesArray;


    AbilityGeneratorMethods(int dice){
        this.abilityValuesArray = rollAbilityScores(dice);
    }
    AbilityGeneratorMethods(int[] array){
        this.abilityValuesArray = randomStandardArray(array);
    }

    private List<Integer> rollAbilityScores(int dice){
        List<Integer> array = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            array.add(rollAbilityScore(dice));
        }
        return array;
    }

    private int rollAbilityScore(int dice){
        int[] array = new int[dice];
        for(int i = 0; i<dice; i++){
            array[i] = ThreadLocalRandom.current().nextInt(1, 7);
        }
        Arrays.sort(array);
        return Arrays.stream(Arrays.copyOfRange(array, dice-3, array.length)).sum();
    }

    private List<Integer> randomStandardArray(int[] array){
        List<Integer> standardArray = new ArrayList<>();
        IntStream.of(array).forEach(standardArray::add);
        Collections.shuffle(standardArray);
        return standardArray;
    }

    public List<Integer> getAbilityValuesArray() {
        return abilityValuesArray;
    }
}
