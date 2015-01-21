package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rui Almeida on 20-05-2014.
 */
public class GameResponse {
    public ArrayList<Game> Game;
    public ArrayList<Game> getGame() {
        return Game;
    }

    public ArrayList<Game> results;

    public void setGame(ArrayList<Game> game) {
        Game = game;
    }

}
