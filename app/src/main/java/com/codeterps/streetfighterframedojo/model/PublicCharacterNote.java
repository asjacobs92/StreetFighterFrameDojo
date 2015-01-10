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

    @ForeignCollectionField
    private ForeignCollection<CharacterNoteVote> publicCharacterNoteVotes;
    
}
