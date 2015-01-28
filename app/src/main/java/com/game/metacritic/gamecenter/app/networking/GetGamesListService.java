package com.game.metacritic.gamecenter.app.networking;

import android.content.Context;
import android.os.AsyncTask;

import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.interfaces.TaskCallback;
import com.google.gson.Gson;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import quickutils.core.QuickUtils;

/**
 * Created by rui.almeida on 08/08/2014.
 */
public class GetGamesListService extends AsyncTask<Void, Void, Void> {
    public Exception mException;

    private Context mContext;
    private ArrayList<Game> mGameResponse;
    private TaskCallback<ArrayList<Game>> mCallBack;
    private String mGameTitle;
    private String gameTitleEnconded;

    public GetGamesListService(Context context, String gameTitle, TaskCallback callback) {
        mContext = context;
        mCallBack = callback;
        mGameTitle = gameTitle;
    }


    @Override
    protected Void doInBackground(Void... params) {
        QuickUtils.log.d("STARTING GAME REQUEST...");
        // if (QuickUtils.web.hasInternetConnection(mContext)) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            gameTitleEnconded = URLEncoder.encode(mGameTitle, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constants.GAMESDB_ENDPOINT + Constants.GET_GAME_LIST_ENDPOINT.replace("{gameTitle}", gameTitleEnconded);
        HttpPost httppost = new HttpPost(url);

        try {
            QuickUtils.log.d("url"+ url);
            String gameXml = httpclient.execute(httppost, new BasicResponseHandler());
            JSONObject jsonObject = XML.toJSONObject(gameXml);
            JSONObject msg = (JSONObject) jsonObject.get("Data");
            Gson gson = new Gson();
            GameResponse gameResponse = gson.fromJson(String.valueOf(msg), GameResponse.class);
            mGameResponse = gameResponse.Game;
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
