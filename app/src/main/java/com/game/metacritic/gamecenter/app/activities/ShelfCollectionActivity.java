package com.game.metacritic.gamecenter.app.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.fragments.ShelfCollectionFragment;
import com.game.metacritic.gamecenter.app.fragments.ShelfSearchFragment;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.google.gson.Gson;

public class ShelfCollectionActivity extends Activity implements ShelfCollectionFragment.OnFragmentInteractionListener {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_collection);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.shelf_collection_activity, ShelfCollectionFragment.newInstance()).commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}