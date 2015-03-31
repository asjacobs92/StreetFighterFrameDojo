package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FelipeIsrael on 3/11/2015.
 */

@DatabaseTable(tableName = "moveStreetFighter4")
public class MoveStreetFighter4 {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Move move;

    @DatabaseField
    private String meterGain;
    @DatabaseField
    private int blockStun;
    @DatabaseField
    private int hitStun;
    @DatabaseField
    private boolean canArmorBreak;
    @DatabaseField
    private boolean isProjectile;
    @DatabaseField
    private boolean isThrow;
    @DatabaseField
    private String onHitGround;
    @DatabaseField
    private String onCounterHitGround;
    @DatabaseField
    private String onHitAir;
    @DatabaseField
    private String onCounterHitAir;
    @DatabaseField
    private boolean hasArmor;
    @DatabaseField
    private String fullInvincible;
    @DatabaseField
    private String strikeInvincible;
    @DatabaseField
    private String projectileInvincible;
    @DatabaseField
    private String throwInvincible;
    @DatabaseField
    private String airborne;
    @DatabaseField
    private String juggleInfo;

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

    public String getMeterGain() {
        return meterGain;
    }

    public void setMeterGain(String meterGain) {
        this.meterGain = meterGain;
    }

    public int getBlockStun() {
        return blockStun;
    }

    public void setBlockStun(int blockStun) {
        this.blockStun = blockStun;
    }

    public int getHitStun() {
        return hitStun;
    }

    public void setHitStun(int hitStun) {
        this.hitStun = hitStun;
    }

    public boolean isCanArmorBreak() {
        return canArmorBreak;
    }

    public void setCanArmorBreak(boolean canArmorBreak) {
        this.canArmorBreak = canArmorBreak;
    }

    public boolean isProjectile() {
        return isProjectile;
    }

    public void setProjectile(boolean isProjectile) {
        this.isProjectile = isProjectile;
    }

    public boolean isThrow() {
        return isThrow;
    }

    public void setThrow(boolean isThrow) {
        this.isThrow = isThrow;
    }

    public String getOnHitGround() {
        return onHitGround;
    }

    public void setOnHitGround(String onHitGround) {
        this.onHitGround = onHitGround;
    }

    public String getOnCounterHitGround() {
        return onCounterHitGround;
    }

    public void setOnCounterHitGround(String onCounterHitGround) {
        this.onCounterHitGround = onCounterHitGround;
    }

    public String getOnHitAir() {
        return onHitAir;
    }

    public void setOnHitAir(String onHitAir) {
        this.onHitAir = onHitAir;
    }

    public String getOnCounterHitAir() {
        return onCounterHitAir;
    }

    public void setOnCounterHitAir(String onCounterHitAir) {
        this.onCounterHitAir = onCounterHitAir;
    }

    public boolean isHasArmor() {
        return hasArmor;
    }

    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    public String getFullInvincible() {
        return fullInvincible;
    }

    public void setFullInvincible(String fullInvincible) {
        this.fullInvincible = fullInvincible;
    }

    public String getStrikeInvincible() {
        return strikeInvincible;
    }

    public void setStrikeInvincible(String strikeInvincible) {
        this.strikeInvincible = strikeInvincible;
    }

    public String getProjectileInvincible() {
        return projectileInvincible;
    }

    public void setProjectileInvincible(String projectileInvincible) {
        this.projectileInvincible = projectileInvincible;
    }

    public String getThrowInvincible() {
        return throwInvincible;
    }

    public void setThrowInvincible(String throwInvincible) {
        this.throwInvincible = throwInvincible;
    }

    public String getAirborne() {
        return airborne;
    }

    public void setAirborne(String airborne) {
        this.airborne = airborne;
    }

    public String getJuggleInfo() {
        return juggleInfo;
    }

    public void setJuggleInfo(String juggleInfo) {
        this.juggleInfo = juggleInfo;
    }
}
