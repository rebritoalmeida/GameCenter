package com.game.metacritic.gamecenter.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.*;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

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
        runOnUiThread(new Runnable() {
            public void run() {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://www.metacritic.com/search/all/" + searchKey + "/results", new

                                JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, org.json.JSONArray
                                            responseBody) {
                                        System.out.println(statusCode);
                                        //System.out.println(responseBody.toString());

                                    }
                                }
                );
            }
        });
    }
}
