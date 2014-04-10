package com.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbase.fragments.BaseFragment;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbasesamples.R;

public class HelloWorldFragment extends BaseFragment {

	// region Life Cycle

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState,
				R.layout.fragment_helloworld, R.string.tag_fragment_helloWorld);

	}

	// endregion

	// region Get / Set

	@Override
	public BaseViewModel getViewModel() {
		return null;
	}

	// endregion

}
