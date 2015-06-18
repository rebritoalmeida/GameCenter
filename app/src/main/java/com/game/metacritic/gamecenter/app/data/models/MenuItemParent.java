package com.game.metacritic.gamecenter.app.data.models;
/**
 * Created by mauro.chegancas on 31/07/2014.
 */
public abstract class MenuItemParent {

    public String title;
    public MenuType type;

    public MenuItemParent(String title, MenuType type) {
        this.title = title;
        this.type = type;
    }

    public MenuItemParent(String title) {

    }
}
