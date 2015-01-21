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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.utils.interfaces.InstallGameButtonClickListener;
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
    private InstallGameButtonClickListener mClickListener = null;

    public GameArrayAdapter(Context context, List<Game> gamesList, InstallGameButtonClickListener installClickListener) {
        super(context, R.layout.game_row, gamesList);
        this.mContext = context;
        this.mGames = gamesList;
        this.mClickListener = installClickListener;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Game game = mGames.get(position);

        LinearLayout row;
        row = (LinearLayout) (convertView == null ? LayoutInflater.from(getContext()).inflate(R.layout.game_row, parent, false) : convertView);

        viewHolder = new ViewHolder();
        viewHolder.titleTextView = (TextView) row.findViewById(R.id.title_textview);
        viewHolder.imageImageView = (ImageView) row.findViewById(R.id.image_imagebutton);
        viewHolder.releaseDateTextView = (TextView) row.findViewById(R.id.release_date_textview);
        viewHolder.platformTextView = (TextView) row.findViewById(R.id.platform_textview);
        viewHolder.actionButton = (Button) row.findViewById(R.id.action_app_button);
        viewHolder.barView = (View) row.findViewById(R.id.left_bar);


        //Utils.setStoreActionButton(flow, viewHolder.actionButton);

        String text = "Add";
        int backgroundcolor = R.color.AddButton;
        //image = (flow.isInstalled)?R.drawable.cancel:R.drawable.cloud;

        int color = mContext.getResources().getColor(backgroundcolor);

        viewHolder.actionButton.setGravity(Gravity.CENTER);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(10);
        shape.setColor(color);
        viewHolder.actionButton.setBackgroundDrawable(shape);
        viewHolder.actionButton.setText(text);

        viewHolder.actionButton.setTag(position);

        viewHolder.actionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), false);
            }
        });

        viewHolder.imageImageView.setTag(position);

        viewHolder.imageImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true);
            }
        });

        viewHolder.releaseDateTextView.setTag(position);

        viewHolder.releaseDateTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true);
            }
        });

        viewHolder.platformTextView.setTag(position);

        viewHolder.platformTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true);
            }
        });

        viewHolder.titleTextView.setTag(position);

        viewHolder.titleTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getClickProperties((Integer) v.getTag(), true);
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

    private void getClickProperties(Integer tag, Boolean isDetail) {
        if (mClickListener != null) {
            mClickListener.onBtnClick(tag, isDetail);
        }
    }

    private class ViewHolder {
        protected TextView titleTextView;
        protected TextView platformTextView;
        protected ImageView imageImageView;
        protected TextView releaseDateTextView;
        protected Button actionButton;
        protected View barView;
    }
}
