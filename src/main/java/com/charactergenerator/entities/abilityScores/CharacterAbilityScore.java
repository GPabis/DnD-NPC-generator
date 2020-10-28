package com.charactergenerator.entities.abilityScores;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static com.charactergenerator.entities.abilityScores.AbilityScoreGenerator.generateAbilityScores;

@Data
public class CharacterAbilityScore {
    private List<AbilityScore> characterAbilityScore;

    public CharacterAbilityScore(AbilityGeneratorMethods method){
        this.characterAbilityScore = setCharacterAbilityScore(method);
    }

    public List<AbilityScore> setCharacterAbilityScore(AbilityGeneratorMethods method) {
        List<AbilityScore> characterAbilityScore = new ArrayList<>();
        List<Integer> abilityValues = generateAbilityScores(method);
        if (abilityValues.size() == 6){
            addStatisticsToList(characterAbilityScore);
            IntStream.range(0, characterAbilityScore.size())
                    .forEach(index -> characterAbilityScore.get(index).setAbilityScore(abilityValues.get(index)));
            return characterAbilityScore;
        } else throw new NullPointerException();
    }

    private void addStatisticsToList(List<AbilityScore> abilityScoreList){
        abilityScoreList.add(new Strength());
        abilityScoreList.add(new Dexterity());
        abilityScoreList.add(new Constitution());
        abilityScoreList.add(new Intelligence());
        abilityScoreList.add(new Wisdom());
        abilityScoreList.add(new Charisma());
    }

    public AbilityScore getStrength(){
        return characterAbilityScore.get(0);
    }

    public AbilityScore getDexterity(){
        return characterAbilityScore.get(1);
    }

    public AbilityScore getConstitution(){
        return characterAbilityScore.get(2);
    }

    public AbilityScore getIntelligence(){
        return characterAbilityScore.get(3);
    }

    public AbilityScore getWisdom(){
        return characterAbilityScore.get(4);
    }

    public AbilityScore getCharisma(){
        return characterAbilityScore.get(5);
    }



}
