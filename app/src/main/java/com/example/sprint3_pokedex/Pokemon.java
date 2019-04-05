package com.example.sprint3_pokedex;

public class Pokemon {

    private String[] elementType;
    private String imageURL;
    private int number;
    private String name;
    private String[] moves;
    private boolean isFavorite = false;

    public Pokemon(String[] elementType, String imageURL, int number, String name, String[] moves) {
        this.elementType = elementType;
        this.imageURL = imageURL;
        this.number = number;
        this.name = name;
        this.moves = moves;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String[] getElementType() {
        return elementType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String[] getMoves() {
        return moves;
    }
}

