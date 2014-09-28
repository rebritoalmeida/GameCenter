package com.game.metacritic.gamecenter.app.data.models;

/**
 * Created by ruialmeida on 9/28/14.
 */
public class FindGameRequest {

    public String title;
    public int platform;
    public int retry;

    public FindGameRequest(String title, int platform, int retry) {
        this.title = title;
        this.platform = platform;
        this.retry = retry;
    }
}
