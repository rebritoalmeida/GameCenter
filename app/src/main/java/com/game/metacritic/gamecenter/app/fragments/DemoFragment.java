package com.game.metacritic.gamecenter.app.fragments;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.game.metacritic.gamecenter.app.R;
import com.game.metacritic.gamecenter.app.activities.NavigationDelegate;

public class DemoFragment extends Fragment {
	
	

	private NavigationDelegate mNavigationDelegate;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof NavigationDelegate){
			mNavigationDelegate = (NavigationDelegate)activity;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View retval = inflater.inflate(R.layout.fullscreenfragment, container, false);
		
		Button mBackButton = (Button)retval.findViewById(R.id.button1);
		mBackButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mNavigationDelegate.onNavigateBack();
				
			}
		});
		
		return retval;
	}

	
	
}
