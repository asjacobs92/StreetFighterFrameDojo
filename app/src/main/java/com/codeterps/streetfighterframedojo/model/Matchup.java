package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class Matchup {
    @DatabaseField(generatedId = true)
    private int matchupId;
    @DatabaseField
    private Character character1;
    @DatabaseField
    private Character character2;

    @ForeignCollectionField
    private ForeignCollection<PersonalMatchupNote> personalMatchupNotes;
    @ForeignCollectionField
    private ForeignCollection<PublicMatchupNote> publicMatchupNotes;
    @ForeignCollectionField
    private ForeignCollection<MatchupOutcomeVote> matchupOutcomeVotes;

    public int getMatchupId() {
        return matchupId;
    }

    public void setMatchupId(int matchupId) {
        this.matchupId = matchupId;
    }

    public Character getCharacter1() {
        return character1;
    }

    public void setCharacter1(Character character1) {
        this.character1 = character1;
    }

    public Character getCharacter2() {
        return character2;
    }

    public void setCharacter2(Character character2) {
        this.character2 = character2;
    }

    public ForeignCollection<PersonalMatchupNote> getPersonalMatchupNotes() {
        return personalMatchupNotes;
    }

    public void setPersonalMatchupNotes(ForeignCollection<PersonalMatchupNote> personalMatchupNotes) {
        this.personalMatchupNotes = personalMatchupNotes;
    }

    public ForeignCollection<PublicMatchupNote> getPublicMatchupNotes() {
        return publicMatchupNotes;
    }

    public void setPublicMatchupNotes(ForeignCollection<PublicMatchupNote> publicMatchupNotes) {
        this.publicMatchupNotes = publicMatchupNotes;
    }

    public ForeignCollection<MatchupOutcomeVote> getMatchupOutcomeVotes() {
        return matchupOutcomeVotes;
    }

    public void setMatchupOutcomeVotes(ForeignCollection<MatchupOutcomeVote> matchupOutcomeVotes) {
        this.matchupOutcomeVotes = matchupOutcomeVotes;
    }
}
