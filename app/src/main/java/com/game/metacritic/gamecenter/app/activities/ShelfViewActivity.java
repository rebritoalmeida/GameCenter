package com.game.metacritic.gamecenter.app.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.adapters.HorizontalListView;
import com.game.metacritic.gamecenter.app.data.models.Library;

import java.util.ArrayList;

public class ShelfViewActivity extends ListActivity {
    /** Called when the activity is first created. */
    private VerticalAdapter verListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_search);
 
		/*
		 * Calling Library & BookItem classes for create list of groups
		 *  groupbyArrayBookItem return back array of array of items
		 */
        Library lb = new Library();
        ArrayList<GameResponse> games = new ArrayList<GameResponse>();
        for (GameResponse item : games) {
            lb.addBookItem(item);
        }
        ArrayList<ArrayList<GameResponse>> groupList =
                new ArrayList<ArrayList<GameResponse>>();
        groupList = lb.groupbyArrayBookItem(Library.AUTHOR);

        verListAdapter = new VerticalAdapter(this, R.layout.shelf_row, groupList);
        setListAdapter(verListAdapter);

        verListAdapter.notifyDataSetChanged();
    }

    /**
     * This class add a list of ArrayList to ListView that it include multi
     * items as bookItem.
     */
    private class VerticalAdapter extends ArrayAdapter<ArrayList<GameResponse>> {

        private int resource;

        public VerticalAdapter(Context _context, int _ResourceId,
                               ArrayList<ArrayList<GameResponse>> _items) {
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

    /*
     * This class add some items to Horizontal ListView this ListView include
     * several bookItem.
     */
    private class HorizontalAdapter extends ArrayAdapter<GameResponse> {

        private int resource;

        public HorizontalAdapter(Context _context, int _textViewResourceId,
                                 ArrayList<GameResponse> _items) {
            super(_context, _textViewResourceId, _items);
            this.resource = _textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(getContext()).inflate(
                    this.resource, null);

            TextView topText = (TextView) retval.findViewById(R.id.title);
            TextView bottomText = (TextView) retval
                    .findViewById(R.id.author);

            topText.setText("autor");
            bottomText.setText("title");

            return retval;
        }
    }
}