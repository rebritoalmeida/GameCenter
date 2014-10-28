package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ruialmeida on 10/28/14.
 */
public interface IGame {
    @SerializedName("name")
    String name = null;
    @SerializedName("score")
    String score = null;
    @SerializedName("url")
    String url = null;
    @SerializedName("rlsdate")
    String rlsdate = null;
    @SerializedName("rating")
    String rating = null;
    @SerializedName("publisher")
    String publisher = null;
    @SerializedName("platform")
    String platform = null;
    @SerializedName("genre")
    String genre = null;
    @SerializedName("thumbnail")
    String thumbnail = null;
    @SerializedName("userscore")
    Double userscore = null;
    @SerializedName("developer")
    String developer = null;
}
