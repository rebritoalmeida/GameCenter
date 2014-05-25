package com.game.metacritic.gamecenter.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import CommonObj.Game;
import CommonObj.ResponseResult;

public class Main extends ActionBarActivity {
    public ResponseResult results;

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
        EditText textSearch = (EditText) findViewById(R.id.searchText);

        Ion.with(getBaseContext())
                .load("https://byroredux-metacritic.p.mashape.com/search/game")
                .setHeader("X-Mashape-Authorization", "k9NRNiglcY9Tepn1mjBQgf67JuBpBxyh")
                .setBodyParameter("title",textSearch.getText().toString())
                .as(new TypeToken<ResponseResult>() {})
                .setCallback(new FutureCallback<ResponseResult>() {
                    @Override
                    public void onCompleted(Exception e, ResponseResult result) {
                        if(result != null){
                            results = result;
                        }
                        TextView text = (TextView) findViewById(R.id.textView);
                        for(Game ee : results.results)
                         text.setText(ee.name);
                        createTable(results.results);
                    }
                });
    }

    public void createTable(List<Game> gameList){
        // reference the table layout
        final TableLayout tbl = (TableLayout)findViewById(R.id.tableGames);
        for(final Game game : gameList) {
            // delcare a new row
            final TableRow newRow = new TableRow(this);
            final TextView rowText = new TextView(getApplicationContext());
            rowText.setText(game.name);
            // add views to the row
            newRow.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    TextView text = (TextView) findViewById(R.id.textView);
                    text.setText(game.name);
                    Fragment gameDetails = new GameDetails();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_placeholder, gameDetails);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });
            newRow.addView(rowText); // you would actually want to set properties on this before adding it
            // add the row to the table layout
            tbl.addView(newRow);
        }
    }
}
