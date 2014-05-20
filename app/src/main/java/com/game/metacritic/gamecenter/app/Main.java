package com.game.metacritic.gamecenter.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

import CommonObj.Game;
import CommonObj.ResponseResult;

public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void searchButton(View v){
        EditText textSearch = (EditText) findViewById(R.id.searchText);
        TextView text = (TextView) findViewById(R.id.textView);

        text.setText(textSearch.getText());
        String textString = textSearch.getText().toString();
        sendRequest(textString);
    }

    public void sendRequest(final String searchKey){

        Ion.with(getBaseContext())
                .load("https://byroredux-metacritic.p.mashape.com/search/game")
                .setHeader("X-Mashape-Authorization", "k9NRNiglcY9Tepn1mjBQgf67JuBpBxyh")
                .setBodyParameter("title", "Elder")
                .setBodyParameter("platform", "1")
                .as(new TypeToken<ResponseResult>() {})
                .setCallback(new FutureCallback<ResponseResult>() {
                    @Override
                    public void onCompleted(Exception e, ResponseResult result) {
                        TextView text = (TextView) findViewById(R.id.textView);
                        for(Game ee : result.results)
                         text.setText(ee.name);
                    }
                });

    }
}
