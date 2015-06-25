package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ruialmeida on 6/25/15.
 */
public class Platform {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("alias")
    public  String alias;
}
