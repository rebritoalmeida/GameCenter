package com.game.metacritic.gamecenter.app.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.fragments.ShelfSearchFragment;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.google.gson.Gson;

import quickutils.core.QuickUtils;

public class ShelfSearchActivity extends Activity implements SearchView.OnQueryTextListener, ShelfSearchFragment.OnFragmentInteractionListener {
    /**
     * Called when the activity is first created.
     */

    private Boolean isMenuPressed = false;
    private Menu mMenu;
    private MenuItem searchItem;
    private SearchView mSearchView;
    private ShelfSearchFragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_search);
        mFragment = (ShelfSearchFragment) getFragmentManager().findFragmentById(R.id.list);

        String gameItemString = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameItemString = extras.getString(Constants.GAME_RESPONSE_KEY);
        }
        Game[] itemGameArray = new Gson().fromJson(gameItemString, Game[].class);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.shelf_search_activity, ShelfSearchFragment.newInstance(itemGameArray)).commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.shelf_search, menu);
        mMenu = menu;
        searchItem = menu.findItem(R.id.action_search);
        //refreshContact = menu.findItem(R.id.refresh_contact_text);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setIconified(true);
        MenuItemCompat.collapseActionView(searchItem);
        setupSearchView(searchItem);
        return super.onCreateOptionsMenu(menu);
    }

    public void setupSearchView(MenuItem searchItem) {
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
                | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        QuickUtils.log.d("s" + s);
        mFragment.searchGameService(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        QuickUtils.log.d("ssssssssss" + s);
        return false;
    }
}