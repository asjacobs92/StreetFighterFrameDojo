package com.codeterps.streetfighterframedojo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by FelipeIsrael on 1/9/2015.
 */

@DatabaseTable(tableName = "move")
public class Move implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Character character;

    @DatabaseField
    private String name;
    @DatabaseField
    private String nickname;
    @DatabaseField
    private String moveInput;
    @DatabaseField
    private String type;

    @DatabaseField
    private int damage;
    @DatabaseField
    private int stun;
    @DatabaseField
    private String hitLevel;
    @DatabaseField
    private String cancelAbility;
    @DatabaseField
    private int startup;
    @DatabaseField
    private int active;
    @DatabaseField
    private int recovery;
    @DatabaseField
    private int totalFrames;
    @DatabaseField
    private int advantageOnGuard;
    @DatabaseField
    private int advantageOnHit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMoveInput() {
        return moveInput;
    }

    public void setMoveInput(String moveInput) {
        this.moveInput = moveInput;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public String getHitLevel() {
        return hitLevel;
    }

    public void setHitLevel(String hitLevel) {
        this.hitLevel = hitLevel;
    }

    public String getCancelAbility() {
        return cancelAbility;
    }

    public void setCancelAbility(String cancelAbility) {
        this.cancelAbility = cancelAbility;
    }

    public int getStartup() {
        return startup;
    }

    public void setStartup(int startup) {
        this.startup = startup;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRecovery() {
        return recovery;
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public int getAdvantageOnGuard() {
        return advantageOnGuard;
    }

    public void setAdvantageOnGuard(int advantageOnGuard) {
        this.advantageOnGuard = advantageOnGuard;
    }

    public int getAdvantageOnHit() {
        return advantageOnHit;
    }

    public void setAdvantageOnHit(int advantageOnHit) {
        this.advantageOnHit = advantageOnHit;
    }
}
