package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class Character {
    @DatabaseField(generatedId = true)
    private int characterId;
    @DatabaseField
    private String characterName;

    @ForeignCollectionField
    private ForeignCollection<Attribute> characterAttributes;
    @ForeignCollectionField
    private ForeignCollection<Move> characterMoves;
    @ForeignCollectionField
    private ForeignCollection<PersonalCharacterNote> characterPersonalNotes;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public ForeignCollection<Attribute> getCharacterAttributes() {
        return characterAttributes;
    }

    public void setCharacterAttributes(ForeignCollection<Attribute> characterAttributes) {
        this.characterAttributes = characterAttributes;
    }

    public ForeignCollection<Move> getCharacterMoves() {
        return characterMoves;
    }

    public void setCharacterMoves(ForeignCollection<Move> characterMoves) {
        this.characterMoves = characterMoves;
    }
}
