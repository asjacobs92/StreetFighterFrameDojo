package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class CharacterNoteVote {
    @DatabaseField(generatedId = true)
    private int CharacterNoteVoteId;
    @DatabaseField
    private boolean CharacterNoteVoteLike;

}
