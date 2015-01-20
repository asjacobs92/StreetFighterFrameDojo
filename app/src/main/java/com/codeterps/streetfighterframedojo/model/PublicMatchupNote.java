package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "publicMatchupNote")
public class PublicMatchupNote {
    @DatabaseField(generatedId = true)
    private int publicMatchupNoteId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Matchup matchup;

    @ForeignCollectionField
    private ForeignCollection<MatchupNoteVote> matchupNoteVotes;

    @DatabaseField
    private String content;
}
