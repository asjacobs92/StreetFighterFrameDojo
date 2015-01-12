package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class Move {
    @DatabaseField(generatedId = true)
    private int moveId;
    @DatabaseField
    private String moveName;
    @DatabaseField
    private String moveInput;
    @DatabaseField
    private Character character;

    @ForeignCollectionField
    private ForeignCollection<FrameData> moveFrameData;


    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public String getMoveInput() {
        return moveInput;
    }

    public void setMoveInput(String moveInput) {
        this.moveInput = moveInput;
    }

    public ForeignCollection<FrameData> getMoveFrameData() {
        return moveFrameData;
    }

    public void setMoveFrameData(ForeignCollection<FrameData> moveFrameData) {
        this.moveFrameData = moveFrameData;
    }
}
