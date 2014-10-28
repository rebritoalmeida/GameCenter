package com.game.metacritic.gamecenter.app.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Rui Almeida on 21-05-2014.
 */
@RealmClass
public class GameDAO extends RealmObject {

    private String name;
    private String score;
    private String platform;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}