package com.charactergenerator.entities.actionEntity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACTIONS_TABLE")
public class ActionEntity {
    @Column(name = "ACTION_ID")
    private @Id @GeneratedValue Long id;
    @Column(name = "ACTION_NAME")
    private String actionName;
    @Column(name = "ACTION_NAME_ENCODED")
    private String actionNameEncoded;

    public ActionEntity(){}

    public ActionEntity(String actionName, String actionNameEncoded){
        this.actionName = actionName;
        this.actionNameEncoded = actionNameEncoded;
    }
}
