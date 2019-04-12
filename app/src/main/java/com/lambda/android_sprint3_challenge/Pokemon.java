package com.lambda.android_sprint3_challenge;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name, spriteUrl, ID, abilities, types;

    public Pokemon(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.spriteUrl = "";

        this.abilities = "";
        this.types = "";
    }

    public Pokemon(String name, String ID, String abilities, String types, String spriteUrl) {
        this.name = name;
        this.ID = ID;
        this.spriteUrl = spriteUrl;

        this.abilities = abilities;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
