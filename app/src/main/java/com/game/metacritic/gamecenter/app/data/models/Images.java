package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ruialmeida on 1/17/15.
 */
public class Images {

    @SerializedName("Boxart")
    public ArrayList<Boxart> boxarts;
}
