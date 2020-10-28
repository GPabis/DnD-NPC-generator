package com.charactergenerator.entities.professionEntity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PROFESSION_TABLE")
public class ProfessionEntity {
    @Column(name = "PROFESSION_ID")
    private @Id @GeneratedValue Long professionId;
    @Column(name = "PROFESSION_NAME")
    private String professionName;
    @Column(name = "PROFESSION_NAME_ENCODED")
    private String professionNameEncoded;

    public ProfessionEntity(){ }

    public ProfessionEntity(String professionName, String professionNameEncoded){
        this.professionName = professionName;
        this.professionNameEncoded = professionNameEncoded;
    }
}
