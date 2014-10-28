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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmQuery;
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
        game.setScore(mGame.score);
        game.setPlatform(mGame.platform);

        realm.commitTransaction();
        return true;
    }

    public static RealmResults selectInBD(Activity act){
        Realm realm = Realm.getInstance(act);
        RealmResults<GameDAO> gam = realm.where(GameDAO.class).findAll();
        return gam;
    }

    public static int existsInBD(Activity act,Game mGame){
        Realm realm = Realm.getInstance(act);
        RealmResults<GameDAO> query = realm.where(GameDAO.class)
                            .equalTo("name", mGame.name)
                            .equalTo("score", mGame.score)
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

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static int hexToColor(String hexColor) {

        return Color.parseColor(hexColor);
    }

/*
    public static FeatureType convertStringToEnum(String string) {
        FeatureType featureType;
        try {
            featureType  =   FeatureType.valueOf(string.toUpperCase());
        } catch(Exception e) {
            featureType = FeatureType.DEFAULT;
        }
        return featureType;
    }

    public static IndicatorType convertIndicatorStringToEnum(String string) {
        IndicatorType indicatorType;
        try {
            indicatorType  =   IndicatorType.valueOf(string.toUpperCase());
        } catch(Exception e) {
            indicatorType = IndicatorType.DEFAULT;
        }
        return indicatorType;
    }
*/
/*
    public static void initURLs() {
        try {
            mBaseURL = DataManager.getBaseUrl();
            AUTHENTICATION_PATH = mBaseURL + "/api/authentication/gettoken";
            CONFIGURATION_PATH = mBaseURL + "/api/configuration/readbygroup";
            FLOWS_GET_ALL_PATH = mBaseURL + "/api/flow/readuserflows";
            FLOWS_REMOVE_PATH = mBaseURL + "/api/flow/removeuserfromflow";
            FLOWS_UNSELECTED_PATH = mBaseURL + "/api/flow/readunselectedflows";
            FLOWS_HIGHLIGHT_PATH = mBaseURL + "/api/flow/ReadHighlight";
            FLOWS_ADD_PATH = mBaseURL + "/api/flow/addusertoflow";
            CREDENTIALS_PATH = mBaseURL + "/api/userCredentials/checkcredentials";
            CREDENTIALS_VALIDATE_PATH = mBaseURL + "/api/userCredentials/setcredentials";
            KEYWORDS_PATH = mBaseURL + "/api/contextualKeywords/getaliaslist";
            KEYWORDS_ADD_PATH = mBaseURL + "/api/contextualkeywords/addkeyword";

            // todo: rest of the PATHS
        } catch (Exception e) {
            // todo failure
            e.printStackTrace();
        }
    }
*/
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
/*
    public static ContentLoadingProgressDialog dialogWithMessage(Context context,String message) {
        return new ContentLoadingProgressDialog(context)
                .message(message)
                .indeterminate(true)
                .cancelable(true)
                .minDelay(0)
                .minShowTime(10);
    }
*/

    public static  boolean equalLists(List a, List b){
        // Check for sizes and nulls
        if ((a == null && b!= null) || (a != null && b== null) || (a.size() != b.size()) ){
            return false;
        }

        if (a == null && b == null) return true;

        return a.hashCode() == b.hashCode();

    }
/*
    public static void performLogout(Activity activity) {

        DataManager.wipe();

        // Navigate to
        Intent mainIntent = new Intent(activity, LoginActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.overridePendingTransition(0, 0);
        activity.startActivity(mainIntent);
    }
*/
    public static String removeAccents(String value)
    {
        if (MAP_NORM == null || MAP_NORM.size() == 0)
        {
            MAP_NORM = new HashMap<Character, Character>();
            MAP_NORM.put('À', 'A');
            MAP_NORM.put('Á', 'A');
            MAP_NORM.put('Â', 'A');
            MAP_NORM.put('Ã', 'A');
            MAP_NORM.put('Ä', 'A');
            MAP_NORM.put('È', 'E');
            MAP_NORM.put('É', 'E');
            MAP_NORM.put('Ê', 'E');
            MAP_NORM.put('Ë', 'E');
            MAP_NORM.put('Í', 'I');
            MAP_NORM.put('Ì', 'I');
            MAP_NORM.put('Î', 'I');
            MAP_NORM.put('Ï', 'I');
            MAP_NORM.put('Ù', 'U');
            MAP_NORM.put('Ú', 'U');
            MAP_NORM.put('Û', 'U');
            MAP_NORM.put('Ü', 'U');
            MAP_NORM.put('Ò', 'O');
            MAP_NORM.put('Ó', 'O');
            MAP_NORM.put('Ô', 'O');
            MAP_NORM.put('Õ', 'O');
            MAP_NORM.put('Ö', 'O');
            MAP_NORM.put('Ñ', 'N');
            MAP_NORM.put('Ç', 'C');
            MAP_NORM.put('ª', 'A');
            MAP_NORM.put('º', 'O');
            MAP_NORM.put('§', 'S');
            MAP_NORM.put('³', '3');
            MAP_NORM.put('²', '2');
            MAP_NORM.put('¹', '1');
            MAP_NORM.put('à', 'a');
            MAP_NORM.put('á', 'a');
            MAP_NORM.put('â', 'a');
            MAP_NORM.put('ã', 'a');
            MAP_NORM.put('ä', 'a');
            MAP_NORM.put('è', 'e');
            MAP_NORM.put('é', 'e');
            MAP_NORM.put('ê', 'e');
            MAP_NORM.put('ë', 'e');
            MAP_NORM.put('í', 'i');
            MAP_NORM.put('ì', 'i');
            MAP_NORM.put('î', 'i');
            MAP_NORM.put('ï', 'i');
            MAP_NORM.put('ù', 'u');
            MAP_NORM.put('ú', 'u');
            MAP_NORM.put('û', 'u');
            MAP_NORM.put('ü', 'u');
            MAP_NORM.put('ò', 'o');
            MAP_NORM.put('ó', 'o');
            MAP_NORM.put('ô', 'o');
            MAP_NORM.put('õ', 'o');
            MAP_NORM.put('ö', 'o');
            MAP_NORM.put('ñ', 'n');
            MAP_NORM.put('ç', 'c');
        }

        if (value == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(value);

        for(int i = 0; i < value.length(); i++) {
            Character c = MAP_NORM.get(sb.charAt(i));
            if(c != null) {
                sb.setCharAt(i, c.charValue());
            }
        }

        return sb.toString();

    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
/*
    public static void sortListContactByFullName(List<Contact> list){
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(final Contact object1, final Contact object2) {
                    return removeAccents(object1.fullName).compareToIgnoreCase(removeAccents(object2.fullName));
                }
            });
        }
    }

    public static void sortListKeywordByName(List<Keyword> list){
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<Keyword>() {
                @Override
                public int compare(final Keyword object1, final Keyword object2) {
                    return removeAccents(object1.keyword).compareToIgnoreCase(removeAccents(object2.keyword));
                }
            });
        }
    }
    */
}

