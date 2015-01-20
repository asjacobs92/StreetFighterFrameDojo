package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(generatedId = true)
    private int userId;
    @DatabaseField
    private int userName;

    @ForeignCollectionField
    private ForeignCollection<PublicCharacterNote> userPublicCharacterNotes;
    @ForeignCollectionField
    private ForeignCollection<PersonalCharacterNote> userPersonalCharacterNotes;
    @ForeignCollectionField
    private ForeignCollection<PublicMatchupNote> userPublicMatchupNotes;
    @ForeignCollectionField
    private ForeignCollection<PersonalMatchupNote> userPersonalMatchupNotes;
    @ForeignCollectionField
    private ForeignCollection<CharacterNoteVote> userCharacterNoteVotes;
    @ForeignCollectionField
    private ForeignCollection<MatchupNoteVote> userMatchupNoteVotes;
    @ForeignCollectionField
    private ForeignCollection<MatchupOutcomeVote> userMatchupOutcomeVotes;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public ForeignCollection<PublicCharacterNote> getUserPublicCharacterNotes() {
        return userPublicCharacterNotes;
    }

    public void setUserPublicCharacterNotes(ForeignCollection<PublicCharacterNote> userPublicCharacterNotes) {
        this.userPublicCharacterNotes = userPublicCharacterNotes;
    }

    public ForeignCollection<PersonalCharacterNote> getUserPersonalCharacterNotes() {
        return userPersonalCharacterNotes;
    }

    public void setUserPersonalCharacterNotes(ForeignCollection<PersonalCharacterNote> userPersonalCharacterNotes) {
        this.userPersonalCharacterNotes = userPersonalCharacterNotes;
    }

    public ForeignCollection<PublicMatchupNote> getUserPublicMatchupNotes() {
        return userPublicMatchupNotes;
    }

    public void setUserPublicMatchupNotes(ForeignCollection<PublicMatchupNote> userPublicMatchupNotes) {
        this.userPublicMatchupNotes = userPublicMatchupNotes;
    }

    public ForeignCollection<PersonalMatchupNote> getUserPersonalMatchupNotes() {
        return userPersonalMatchupNotes;
    }

    public void setUserPersonalMatchupNotes(ForeignCollection<PersonalMatchupNote> userPersonalMatchupNotes) {
        this.userPersonalMatchupNotes = userPersonalMatchupNotes;
    }

    public ForeignCollection<CharacterNoteVote> getUserCharacterNoteVotes() {
        return userCharacterNoteVotes;
    }

    public void setUserCharacterNoteVotes(ForeignCollection<CharacterNoteVote> userCharacterNoteVotes) {
        this.userCharacterNoteVotes = userCharacterNoteVotes;
    }

    public ForeignCollection<MatchupNoteVote> getUserMatchupNoteVotes() {
        return userMatchupNoteVotes;
    }

    public void setUserMatchupNoteVotes(ForeignCollection<MatchupNoteVote> userMatchupNoteVotes) {
        this.userMatchupNoteVotes = userMatchupNoteVotes;
    }

    public ForeignCollection<MatchupOutcomeVote> getUserMatchupOutcomeVotes() {
        return userMatchupOutcomeVotes;
    }

    public void setUserMatchupOutcomeVotes(ForeignCollection<MatchupOutcomeVote> userMatchupOutcomeVotes) {
        this.userMatchupOutcomeVotes = userMatchupOutcomeVotes;
    }
}
