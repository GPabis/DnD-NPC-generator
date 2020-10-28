package com.charactergenerator.entities.traitEntity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSONALITY_TRAITS")
public class TraitEntity {
    @Column(name = "ID")
    private @Id @GeneratedValue Long id;
    @Column(name = "TRAIT_NAME")
    private String traitName;
    @Column(name="TRAIT_NAME_ENCODED")
    private String traitNameEncoded;
    @Column(name="TRAIT_TYPE")
    private TraitTypes traitTypes;

    public TraitEntity(){}

    public TraitEntity(String traitName, String traitNameEncoded, TraitTypes traitTypes) {
        this.traitName = traitName;
        this.traitNameEncoded = traitNameEncoded;
        this.traitTypes = traitTypes;
    }
}
