package com.game.metacritic.gamecenter.app.data.models;

/**
 * Created by mauro.chegancas on 31/07/2014.
 */
public class MenuItem extends MenuItemParent {

    public int icon;

    public MenuItem(String title, int icon, MenuType type) {
        super(title,type);
        this.icon = icon;
    }
}
