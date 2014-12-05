package com.game.metacritic.gamecenter.app.data.models;

import com.game.metacritic.gamecenter.app.data.models.enums.PlatformType;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Rui Almeida on 21-05-2014.
 */
public class Game {
    @SerializedName("name")
    public String name;
    @SerializedName("score")
    public String score;
    @SerializedName("url")
    public String url;
    @SerializedName("rlsdate")
    public String rlsdate;
    @SerializedName("rating")
    public String rating;
    @SerializedName("publisher")
    public String publisher;
    @SerializedName("platform")
    public String platform;
    @SerializedName("genre")
    public String genre;
    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("userscore")
    public String userscore;
    @SerializedName("developer")
    public String developer;

    public Game(String score, String name) {
        this.score = score;
        this.name = name;
    }

    public Game(String name, String score, String url, String rlsdate, String rating, String publisher, String platform, String genre, String thumbnail, String userscore, String developer) {
        this.name = name;
        this.score = score;
        this.url = url;
        this.rlsdate = rlsdate;
        this.rating = rating;
        this.publisher = publisher;
        this.platform = platform;
        this.genre = genre;
        this.thumbnail = thumbnail;
        this.userscore = userscore;
        this.developer = developer;
    }
}