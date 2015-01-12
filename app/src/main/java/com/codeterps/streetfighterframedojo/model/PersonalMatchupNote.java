package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class PersonalMatchupNote {
    @DatabaseField(generatedId = true)
    private int personalMatchupNoteId;
    @DatabaseField
    private Matchup matchup;
    @DatabaseField
    private String content;

    public int getPersonalMatchupNoteId() {
        return personalMatchupNoteId;
    }

    public void setPersonalMatchupNoteId(int personalMatchupNoteId) {
        this.personalMatchupNoteId = personalMatchupNoteId;
    }

    public Matchup getMatchup() {
        return matchup;
    }

    public void setMatchup(Matchup matchup) {
        this.matchup = matchup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
