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
import com.game.metacritic.gamecenter.app.activities.GameDetailsActivity;
import com.game.metacritic.gamecenter.app.activities.ShelfSearchActivity;
import com.game.metacritic.gamecenter.app.adapters.GameArrayAdapter;
import com.game.metacritic.gamecenter.app.adapters.VerticalAdapter;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.data.models.Library;
import com.game.metacritic.gamecenter.app.networking.GetGameService;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.game.metacritic.gamecenter.app.utils.interfaces.InstallGameButtonClickListener;
import com.game.metacritic.gamecenter.app.utils.interfaces.TaskCallback;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import quickutils.core.QuickUtils;

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
    private List<Game> mGameResponse;
    ArrayList<ArrayList<Game>> mGroupList;
    private GameArrayAdapter gameAdapter;
    private int pos;

    public ShelfSearchFragment() {
        // Required empty public constructor
    }

    public static ShelfSearchFragment newInstance(Game[] gameResponse) {
        ShelfSearchFragment fragment = new ShelfSearchFragment();
        fragment.mGameResponse = Arrays.asList(gameResponse);
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
        setGameArrayAdapter(mGameResponse);
        /*
		 * Calling Library & BookItem classes for create list of groups
		 *  groupbyArrayBookItem return back array of array of items
		 */
//        Library lb = new Library();
//        for (Game item : mGameResponse.results) {
//            lb.addGameItem(item);
//        }


//        mGroupList = lb.groupbyArrayBookItem(Library.AUTHOR);

//        verticalAdapter  = new VerticalAdapter(view.getContext(), R.layout.shelf_row, mGroupList);
//        gameListView.setAdapter(verticalAdapter);
//        verticalAdapter.notifyDataSetChanged();

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

    public void setGameArrayAdapter(final List<Game> list) {
        gameAdapter = new GameArrayAdapter(getActivity(), list, new InstallGameButtonClickListener() {
            @Override
            public void onBtnClick(int position, Boolean isDetail) {
                pos = position;
                if(list.get(position).id != "0"){
                    new GetGameService(getActivity(),list.get(position), new TaskCallback<Game>() {
                        @Override
                        public void onSuccess(Game gameResponse) {
                            Utils.navigateTo(getActivity(), GameDetailsActivity.class, Constants.GAME_KEY, gameResponse);
                        }
                        @Override
                        public void onFailure(Exception e) {
                            e.printStackTrace();
                        }
                    }).execute();
                }
            }
        });
        gameListView.setAdapter(gameAdapter);
    }
}
