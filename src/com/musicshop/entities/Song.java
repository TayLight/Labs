package com.musicshop.entities;


public class Song {

    private int idSong;
    private String titleSong;
    private int duration;
    private int idSinger;
    private int idAlbom;

    public Song(int idSong, String titleSong, int duration, int idSinger, int idAlbom) {
        this.idSong = idSong;
        this.titleSong = titleSong;
        this.duration = duration;
        this.idSinger = idSinger;
        this.idAlbom = idAlbom;
    }

    public Song(String titleSong, int duration, int idSinger, int idAlbom) {
        this.titleSong = titleSong;
        this.duration = duration;
        this.idSinger = idSinger;
        this.idAlbom = idAlbom;
    }

    public Song(String titleSong, int duration) {
        this.titleSong = titleSong;
        this.duration = duration;
    }

    public Song() {
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getTitleSong() {
        return titleSong;
    }

    public void setTitleSong(String titleSong) {
        this.titleSong = titleSong;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    public int getIdAlbom() {
        return idAlbom;
    }

    public void setIdAlbom(int idAlbom) {
        this.idAlbom = idAlbom;
    }

    @Override
    public String toString() {
        return idSong +
                ", '" + titleSong + '\'' +
                ", " + duration +
                ", " + idSinger +
                ", " + idAlbom;
    }
}
