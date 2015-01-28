package com.game.metacritic.gamecenter.app.data.models;

import com.game.metacritic.gamecenter.app.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rui Almeida on 21-05-2014.
 */
public class Game {

    @SerializedName("id")
    public String id;
    @SerializedName("GameTitle")
    public String name;
    @SerializedName("ReleaseDate")
    public String releaseDate;
    @SerializedName("Platform")
    public String platform;
    @SerializedName("PlatformId")
    public String platformId;
    @SerializedName("ESRB")
    public String rating;
    @SerializedName("Overview")
    public String overview;
    @SerializedName("Genres")
    public Genres genres;
    @SerializedName("Images")
    public Images images;
    @SerializedName("Developer")
    public String developer;
    @SerializedName("Rating")
    public String userscore;



    private String thumbnail;
    public boolean isBox;
    public boolean isCartridge;
    public boolean isManual;

    @SerializedName("score")
    public String score;
    @SerializedName("url")
    public String url;
    @SerializedName("publisher")
    public String publisher;



    public Game(String score, String name) {
        this.score = score;
        this.name = name;
    }

    public Game(String id, String name, String score, String url, String rlsdate, String rating, String publisher, String platform, String genre, String thumbnail, String userscore, String developer, boolean box, boolean cartridge, boolean manual) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.url = url;
        this.releaseDate = rlsdate;
        this.rating = rating;
        this.publisher = publisher;
        this.platform = platform;
        this.thumbnail = thumbnail;
        this.userscore = userscore;
        this.developer = developer;
        this.isBox = box;
        this.isCartridge = cartridge;
        this.isManual = manual;
    }

    public String getThumbnail() {
        return Constants.THUMBNAIL_ENDPOINT.replace("{gameId}", id);
    }
}