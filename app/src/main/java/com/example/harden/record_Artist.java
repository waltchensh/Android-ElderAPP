package com.example.harden;

public class record_Artist {

    String artistID;
    String artistName;
    String artistGenre;

    public record_Artist(){

    }

    public record_Artist(String artistID, String artistName, String artistGenre) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}
