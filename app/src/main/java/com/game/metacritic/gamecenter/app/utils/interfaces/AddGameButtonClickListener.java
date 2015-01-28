package com.game.metacritic.gamecenter.app.utils.interfaces;

import android.widget.LinearLayout;

/**
 * Created by rui almeida on 06/08/2014.
 */
public interface AddGameButtonClickListener {
    public abstract void onBtnClick(int position, Boolean isDetail, Boolean isDelete,  LinearLayout row);
}
