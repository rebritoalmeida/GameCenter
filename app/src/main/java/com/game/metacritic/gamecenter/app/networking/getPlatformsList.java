package com.game.metacritic.gamecenter.app.networking;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;

import com.game.metacritic.gamecenter.app.data.models.Platform;
import com.game.metacritic.gamecenter.app.data.models.PlatformResponse;
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
import java.util.ArrayList;

import quickutils.core.QuickUtils;

/**
 * Created by ruialmeida on 6/25/15.
 */
public class GetPlatformsList extends AsyncTask<Void, Void, Void>{
    public Exception mException;
    private ArrayList<Platform> mPlatformResponse;
    private Context mContext;
    private TaskCallback<ArrayList<Platform>> mCallBack;

    public GetPlatformsList(Context context, TaskCallback callback){
        mContext = context;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpclient = new DefaultHttpClient();

        String url = Constants.GAMESDB_ENDPOINT + Constants.GET_PLATFORMS_LIST;
        HttpPost httppost = new HttpPost(url);

        try {
            QuickUtils.log.d("url"+ url);
            String platformsXml = httpclient.execute(httppost, new BasicResponseHandler());
            JSONObject jsonObject = XML.toJSONObject(platformsXml);
            JSONObject msg = (JSONObject) jsonObject.get("Data");
            JSONObject platforms = (JSONObject) msg.get("Platforms");
            Gson gson = new Gson();
            PlatformResponse platformResponse = gson.fromJson(String.valueOf(platforms), PlatformResponse.class);
            mPlatformResponse = platformResponse.Platform;
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
                mCallBack.onSuccess(mPlatformResponse);
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
