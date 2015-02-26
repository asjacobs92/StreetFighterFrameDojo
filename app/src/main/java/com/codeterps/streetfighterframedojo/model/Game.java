package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "game")
public class Game implements Serializable {

    @DatabaseField(generatedId = true)
    private int gameId;
    @DatabaseField
    private String gameName;
    @DatabaseField
    private String gameLogoPath;
    @DatabaseField
    private int clicks;
    @DatabaseField
    private long lastClick;

    @ForeignCollectionField
    private ForeignCollection<Character> gameCharacters;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameLogoPath() {
        return gameLogoPath;
    }

    public void setGameLogoPath(String gameLogoPath) {
        this.gameLogoPath = gameLogoPath;
    }

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

    public ForeignCollection<Character> getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(ForeignCollection<Character> gameCharacters) {
        this.gameCharacters = gameCharacters;
    }

}
