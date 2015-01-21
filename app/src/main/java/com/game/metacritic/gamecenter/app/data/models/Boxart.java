package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ruialmeida on 1/17/15.
 */
public class Boxart {
    @SerializedName("content")
    public String content;
    @SerializedName("heigth")
    public int height;
    @SerializedName("thumb")
    public String thumb;
    @SerializedName("side")
    public String side;
    @SerializedName("width")
    public int width;
}
