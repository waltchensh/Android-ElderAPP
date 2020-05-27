package com.example.harden;

public class join_activity {

    String artistID;
    String artistName;
    String artistGenre;

    public join_activity(){

    }

    public join_activity(String artistID, String artistName, String artistGenre) {
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
