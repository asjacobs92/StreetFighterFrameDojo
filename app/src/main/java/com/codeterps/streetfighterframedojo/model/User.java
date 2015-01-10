package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
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
}
