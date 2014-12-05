package com.game.metacritic.gamecenter.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.activities.GameDetailsActivity;
import com.game.metacritic.gamecenter.app.activities.SearchActivity;
import com.game.metacritic.gamecenter.app.activities.ShelfCollectionActivity;
import com.game.metacritic.gamecenter.app.activities.ShelfSearchActivity;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import quickutils.core.QuickUtils;

/**
 * Created by Rui on 22-09-2014.
 */
class HorizontalAdapter extends ArrayAdapter<Game> {

    private int resource;
    private ArrayList<Game> mGames;
    private Context mContext;

    public HorizontalAdapter(Context _context, int _textViewResourceId,
                             ArrayList<Game> _items) {
        super(_context, _textViewResourceId, _items);
        this.resource = _textViewResourceId;
        this.mGames = _items;
        this.mContext = _context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View retval = LayoutInflater.from(getContext()).inflate(this.resource, null);



        ImageView icon = (ImageView) retval.findViewById(R.id.icon);
        //todo : remove on click
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.navigateTo((ShelfSearchActivity)mContext, GameDetailsActivity.class, Constants.GAME_KEY, mGames.get(position));
            }
        });

        TextView topText = (TextView) retval.findViewById(R.id.title);
        TextView bottomText = (TextView) retval
                .findViewById(R.id.author);

        if(mGames.get(position) != null) {
            QuickUtils.log.v(mGames.get(position).thumbnail);
            Picasso.with(mContext)
                    .load(mGames.get(position).thumbnail)
                    .into(icon);


            topText.setText(mGames.get(position).name);
            bottomText.setText(mGames.get(position).platform);
        }
        topText.setTextSize(10);
        bottomText.setTextSize(10);
        return retval;
    }
}