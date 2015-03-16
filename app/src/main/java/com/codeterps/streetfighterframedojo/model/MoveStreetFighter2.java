package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 3/15/2015.
 */
@DatabaseTable(tableName = "moveStreetFighter2")
public class MoveStreetFighter2 extends Move {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Move move;

    @DatabaseField
    private int stunTimer;
    @DatabaseField
    private int superMeter;
    @DatabaseField
    private int damageVariation;
    @DatabaseField
    private int stunVariation;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getStunTimer() {
        return stunTimer;
    }

    public void setStunTimer(int stunTimer) {
        this.stunTimer = stunTimer;
    }

    public int getSuperMeter() {
        return superMeter;
    }

    public void setSuperMeter(int superMeter) {
        this.superMeter = superMeter;
    }

    public int getDamageVariation() {
        return damageVariation;
    }

    public void setDamageVariation(int damageVariation) {
        this.damageVariation = damageVariation;
    }

    public int getStunVariation() {
        return stunVariation;
    }

    public void setStunVariation(int stunVariation) {
        this.stunVariation = stunVariation;
    }
}
