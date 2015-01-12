package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/12/2015.
 */
public class MatchupOutcomeVote {
    @DatabaseField(generatedId = true)
    private int MatchupOutcomeVoteId;
    @DatabaseField
    private int character1Score;
    @DatabaseField
    private int character2Score;
    @DatabaseField
    private Matchup matchup;

    public int getMatchupOutcomeVoteId() {
        return MatchupOutcomeVoteId;
    }

    public void setMatchupOutcomeVoteId(int matchupOutcomeVoteId) {
        MatchupOutcomeVoteId = matchupOutcomeVoteId;
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

    public Matchup getMatchup() {
        return matchup;
    }

    public void setMatchup(Matchup matchup) {
        this.matchup = matchup;
    }
}
