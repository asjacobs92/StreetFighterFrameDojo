package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class PersonalMatchupNote {
    @DatabaseField(generatedId = true)
    private int personalMatchupNoteId;
    @DatabaseField
    private String content;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Matchup matchup;

    public int getPersonalMatchupNoteId() {
        return personalMatchupNoteId;
    }

    public void setPersonalMatchupNoteId(int personalMatchupNoteId) {
        this.personalMatchupNoteId = personalMatchupNoteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
