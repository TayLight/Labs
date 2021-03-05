package com.musicshop.entities;

public class Albom {
    private int idAlbom;
    private String titleAlbom;
    private String genre;

    public Albom(int idAlbom, String titleAlbom, String genre) {
        this.idAlbom = idAlbom;
        this.titleAlbom = titleAlbom;
        this.genre = genre;
    }

    public Albom(String titleAlbom, String genre) {
        this.titleAlbom = titleAlbom;
        this.genre = genre;
    }

    public Albom() {
    }

    public int getIdAlbom() {
        return idAlbom;
    }

    public void setIdAlbom(int idAlbom) {
        this.idAlbom = idAlbom;
    }

    public String getTitleAlbom() {
        return titleAlbom;
    }

    public void setTitleAlbom(String titleAlbom) {
        this.titleAlbom = titleAlbom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Albom{" +
                "idAlbom=" + idAlbom +
                ", titleAlbom='" + titleAlbom + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
