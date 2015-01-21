package com.game.metacritic.gamecenter.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;


import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameDAO;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import quickutils.core.QuickUtils;

/**
 * Created by rui.almeida on 08/08/2014.
 */
public class Utils {


    // PATHs
    public static String REQUEST_GAME_PATH = "https://byroredux-metacritic.p.mashape.com/search/game";



    public static boolean toRefresh;
    // variables
    private static String mBaseURL;
    private static Context mContext;
    private static boolean mText;
    private static Map<Character, Character> MAP_NORM;

    public static synchronized void init(Context context) {
        QuickUtils.log.d("############## INITING UTILS ############ ");
        mContext = context;
    }

    public static boolean intervallContains(double low, double high, double n) {
        return n >= low && n <= high;
    }

    public static boolean insertInBD(Activity act, Game mGame){

        Realm realm = Realm.getInstance(act);
        realm.beginTransaction();
        GameDAO game = realm.createObject(GameDAO.class);

        game.setName(mGame.name);
        game.setPlatform(mGame.platform);
        game.setId(mGame.id);
        game.setThumbnail(mGame.getThumbnail());
        game.setUserscore(mGame.userscore.toString());
        game.setUrl(mGame.url);

        realm.commitTransaction();
        return true;
    }

    public static RealmResults selectInBD(Activity act){
        Realm realm = Realm.getInstance(act);
        RealmResults<GameDAO> gam = realm.where(GameDAO.class).findAll();
        QuickUtils.log.d("selectInBd" + gam.size());
        return gam;
    }

    public static int existsInBD(Activity act,Game mGame){
        Realm realm = Realm.getInstance(act);
        RealmResults<GameDAO> query = realm.where(GameDAO.class)
                            .equalTo("name", mGame.name)
                            .equalTo("platform", mGame.platform)
                            .findAll();
        if(query == null){
            return 0;
        }
        return query.size();
    }

    public static boolean deleteInBD(Activity act){
        try {
            QuickUtils.log.d("deleteInBD");
            Realm.deleteRealmFile(act);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void navigateTo(Activity activity, Class<?> destinationClass) {
        Intent mainIntent = new Intent(activity, destinationClass);
        //mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.overridePendingTransition(0, 0);
        activity.startActivity(mainIntent);
    }

    public static <T> void navigateTo(Activity activity, Class<?> destinationClass, String key, T object) {
        Intent mainIntent = new Intent(activity, destinationClass);
        //mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.overridePendingTransition(0, 0);
        mainIntent.putExtra(key, new Gson().toJson(object) );
        activity.startActivity(mainIntent);
    }
}

