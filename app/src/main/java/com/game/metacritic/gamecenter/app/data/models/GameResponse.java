package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rui Almeida on 20-05-2014.
 */
public class GameResponse {
    @SerializedName("max_pages")
    public int maxPages;
    @SerializedName("count")
    public int count;
    @SerializedName("results")
    public ArrayList<Game> results;
}
