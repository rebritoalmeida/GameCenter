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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.adapters.GameArrayAdapter;
import com.game.metacritic.gamecenter.app.data.models.Game;
import com.game.metacritic.gamecenter.app.data.models.GameDAO;
import com.game.metacritic.gamecenter.app.utils.Utils;
import com.game.metacritic.gamecenter.app.utils.interfaces.AddGameButtonClickListener;


import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import quickutils.core.QuickUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShelfSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 *
 */
public class ShelfCollectionFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private View view;
    private ArrayAdapter<ArrayList<Game>> verticalAdapter;
    private ListView gameListView;
    ArrayList<ArrayList<Game>> mGroupList;
    private GameArrayAdapter gameAdapter;

    public ShelfCollectionFragment() {
        // Required empty public constructor
    }

    public static ShelfCollectionFragment newInstance() {
        ShelfCollectionFragment fragment = new ShelfCollectionFragment();
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

        RealmResults<GameDAO> gameDAOs = Utils.selectInBD(getActivity());
        //Library lb = new Library();
        ArrayList<Game> lb = new ArrayList<Game>();

        for(GameDAO game : gameDAOs){
            Game item = new Game(game.getId(), game.getName(), game.getScore(), game.getUrl(), game.getRlsdate(), game.getRating(), game.getPublisher(), game.getPlatform(), game.getGenre(), game.getThumbnail(), game.getUserscore(), game.getDeveloper(), game.isBox(), game.isCartridge(), game.isManual());

                lb.add(item);
        }

        setGameArrayAdapter(lb);
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

    public void setGameArrayAdapter(final List<Game> list) {
        gameAdapter = new GameArrayAdapter(getActivity(), list, true, new AddGameButtonClickListener() {
            @Override
            public void onBtnClick(int position, Boolean isDetail, Boolean isDelete, LinearLayout row) {
                if(!isDelete) {
                    Game selectedGame = list.get(position);

                    CheckBox check = (CheckBox) row.findViewById(R.id.checkManual);
                    CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkBox);
                    CheckBox checkCart = (CheckBox) row.findViewById(R.id.checkCartridge);

                    selectedGame.isManual = check.isChecked();
                    selectedGame.isBox = checkBox.isChecked();
                    selectedGame.isCartridge = checkCart.isChecked();
                    int result = Utils.updateInBD(getActivity(), selectedGame);
                    if(result > 0){
                        Toast toast = Toast.makeText(getActivity(), selectedGame.name +" Updated", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    QuickUtils.log.d("checkmanual" + check.isChecked() + "checkBox" + checkBox.isChecked() + "checkcart" + checkCart.isChecked());
                } else{
                    Toast toast = Toast.makeText(getActivity(), "Not yet implemented", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        gameListView.setAdapter(gameAdapter);
    }
}
