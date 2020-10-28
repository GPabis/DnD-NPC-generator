package com.charactergenerator.entities.abilityScores;

import lombok.Data;

@Data
public abstract class AbilityScore {
    private int abilityScore;
    private int abilityScoreBonus;

    public AbilityScore(){}

    public AbilityScore(int abilityScore){
        setAbilityScore(abilityScore);
    }

    private void setAbilityScoreBonus() {
       this.abilityScoreBonus = (int) Math.floor(((double) getAbilityScore() - 10) / 2);
    }

    public void setAbilityScore(int abilityScore) {
        this.abilityScore = abilityScore;
        setAbilityScoreBonus();
    }
}
