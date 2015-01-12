package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class Attribute {
    @DatabaseField(generatedId = true)
    private int attributeId;
    @DatabaseField
    private String attributeName;
    @DatabaseField
    private String attributeValue;
    @DatabaseField
    private Character character;

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
