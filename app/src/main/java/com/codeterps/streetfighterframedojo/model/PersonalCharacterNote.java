package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "personalCharacterNote")
public class PersonalCharacterNote {
    @DatabaseField(generatedId = true)
    private int personalCharacterNoteId;
    @DatabaseField
    private String personalCharacterNoteContent;
    @DatabaseField
    private Character character;

    public int getPersonalCharacterNoteId() {
        return personalCharacterNoteId;
    }

    public void setPersonalCharacterNoteId(int personalCharacterNoteId) {
        this.personalCharacterNoteId = personalCharacterNoteId;
    }

    public String getPersonalCharacterNoteContent() {
        return personalCharacterNoteContent;
    }

    public void setPersonalCharacterNoteContent(String personalCharacterNoteContent) {
        this.personalCharacterNoteContent = personalCharacterNoteContent;
    }
}
