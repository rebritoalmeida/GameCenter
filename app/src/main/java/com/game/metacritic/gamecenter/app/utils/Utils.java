package com.game.metacritic.gamecenter.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;


import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.adapters.GameArrayAdapter;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameDAO;
import com.google.gson.Gson;
import com.metrekare.android.widget.ContentLoadingProgressDialog;

import java.util.ArrayList;
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
    private static ArrayList<Integer> mSelectedItems;
    private static ContentLoadingProgressDialog dialog;

    public static synchronized void init(Context context) {
        QuickUtils.log.d("############## INITING UTILS ############ ");
        mContext = context;
    }

    public static boolean intervallContains(double low, double high, double n) {
        return n >= low && n <= high;
    }

    public static ContentLoadingProgressDialog dialogWithMessage(Context context, String message) {
        return new ContentLoadingProgressDialog(context)
                .message(message)
                .indeterminate(true)
                .cancelable(false)
                .minDelay(0)
                .minShowTime(10);
    }

    public static boolean insertInBD(Activity act, Game mGame){
        dialog = Utils.dialogWithMessage(act, "Loading");
        dialog.show();
        Realm realm = Realm.getInstance(act);
        realm.beginTransaction();
        GameDAO game = realm.createObject(GameDAO.class);

        game.setName(mGame.name);
        game.setPlatform(mGame.platform);
        game.setId(mGame.id);
        game.setThumbnail(mGame.getThumbnail());
        game.setBox(mGame.isBox);
        game.setCartridge(mGame.isCartridge);
        game.setManual(mGame.isManual);

        realm.commitTransaction();
        dialog.cancel();
        return true;
    }

    public static RealmResults selectInBD(Activity act){
        Realm realm = Realm.getInstance(act);
        RealmResults<GameDAO> gam = realm.where(GameDAO.class).findAll();
        QuickUtils.log.d("selectInBd" + gam.size());
        return gam;
    }

    public static int existsInBD(Context act,Game mGame){
        Realm realm = Realm.getInstance(act);
        RealmResults<GameDAO> query = realm.where(GameDAO.class)
                            .equalTo("id", mGame.id)
                            .equalTo("name", mGame.name)
                            .findAll();
        if(query == null){
            return 0;
        }
        return query.size();
    }

    public static int updateInBD(Activity act,Game mGame){
        Realm realm = Realm.getInstance(act);
        realm.beginTransaction();

        RealmResults<GameDAO> query = realm.where(GameDAO.class)
                .equalTo("id", mGame.id)
                .equalTo("name", mGame.name)
                .findAll();


        for (int i = 0; i < query.size(); i++) {
            query.get(i).setManual(mGame.isManual);
            query.get(i).setBox(mGame.isBox);
            query.get(i).setCartridge(mGame.isCartridge);
        }

        realm.commitTransaction();
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

    public static void deleteRow(Context context, Game game) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        GameDAO game1 = realm.where(GameDAO.class)
                .equalTo("id", game.id)
                .findFirst();
        game1.removeFromRealm();
        realm.commitTransaction();
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

    public static Dialog onCreateDialog(final Activity activity, final Game game, final GameArrayAdapter gameAdapter) {
        mSelectedItems = new ArrayList();  // Where we track the selected items

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // Set the dialog title
        //builder.setTitle("Pick")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                builder.setMultiChoiceItems(R.array.multi_array, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (int value : mSelectedItems) {
                            QuickUtils.log.d("value ->" + value);
                            switch (value) {
                                case 0:
                                    game.isBox = true;
                                    break;
                                case 1:
                                    game.isCartridge = true;
                                    break;
                                case 2:
                                    game.isManual = true;
                                    break;
                            }

                        }

                        Utils.insertInBD(activity, game);
                        Toast toast = Toast.makeText(activity, "Game added with success", Toast.LENGTH_SHORT);
                        toast.show();

                        if (gameAdapter != null) {
                            gameAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}

