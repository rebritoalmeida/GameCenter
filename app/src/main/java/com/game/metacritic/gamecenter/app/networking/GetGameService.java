package com.game.metacritic.gamecenter.app.networking;

import android.content.Context;
import android.os.AsyncTask;

import com.game.metacritic.gamecenter.app.data.models.FindGameRequest;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.data.models.ResultGame;
import com.game.metacritic.gamecenter.app.data.models.enums.PlatformType;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.interfaces.TaskCallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import quickutils.core.QuickUtils;

/**
 * Created by ruialmeida on 9/28/14.
 */
public class GetGameService extends AsyncTask<Void, Void, Void> {

    public Exception mException;

    private Context mContext;
    private TaskCallback<Game> mCallBack;
    private Game mGame;
    private Game mGameResponse;

    public GetGameService(Context context, Game game, TaskCallback callback) {
        mContext = context;
        mCallBack = callback;
        mGame = game;
    }


    @Override
    protected Void doInBackground(Void... params) {

        QuickUtils.log.d("STARTING GAME REQUEST...");
        // if (QuickUtils.web.hasInternetConnection(mContext)) {
        HttpClient httpclient = new DefaultHttpClient();
        String url = Constants.GAMESDB_ENDPOINT + Constants.GET_GAME_ENDPOINT.replace("{gameId}", mGame.id);
        HttpPost httppost = new HttpPost(url);

        try {
            String gameXml = httpclient.execute(httppost, new BasicResponseHandler());
            JSONObject jsonObject = XML.toJSONObject(gameXml);
            JSONObject msg = (JSONObject) jsonObject.get("Data");
            JSONObject msg1 = (JSONObject) msg.get("Game");

            JSONObject images= msg1.getJSONObject("Images");
            Object item = images.get("boxart");
            if(!(item instanceof JSONArray)) {
                JSONObject boxart = images.getJSONObject("boxart");

                String box = String.valueOf(boxart);
                box = box.replace("{","[{");
                box = box.replace("}","}]");
                JSONArray j = new JSONArray(box);
                images.remove("boxart");
                images.put("boxart",j);

            }


            QuickUtils.log.d("teste");
            Gson gson = new Gson();
            mGameResponse = gson.fromJson(String.valueOf(msg1), Game.class);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
