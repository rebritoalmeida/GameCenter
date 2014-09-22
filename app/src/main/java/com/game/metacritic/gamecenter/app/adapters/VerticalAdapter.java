package com.game.metacritic.gamecenter.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;

import java.util.ArrayList;

/**
 * Created by Rui on 22-09-2014.
 */
public class VerticalAdapter extends ArrayAdapter<ArrayList<Game>> {

    private int resource;

    public VerticalAdapter(Context _context, int _ResourceId,
                           ArrayList<ArrayList<Game>> _items) {
        super(_context, _ResourceId, _items);
        this.resource = _ResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        if (convertView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(resource,
                    null);
        } else {
            rowView = convertView;
        }

        HorizontalListView hListView = (HorizontalListView) rowView
                .findViewById(R.id.subListview);
        HorizontalAdapter horListAdapter = new HorizontalAdapter(
                getContext(), R.layout.item, getItem(position));
        hListView.setAdapter(horListAdapter);

        return rowView;
    }
}
