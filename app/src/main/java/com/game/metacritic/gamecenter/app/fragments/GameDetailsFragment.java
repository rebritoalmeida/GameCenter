package com.game.metacritic.gamecenter.app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameDAO;
import com.game.metacritic.gamecenter.app.utils.Constants;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import garin.artemiy.sqlitesimple.library.SQLiteSimple;
import garin.artemiy.sqlitesimple.library.util.SimpleDatabaseUtil;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import quickutils.core.QuickUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class GameDetailsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Game mGame;
    private View view;
    private TextView gameTitle;
    private TextView gamePlatform;
    private TextView gameReleaseDate;
    private TextView gameGenre;
    private TextView gameRate;
    private TextView gameUserScore;
    private TextView gameDeveloper;
    private TextView gameUrl;
    private Button addGame;
    private Button selectGame;
    private ImageView gameThumbnail;

    public static GameDetailsFragment newInstance(Game game) {
        GameDetailsFragment fragment = new GameDetailsFragment();
        fragment.mGame = game;
        return fragment;
    }

    public GameDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Utils.deleteInBD(getActivity());

        view = inflater.inflate(R.layout.fragment_game_details, container, false);


        gameThumbnail = (ImageView) view.findViewById(R.id.game_thumbnail_image_view);
        gameTitle = (TextView) view.findViewById(R.id.game_title_text_view);
        gamePlatform = (TextView) view.findViewById(R.id.game_platform_text_view);
        gameReleaseDate = (TextView) view.findViewById(R.id.release_date_text_view);
        gameGenre = (TextView) view.findViewById(R.id.genre_text_view);
        gameRate = (TextView) view.findViewById(R.id.rate_text_view);
        gameUserScore = (TextView) view.findViewById(R.id.user_score_text_view);
        gameDeveloper = (TextView) view.findViewById(R.id.game_developer_text_view);
        gameUrl = (TextView) view.findViewById(R.id.game_url_text_view);

        if (mGame.thumbnail != null) {
            Picasso.with(getActivity())
                    .load(mGame.thumbnail)
                    .into(gameThumbnail);
        }

        gameTitle.setText(gameTitle.getText().toString() + " " + mGame.name);
        gamePlatform.setText(gamePlatform.getText().toString() + " " + mGame.platform);
        gameReleaseDate.setText(gameReleaseDate.getText().toString() + " " + mGame.rlsdate);
        gameGenre.setText(gameGenre.getText().toString() + " " + mGame.genre);
        gameRate.setText(gameRate.getText().toString() + " " + mGame.rating);

        gameUserScore.setText(gameUserScore.getText().toString() + " " + mGame.userscore);

        if(Utils.intervallContains(Constants.LOW[0], Constants.LOW[1], mGame.userscore)){
            gameRate.setBackgroundColor(Color.RED);
        } else if(Utils.intervallContains(Constants.MEDIUM[0], Constants.MEDIUM[1], mGame.userscore)){
            gameRate.setBackgroundColor(Color.YELLOW);
        } else if(Utils.intervallContains(Constants.HIGHT[0], Constants.HIGHT[1], mGame.userscore)){
            gameRate.setBackgroundColor(Color.GREEN);
        }

        gameDeveloper.setText(gameDeveloper.getText().toString() + " " + mGame.developer);
        gameUrl.setText(gameUrl.getText().toString() + " " + mGame.url);

        gameUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(mGame.url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        addGame = (Button) view.findViewById(R.id.add_game_button);
        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utils.existsInBD(getActivity(),mGame) > 0){
                    QuickUtils.log.d("Exists");
                } else{
                    Utils.insertInBD(getActivity(), mGame);
                    QuickUtils.log.d("Added with success");
                }
            }
        });

        selectGame = (Button) view.findViewById(R.id.select_game_button);
        selectGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmResults<GameDAO> gam = Utils.selectInBD(getActivity());
                for(int i = 0; i < gam.size(); i++) {
                    QuickUtils.log.v("game name ->" + gam.get(i).getName());
                }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
