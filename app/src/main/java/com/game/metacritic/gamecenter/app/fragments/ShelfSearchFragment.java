package com.game.metacritic.gamecenter.app.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.adapters.VerticalAdapter;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.data.models.Library;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShelfSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 *
 */
public class ShelfSearchFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private View view;
    private ArrayAdapter<ArrayList<Game>> verticalAdapter;
    private ListView gameListView;
    private GameResponse mGameResponse;

    public ShelfSearchFragment() {
        // Required empty public constructor
    }

    public static ShelfSearchFragment newInstance(GameResponse gameResponse) {
        ShelfSearchFragment fragment = new ShelfSearchFragment();
        fragment.mGameResponse = gameResponse;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shelf_search, container, false);
        gameListView = (ListView) view.findViewById(R.id.game_request_listview);

        /*
		 * Calling Library & BookItem classes for create list of groups
		 *  groupbyArrayBookItem return back array of array of items
		 */
        Library lb = new Library();
        for (Game item : mGameResponse.results) {
            lb.addGameItem(item);
        }

        ArrayList<ArrayList<Game>> groupList;
        groupList = lb.groupbyArrayBookItem(Library.AUTHOR);

        verticalAdapter  = new VerticalAdapter(view.getContext(), R.layout.shelf_row, groupList);
        gameListView.setAdapter(verticalAdapter);
        gameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Utils.navigateTo(getActivity(), NewsDetailsActivity.class, Constants.NEWS_ITEM_KEY, listNews.get(position));
            }
        });
        verticalAdapter.notifyDataSetChanged();

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    public interface OnFragmentInteractionListenerl {
    }
}
