package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "character")
public class Character implements Serializable {

    @DatabaseField(generatedId = true)
    private int characterId;
    @DatabaseField
    private String characterName;
    @DatabaseField
    private String characterImagePath;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Game game;
    @DatabaseField
    private int clicks;
    @DatabaseField
    private long lastClick;
    @ForeignCollectionField
    private ForeignCollection<Attribute> characterAttributes;
    @ForeignCollectionField
    private ForeignCollection<Move> characterMoves;
    @ForeignCollectionField
    private ForeignCollection<PersonalCharacterNote> characterPersonalNotes;
    @ForeignCollectionField
    private ForeignCollection<Matchup> characterMatchups;
    @ForeignCollectionField
    private ForeignCollection<PublicCharacterNote> characterPublicNotes;

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public long getLastClick() {
        return lastClick;
    }

    public void setLastClick(long lastClick) {
        this.lastClick = lastClick;
    }

    public ForeignCollection<PublicCharacterNote> getCharacterPublicNotes() {
        return characterPublicNotes;
    }

    public void setCharacterPublicNotes(ForeignCollection<PublicCharacterNote> characterPublicNotes) {
        this.characterPublicNotes = characterPublicNotes;
    }

    public ForeignCollection<Matchup> getCharacterMatchups() {
        return characterMatchups;
    }

    public void setCharacterMatchups(ForeignCollection<Matchup> characterMatchups) {
        this.characterMatchups = characterMatchups;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterImagePath() {
        return characterImagePath;
    }

    public void setCharacterImagePath(String characterImagePath) {
        this.characterImagePath = characterImagePath;
    }

    public ForeignCollection<Attribute> getCharacterAttributes() {
        return characterAttributes;
    }

    public void setCharacterAttributes(ForeignCollection<Attribute> characterAttributes) {
        this.characterAttributes = characterAttributes;
    }

    public ForeignCollection<Move> getCharacterMoves() {
        return characterMoves;
    }

    public void setCharacterMoves(ForeignCollection<Move> characterMoves) {
        this.characterMoves = characterMoves;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ForeignCollection<PersonalCharacterNote> getCharacterPersonalNotes() {
        return characterPersonalNotes;
    }

    public void setCharacterPersonalNotes(ForeignCollection<PersonalCharacterNote> characterPersonalNotes) {
        this.characterPersonalNotes = characterPersonalNotes;
    }
}
