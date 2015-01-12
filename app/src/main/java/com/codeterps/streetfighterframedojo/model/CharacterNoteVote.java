package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

import java.lang.*;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class CharacterNoteVote {
    @DatabaseField(generatedId = true)
    private int CharacterNoteVoteId;
    @DatabaseField
    private boolean CharacterNoteVoteLike;
    @DatabaseField
    private User user;
    @DatabaseField
    private PublicCharacterNote voteNote;

    public int getCharacterNoteVoteId() {
        return CharacterNoteVoteId;
    }

    public void setCharacterNoteVoteId(int characterNoteVoteId) {
        CharacterNoteVoteId = characterNoteVoteId;
    }

    public boolean isCharacterNoteVoteLike() {
        return CharacterNoteVoteLike;
    }

    public void setCharacterNoteVoteLike(boolean characterNoteVoteLike) {
        CharacterNoteVoteLike = characterNoteVoteLike;
    }

    public PublicCharacterNote getVoteNote() {
        return voteNote;
    }

    public void setVoteNote(PublicCharacterNote voteNote) {
        this.voteNote = voteNote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
