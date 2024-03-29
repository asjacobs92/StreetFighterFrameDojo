package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 1/12/2015.
 */

@DatabaseTable(tableName = "matchupOutcomeVote")
public class MatchupOutcomeVote {
    @DatabaseField(generatedId = true)
    private int matchupOutcomeVoteId;
    @DatabaseField
    private int character1Score;
    @DatabaseField
    private int character2Score;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Matchup matchup;

    public int getMatchupOutcomeVoteId() {
        return matchupOutcomeVoteId;
    }

    public void setMatchupOutcomeVoteId(int matchupOutcomeVoteId) {
        this.matchupOutcomeVoteId = matchupOutcomeVoteId;
    }

    public int getCharacter1Score() {
        return character1Score;
    }

    public void setCharacter1Score(int character1Score) {
        this.character1Score = character1Score;
    }

    public int getCharacter2Score() {
        return character2Score;
    }

    public void setCharacter2Score(int character2Score) {
        this.character2Score = character2Score;
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
}
