package com.game.metacritic.gamecenter.app.utils.interfaces;

/**
 * Created by Rui on 17-09-2014.
 */
public interface TaskCallback<T> {
    public void onSuccess(T object);

    public void onFailure(Exception e);
}

