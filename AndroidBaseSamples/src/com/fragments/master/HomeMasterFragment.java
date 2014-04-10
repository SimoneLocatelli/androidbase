package com.fragments.master;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapters.HomeMenuAdapter;
import com.androidbase.fragments.MasterFragment;
import com.androidbasesamples.R;
import com.entity.HomeMenuItem;
import com.viewmodels.fragment.VMHomeMasterFragment;

public class HomeMasterFragment extends MasterFragment<HomeMenuItem> {

	// region Properties

	private VMHomeMasterFragment viewModel;

	// endregion

	// region Life Cycle

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState,
				R.string.tag_fragment_helloWorld);

	}

	// endregion

	// region Get / Set

	@Override
	public VMHomeMasterFragment getViewModel() {

		if (viewModel == null) {
			viewModel = new VMHomeMasterFragment(getActivity());
		}

		return viewModel;
	}

	@Override
	protected HomeMenuAdapter getAdapter() {

		return getViewModel().getAdapter();

	}
	// endregion

}
