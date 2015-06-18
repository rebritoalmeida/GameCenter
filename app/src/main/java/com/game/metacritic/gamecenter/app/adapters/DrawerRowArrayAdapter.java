package com.game.metacritic.gamecenter.app.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.MenuItem;
import com.game.metacritic.gamecenter.app.data.models.MenuItemParent;
import com.game.metacritic.gamecenter.app.data.models.MenuSubItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauro.chegancas on 01/08/2014.
 */
public class DrawerRowArrayAdapter extends ArrayAdapter<MenuItemParent> {

    private final Activity mContext;
    private List<MenuItemParent> mMenuItems = new ArrayList<MenuItemParent>();

    public DrawerRowArrayAdapter(Activity context, List<MenuItemParent> menuItems) {
        super(context, R.layout.drawer_menu_row, menuItems);
        this.mContext = context;
        mMenuItems = menuItems;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflator = mContext.getLayoutInflater();

        final MenuItemParent obj = mMenuItems.get(position);

        View view = inflator.inflate(R.layout.drawer_menu_row, parent, false);
        final TextView menuItemText = (TextView) view.findViewById(R.id.text_drawer_row);
        getTextConfig(menuItemText);

        if(obj instanceof MenuItem)
        {

            //Drawable state_pressed = getContext().getResources().getDrawable(R.drawable.drawer_item_selector).mutate();
            //StateListDrawable drawable = new StateListDrawable();
            //drawable.addState(new int[] { android.R.attr.state_pressed }, state_pressed);
            //drawable.addState(new int[] { }, state_pressed);
            //view.setBackground(drawable);
            getConfigsMenu(view);

            final ImageButton menutItemIcon = (ImageButton)  view.findViewById(R.id.icon_drawer_row);

            menuItemText.setText(mMenuItems.get(position).title);
            Drawable icon = mContext.getResources().getDrawable(((MenuItem) obj).icon);
            menutItemIcon.setImageDrawable(icon);

        }
        else if(obj instanceof MenuSubItem)
        {
            //view.setBackgroundColor(mContext.getResources().getColor(R.color.drawer_subitem_background));
            //Drawable state_pressed = getContext().getResources().getDrawable(R.drawable.drawer_subitem_selector).mutate();

            //StateListDrawable drawable = new StateListDrawable();
            //drawable.addState(new int[] { android.R.attr.state_pressed }, state_pressed);
            //drawable.addState(new int[] { }, state_pressed);
            //view.setBackground(drawable);
            getConfigsSubMenu(view);
            menuItemText.setText(mMenuItems.get(position).title);
        }

        return view;
    }


    public void getConfigsMenu(View view){
        try {

            view.setBackgroundColor(Color.RED);
            View v = view.findViewById(R.id.drawer_layout);

            LayerDrawable bgDrawable = (LayerDrawable)v.getBackground();
            GradientDrawable shape = (GradientDrawable)   bgDrawable.findDrawableByLayerId(R.id.shape_id);
            shape.setStroke(1, Color.BLUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getConfigsSubMenu(View view){
        try {
            float[] hsv = new float[3];
            Color.colorToHSV(Color.RED, hsv);
            hsv[2] *= 0.8f; // value component
            int color = Color.HSVToColor(hsv);
            view.setBackgroundColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTextConfig(TextView menuItemText){
        try {
            menuItemText.setTextColor(Color.GREEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
