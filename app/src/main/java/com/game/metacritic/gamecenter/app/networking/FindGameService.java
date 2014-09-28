package com.game.metacritic.gamecenter.app.networking;

import android.content.Context;
import android.os.AsyncTask;

import com.game.metacritic.gamecenter.app.data.models.FindGameRequest;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.interfaces.TaskCallback;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.JSONObjectBody;
import com.koushikdutta.ion.Ion;

import java.util.concurrent.ExecutionException;

import quickutils.core.QuickUtils;

/**
 * Created by ruialmeida on 9/28/14.
 */
public class FindGameService extends AsyncTask<Void, Void, Void> {

    public Exception mException;

    private Context mContext;
    private Game mGame;
    private TaskCallback<Game> mCallBack;
    private FindGameRequest mfindGameRequest;

    public FindGameService(Context context, FindGameRequest findGameRequest, TaskCallback callback) {
        mContext = context;
        mCallBack = callback;
        mfindGameRequest = findGameRequest;
    }


    @Override
    protected Void doInBackground(Void... params) {
        QuickUtils.log.d("STARTING GAME REQUEST...");
        // if (QuickUtils.web.hasInternetConnection(mContext)) {
        JsonObject json = new JsonObject();

        json.addProperty(Constants.TITLE, mfindGameRequest.title);
        json.addProperty(Constants.PLATFORM, mfindGameRequest.platform);
        json.addProperty(Constants.RETRY, mfindGameRequest.retry);

        try {
            Ion.with(mContext)
                    .load("https://byroredux-metacritic.p.mashape.com/find/game")
                    .setHeader("X-Mashape-Authorization", "k9NRNiglcY9Tepn1mjBQgf67JuBpBxyh")
                    .setJsonObjectBody(json)
                    .as(new TypeToken<Game>() {
                    })
                    .setCallback(new FutureCallback<Game>() {
                        @Override
                        public void onCompleted(Exception e, Game game) {
                            if (e != null) {
                                mException = e;
                                return;
                            }
                            mGame = game;
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
                mCallBack.onSuccess(mGame);
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
