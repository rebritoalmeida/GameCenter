package com.game.metacritic.gamecenter.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.fragments.GameDetailsFragment;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.google.gson.Gson;

public class GameDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);



        String gameItemString = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameItemString = extras.getString(Constants.GAME_KEY);
        }
        Game game = new Gson().fromJson(gameItemString, Game.class);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.game_details_activity, GameDetailsFragment.newInstance(game)).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_details, menu);
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
}
