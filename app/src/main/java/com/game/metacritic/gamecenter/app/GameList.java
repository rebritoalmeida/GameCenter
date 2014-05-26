package com.game.metacritic.gamecenter.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Rui Almeida on 26-05-2014.
 */
public class GameList extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity());

        //il.getInstance().init(config);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_list_fragment, container, false);
    }


}
