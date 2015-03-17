package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 3/15/2015.
 */

@DatabaseTable(tableName = "moveStreetFighter3")
public class MoveStreetFighter3 {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Move move;

    @DatabaseField
    private int crouchingHitAdvantage;
    @DatabaseField
    private String parry;
    @DatabaseField
    private int karaRange;
    @DatabaseField
    private int throwRange;
    @DatabaseField
    private int barGainAtt;
    @DatabaseField
    private int barGainOpp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getCrouchingHitAdvantage() {
        return crouchingHitAdvantage;
    }

    public void setCrouchingHitAdvantage(int crouchingHitAdvantage) {
        this.crouchingHitAdvantage = crouchingHitAdvantage;
    }

    public String getParry() {
        return parry;
    }

    public void setParry(String parry) {
        this.parry = parry;
    }

    public int getKaraRange() {
        return karaRange;
    }

    public void setKaraRange(int karaRange) {
        this.karaRange = karaRange;
    }

    public int getThrowRange() {
        return throwRange;
    }

    public void setThrowRange(int throwRange) {
        this.throwRange = throwRange;
    }

    public int getBarGainAtt() {
        return barGainAtt;
    }

    public void setBarGainAtt(int barGainAtt) {
        this.barGainAtt = barGainAtt;
    }

    public int getBarGainOpp() {
        return barGainOpp;
    }

    public void setBarGainOpp(int barGainOpp) {
        this.barGainOpp = barGainOpp;
    }
}
