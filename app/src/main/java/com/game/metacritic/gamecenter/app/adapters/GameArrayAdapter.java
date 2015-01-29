package com.game.metacritic.gamecenter.app.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.utils.interfaces.AddGameButtonClickListener;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by ruialmeida on 1/15/15.
 */
public class GameArrayAdapter extends ArrayAdapter<Game> {

    private final Context mContext;
    private Color barColor;
    private final List<Game> mGames;
    private ViewHolder viewHolder;
    private AddGameButtonClickListener mClickListener = null;
    private boolean mIsCollection;

    public GameArrayAdapter(Context context, List<Game> gamesList, boolean isCollection, AddGameButtonClickListener installClickListener) {
        super(context, R.layout.game_row, gamesList);
        this.mContext = context;
        this.mGames = gamesList;
        this.mClickListener = installClickListener;
        this.mIsCollection = isCollection;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Game game = mGames.get(position);

        final LinearLayout row;
        row = (LinearLayout) (convertView == null ? LayoutInflater.from(getContext()).inflate(R.layout.game_row, parent, false) : convertView);

        viewHolder = new ViewHolder();
        viewHolder.titleTextView = (TextView) row.findViewById(R.id.title_textview);
        viewHolder.imageImageView = (ImageView) row.findViewById(R.id.image_imagebutton);
        viewHolder.releaseDateTextView = (TextView) row.findViewById(R.id.release_date_textview);
        viewHolder.checkBoxList = (LinearLayout) row.findViewById(R.id.checkBoxList);
        viewHolder.platformTextView = (TextView) row.findViewById(R.id.platform_textview);
        viewHolder.actionButton = (Button) row.findViewById(R.id.action_app_button);
        viewHolder.boxCheckbox = (CheckBox) row.findViewById(R.id.checkBox);
        viewHolder.cartCheckbox = (CheckBox) row.findViewById(R.id.checkCartridge);
        viewHolder.manualCheckbox = (CheckBox) row.findViewById(R.id.checkManual);
        viewHolder.barView = (View) row.findViewById(R.id.left_bar);
        viewHolder.deleteButton = (Button) row.findViewById(R.id.delete_app_button);


        int addColorInt = R.color.AddButton;
        int deleteColorInt = R.color.red;
        int unselectedColorInt = R.color.gray;

        int addColor = mContext.getResources().getColor(addColorInt);
        int deleteColor = mContext.getResources().getColor(deleteColorInt);
        int unselectedColor = mContext.getResources().getColor(unselectedColorInt);


        final GradientDrawable addShape = new GradientDrawable();
        addShape.setCornerRadius(10);
        addShape.setColor(addColor);

        GradientDrawable deleteShape = new GradientDrawable();
        deleteShape.setCornerRadius(10);
        deleteShape.setColor(deleteColor);

        GradientDrawable unselectedShape = new GradientDrawable();
        unselectedShape.setCornerRadius(10);
        unselectedShape.setColor(unselectedColor);

        //Utils.setStoreActionButton(flow, viewHolder.actionButton);
        String text;


        if (mIsCollection) {
            text = "Update";

            viewHolder.checkBoxList.setVisibility(View.VISIBLE);

            viewHolder.checkBoxList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    viewHolder.actionButton.setBackgroundDrawable(addShape);

                }
            });
            viewHolder.boxCheckbox.setChecked(game.isBox);
            viewHolder.manualCheckbox.setChecked(game.isManual);
            viewHolder.cartCheckbox.setChecked(game.isCartridge);
            viewHolder.releaseDateTextView.setVisibility(View.GONE);
            viewHolder.deleteButton.setBackgroundDrawable(deleteShape);
            viewHolder.actionButton.setBackgroundDrawable(addShape);
        } else {
            text = "Add";
            viewHolder.checkBoxList.setVisibility(View.GONE);
            viewHolder.deleteButton.setVisibility(View.GONE);
            viewHolder.actionButton.setBackgroundDrawable(addShape);
        }

        //image = (flow.isInstalled)?R.drawable.cancel:R.drawable.cloud;
        viewHolder.actionButton.setGravity(Gravity.CENTER);
        viewHolder.actionButton.setText(text);

        viewHolder.actionButton.setTag(position);
        viewHolder.actionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), false, false, row);
            }
        });

        viewHolder.deleteButton.setTag(position);
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), false, true, row);
            }
        });

        viewHolder.imageImageView.setTag(position);

        viewHolder.imageImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true, false, row);
            }
        });

        viewHolder.releaseDateTextView.setTag(position);

        viewHolder.releaseDateTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true,false, row);
            }
        });

        viewHolder.platformTextView.setTag(position);

        viewHolder.platformTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true, false, row);
            }
        });

        viewHolder.titleTextView.setTag(position);

        viewHolder.titleTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true, false, row);
            }
        });

        viewHolder.barView.setBackgroundColor(Color.GRAY);

        viewHolder.titleTextView.setTextColor(Color.BLACK);
        viewHolder.titleTextView.setAlpha(40);
        viewHolder.titleTextView.setText(game.name);

        viewHolder.releaseDateTextView.setTextColor(Color.BLACK);
        viewHolder.releaseDateTextView.setAlpha(40);
        viewHolder.releaseDateTextView.setText(game.releaseDate);

        viewHolder.platformTextView.setTextColor(Color.BLACK);
        viewHolder.platformTextView.setAlpha(40);
        viewHolder.platformTextView.setText(game.platform);

        viewHolder.imageImageView.setBackgroundColor(android.graphics.Color.BLACK);
        viewHolder.imageImageView.getBackground().setAlpha(102);

        Ion.with(viewHolder.imageImageView)
                .load(game.getThumbnail());

        return row;
    }

    private void getClickProperties(Integer tag, Boolean isDetail, Boolean isDelete, LinearLayout row) {
        if (mClickListener != null) {
            mClickListener.onBtnClick(tag, isDetail, isDelete, row);
        }
    }

    private class ViewHolder {
        protected TextView titleTextView;
        protected TextView platformTextView;
        protected ImageView imageImageView;
        protected TextView releaseDateTextView;
        protected Button actionButton;
        protected View barView;
        protected LinearLayout checkBoxList;
        protected CheckBox boxCheckbox;
        protected CheckBox cartCheckbox;
        protected CheckBox manualCheckbox;
        protected Button deleteButton;
    }
}
