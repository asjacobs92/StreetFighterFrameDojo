package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class PublicCharacterNote {
    @DatabaseField(generatedId = true)
    private int publicCharacterNoteId;
    @DatabaseField
    private String publicCharacterNoteContent;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Character character;

    @ForeignCollectionField
    private ForeignCollection<CharacterNoteVote> publicCharacterNoteVotes;

    public int getPublicCharacterNoteId() {
        return publicCharacterNoteId;
    }

    public void setPublicCharacterNoteId(int publicCharacterNoteId) {
        this.publicCharacterNoteId = publicCharacterNoteId;
    }

    public String getPublicCharacterNoteContent() {
        return publicCharacterNoteContent;
    }

    public void setPublicCharacterNoteContent(String publicCharacterNoteContent) {
        this.publicCharacterNoteContent = publicCharacterNoteContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public ForeignCollection<CharacterNoteVote> getPublicCharacterNoteVotes() {
        return publicCharacterNoteVotes;
    }

    public void setPublicCharacterNoteVotes(ForeignCollection<CharacterNoteVote> publicCharacterNoteVotes) {
        this.publicCharacterNoteVotes = publicCharacterNoteVotes;
    }
}
