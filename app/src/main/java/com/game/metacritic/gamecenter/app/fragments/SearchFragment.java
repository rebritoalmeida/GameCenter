package com.game.metacritic.gamecenter.app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.activities.ShelfCollectionActivity;
import com.game.metacritic.gamecenter.app.activities.ShelfSearchActivity;
import com.game.metacritic.gamecenter.app.data.models.FindGameRequest;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameResponse;
import com.game.metacritic.gamecenter.app.data.models.ResultGame;
import com.game.metacritic.gamecenter.app.data.models.enums.PlatformType;
import com.game.metacritic.gamecenter.app.networking.FindGameService;
import com.game.metacritic.gamecenter.app.networking.SearchGameService;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.game.metacritic.gamecenter.app.utils.interfaces.TaskCallback;

import java.util.ArrayList;

import quickutils.core.QuickUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 *
 */
public class SearchFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button searchGameButton;
    private Button myCollectionButton;

    private ArrayList<Game> gameList;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);


        //Define Add FlowGridItem Background
        searchGameButton = (Button) view.findViewById(R.id.search_game_button);
        searchGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchGameText = (EditText) view.findViewById(R.id.search_game_text);
                searchGameService(searchGameText.getText().toString());
            }
        });

        myCollectionButton = (Button) view.findViewById(R.id.my_collection_button);
        myCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.navigateTo(getActivity(), ShelfCollectionActivity.class);

            }
        });
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


    public void searchGameService(String gameSearch){
        new SearchGameService(getActivity(),gameSearch, new TaskCallback<GameResponse>() {
            @Override
            public void onSuccess(final GameResponse gameResp) {

                //todo: work on results


                final GameResponse gameResponse = gameResp;


                new FindGameService(getActivity(), gameResponse,new TaskCallback<ArrayList<Game>>() {
                    @Override
                    public void onSuccess(ArrayList<Game> gam) {
                        //gameList.add(gam.game);
                        gameList = gam;
                        gameResponse.results = gameList;
                        Utils.navigateTo(getActivity(), ShelfSearchActivity.class, Constants.GAME_RESPONSE_KEY, gameResponse);

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                }).execute();
             }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        }).execute();
    }

}
