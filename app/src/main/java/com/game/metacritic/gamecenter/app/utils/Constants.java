package com.game.metacritic.gamecenter.app.utils;

/**
 * Created by rui.almeida on 08/08/2014.
 */
public class Constants {

    public static final String GAMESDB_ENDPOINT = "http://www.thegamesdb.net/api/";
    public static final String GET_GAME_LIST_ENDPOINT = "GetGamesList.php?name={gameTitle}";
    public static final String THUMBNAIL_ENDPOINT = "http://thegamesdb.net/banners/_favcache/_tile-view/boxart/original/front/{gameId}-1.jpg";
    public static final String GET_GAME_ENDPOINT = "GetGame.php?id={gameId}";



    // KEYS
    public static final String TITLE = "title";
    public static final String PLATFORM = "platform";
    public static final String RETRY = "retry";



    // COLORS
    public static final String DEFAULT_PRIMARY_BACKGROUND_COLOR = "#525662";


    //KEYS
    public static final String GAME_RESPONSE_KEY = "game_response_key";
    public static final String GAME_KEY = "game_key";


    //Variables
    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final Double[] LOW = {0.0, 3.0};
    public static final Double[] MEDIUM = {3.1, 7.0};
    public static final Double[] HIGHT = {7.1, 10.0};

}
