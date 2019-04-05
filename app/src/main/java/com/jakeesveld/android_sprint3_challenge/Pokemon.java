package com.jakeesveld.android_sprint3_challenge;

public class Pokemon {

    private String name, spriteUrl, id;
    private String[] abilities, types;

    public Pokemon(String name, String spriteUrl, String id, String[] abilities, String[] types) {
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.id = id;
        this.abilities = abilities;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public String getId() {
        return id;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public String[] getTypes() {
        return types;
    }
}
