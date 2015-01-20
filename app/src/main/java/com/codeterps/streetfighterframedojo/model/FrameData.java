package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "frameData")
public class FrameData {
    @DatabaseField(generatedId = true)
    private int frameDataId;
    @DatabaseField
    private String frameDataName;
    @DatabaseField
    private String frameDataValue;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Move move;

    public int getFrameDataId() {
        return frameDataId;
    }

    public void setFrameDataId(int frameDataId) {
        this.frameDataId = frameDataId;
    }

    public String getFrameDataName() {
        return frameDataName;
    }

    public void setFrameDataName(String frameDataName) {
        this.frameDataName = frameDataName;
    }

    public String getFrameDataValue() {
        return frameDataValue;
    }

    public void setFrameDataValue(String frameDataValue) {
        this.frameDataValue = frameDataValue;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
