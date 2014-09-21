package com.game.metacritic.gamecenter.app.networking;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.*;
import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.exceptions.NoInternetConnectionException;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.game.metacritic.gamecenter.app.utils.interfaces.TaskCallback;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.concurrent.ExecutionException;

import quickutils.core.QuickUtils;

/**
 * Created by rui.almeida on 08/08/2014.
 */
public class SearchGameService  extends AsyncTask<Void, Void, Void> {
    public Exception mException;

    private Context mContext;
    private GameResponse mGameResponse;
    private TaskCallback<GameResponse> mCallBack;
    private String mGameTitle;

    public SearchGameService(Context context, String gameTitle, TaskCallback callback) {
        mContext = context;
        mCallBack = callback;
        mGameTitle = gameTitle;
    }


    @Override
    protected Void doInBackground(Void... params) {
        QuickUtils.log.d("STARTING GAME REQUEST...");
       // if (QuickUtils.web.hasInternetConnection(mContext)) {
            try {
                Ion.with(mContext)
                        .load("https://byroredux-metacritic.p.mashape.com/search/game")
                        .setHeader("X-Mashape-Authorization", "k9NRNiglcY9Tepn1mjBQgf67JuBpBxyh")
                        .setBodyParameter(Constants.TITLE,String.valueOf(mGameTitle))
                        .as(new TypeToken<GameResponse>() {
                        })
                        .setCallback(new FutureCallback<GameResponse>() {
                            @Override
                            public void onCompleted(Exception e, GameResponse gameResponse) {
                                if (e != null) {
                                    mException = e;
                                    return;
                                }
                                mGameResponse = gameResponse;
                            }
                        }).get();

            } catch (InterruptedException e) {
                mException = e;
            } catch (NullPointerException e) {
                mException = e;
            } catch (ExecutionException e) {
                mException = e;
            }catch (Exception e) {
                mException = e;
            }

       // } else {
        //    mException = new NoInternetConnectionException("No internet connection");
        //}
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess(mGameResponse);
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
