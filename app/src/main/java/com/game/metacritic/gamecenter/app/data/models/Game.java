package com.game.metacritic.gamecenter.app.data.models;

import android.content.Context;

import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.google.gson.annotations.SerializedName;

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
    public String esrb;
    @SerializedName("Overview")
    public String overview;
    @SerializedName("Genres")
    public Genres genres;
    @SerializedName("Images")
    public Images images;
    @SerializedName("Developer")
    public String developer;
    @SerializedName("Rating")
    public String rating;



    private String thumbnail;
    public boolean isBox;
    public boolean isCartridge;
    public boolean isManual;


    @SerializedName("url")
    public String url;
    @SerializedName("publisher")
    public String publisher;



    public Game(String rating, String name) {
        this.rating = rating;
        this.name = name;
    }

    public Game(String id, String name, String score, String url, String rlsdate, String rating, String publisher, String platform, String genre, String thumbnail, String userscore, String developer, boolean box, boolean cartridge, boolean manual) {
        this.id = id;
        this.name = name;
        this.rating = score;
        this.url = url;
        this.releaseDate = rlsdate;
        this.esrb = rating;
        this.publisher = publisher;
        this.platform = platform;
        this.thumbnail = thumbnail;
        this.developer = developer;
        this.isBox = box;
        this.isCartridge = cartridge;
        this.isManual = manual;
    }

    public String getThumbnail() {
        return Constants.THUMBNAIL_ENDPOINT.replace("{gameId}", id);
    }

    public int exists(Context context, Game game){
       return Utils.existsInBD(context, game);
    }
}