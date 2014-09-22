package com.game.metacritic.gamecenter.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rui on 22-09-2014.
 */
class HorizontalAdapter extends ArrayAdapter<Game> {

    private int resource;
    private ArrayList<Game> mGames;

    public HorizontalAdapter(Context _context, int _textViewResourceId,
                             ArrayList<Game> _items) {
        super(_context, _textViewResourceId, _items);
        this.resource = _textViewResourceId;
        this.mGames = _items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View retval = LayoutInflater.from(getContext()).inflate(this.resource, null);



        ImageView icon = (ImageView) retval.findViewById(R.id.icon);
        TextView topText = (TextView) retval.findViewById(R.id.title);
        TextView bottomText = (TextView) retval
                .findViewById(R.id.author);

       // Picasso.with(convertView.getContext())
         //       .load(mGames.get(position).url)
           //     .into(icon);

        topText.setText(mGames.get(position).name);
        bottomText.setText(mGames.get(position).platform);
        topText.setTextSize(10);
        bottomText.setTextSize(10);
        return retval;
    }
}