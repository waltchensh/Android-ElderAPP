package com.example.harden;

public class Artist {
    String artistId;
    String artistName;
    String artistGenre;
    String artistRe; //new


    public Artist(){

    }

    public Artist(String artistId, String artistName, String artistGenre ,String artistRe) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
        this.artistRe = artistRe; //new
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

    public String getArtistRe (){
        return artistRe;
    }  //new
}
