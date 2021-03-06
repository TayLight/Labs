package com.musicshop.entities;

public class Singer {
    private int idSinger;
    private String name;

    public Singer(int idSinger, String name) {
        this.idSinger = idSinger;
        this.name = name;
    }

    public Singer() {
    }

    public Singer(String name) {
        this.name = name;
    }

    public int getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return idSinger +
                ",'" + name + '\'';
    }
}
