package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "game")
public class Game {
    @DatabaseField(generatedId = true)
    private int gameId;
    @DatabaseField
    private String gameName;
    @DatabaseField
    private String gameLogoPath;

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

    public ForeignCollection<Character> getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(ForeignCollection<Character> gameCharacters) {
        this.gameCharacters = gameCharacters;
    }


}
