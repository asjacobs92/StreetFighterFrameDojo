package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class MatchupNoteVote {
    @DatabaseField(generatedId = true)
    private int matchupNoteVoteId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private PublicMatchupNote publicMatchupNote;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
    @DatabaseField
    private boolean characterNoteVoteLike;

    public int getMatchupNoteVoteId() {
        return matchupNoteVoteId;
    }

    public void setMatchupNoteVoteId(int matchupNoteVoteId) {
        this.matchupNoteVoteId = matchupNoteVoteId;
    }

    public PublicMatchupNote getPublicMatchupNote() {
        return publicMatchupNote;
    }

    public void setPublicMatchupNote(PublicMatchupNote publicMatchupNote) {
        this.publicMatchupNote = publicMatchupNote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCharacterNoteVoteLike() {
        return characterNoteVoteLike;
    }

    public void setCharacterNoteVoteLike(boolean characterNoteVoteLike) {
        this.characterNoteVoteLike = characterNoteVoteLike;
    }
}
