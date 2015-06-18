package com.game.metacritic.gamecenter.app.activities;

import android.app.ActionBar;
        import android.app.Activity;
        import android.app.FragmentManager;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.widget.DrawerLayout;
        import android.text.Spannable;
        import android.text.SpannableString;
        import android.text.style.ForegroundColorSpan;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.fragments.NavigationDrawerFragment;

import quickutils.core.QuickUtils;

/**
 * Created by nunofeliciano on 27/08/14.
 */
public class ParentActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    protected NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupDrawer() {

        //todo: Change Other Activities to implement ParentActivity

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        /*fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/
    }

    public void restoreActionBar(String title) {
        ColorDrawable colorDrawable = null;
        int textColor = Color.WHITE;
        try {
            colorDrawable = new ColorDrawable(Color.RED);
            textColor = Color.BLACK;


        } catch (Exception e) {
            e.printStackTrace();
        }
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);

        Spannable text = new SpannableString(title);
        text.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        actionBar.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        actionBar.setDisplayHomeAsUpEnabled(true);

        //actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.drawer_subitem_selector).mutate()); //Here you should pass the color
        actionBar.setBackgroundDrawable(colorDrawable);
    }


}

