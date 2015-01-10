package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */
public class FrameData {
    @DatabaseField(generatedId = true)
    private int frameDataId;
    @DatabaseField
    private String frameDataName;
    @DatabaseField
    private String frameDataValue;

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
}
