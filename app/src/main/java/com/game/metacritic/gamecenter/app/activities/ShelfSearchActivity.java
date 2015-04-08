package com.game.metacritic.gamecenter.app.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.fragments.ShelfSearchFragment;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.google.gson.Gson;

import quickutils.core.QuickUtils;

public class ShelfSearchActivity extends Activity implements ShelfSearchFragment.OnFragmentInteractionListener, SearchView.OnQueryTextListener {
    private SearchView mSearchView;
    private MenuItem searchItem;
    private String[] state = { "Cupcake", "Donut", "Eclair", "Froyo",
            "Gingerbread", "HoneyComb", "IceCream Sandwich", "Jellybean",
            "kitkat" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_search);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        searchItem = menu.findItem(R.id.action_search);



        final Spinner s = (Spinner) menu.findItem(R.id.menuSort).getActionView(); // find the spinner
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, state);

        //  SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActionBar().getThemedContext(), R.array.platform, android.R.layout.simple_spinner_dropdown_item); //  create the adapter from a StringArray
        s.setAdapter(spinnerArrayAdapter); // set the adapter
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                QuickUtils.log.d("selected->" + s.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        QuickUtils.log.d("callSearch");
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setIconified(true);

//        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerPlatform.setAdapter(adapter_state);
//        spinnerPlatform.setOnItemSelectedListener(this);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        QuickUtils.log.d("ENTROUUUUUUUUUU---->"+s);
        ShelfSearchFragment fragment = (ShelfSearchFragment) getFragmentManager().findFragmentById(R.id.shelf_search_activity);
        fragment.searchQuery(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}