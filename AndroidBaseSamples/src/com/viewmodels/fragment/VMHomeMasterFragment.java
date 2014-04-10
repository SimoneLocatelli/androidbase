package com.viewmodels.fragment;

import java.util.ArrayList;

import android.app.Activity;

import com.adapters.HomeMenuAdapter;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbasesamples.R;
import com.entity.HomeMenuItem;

public class VMHomeMasterFragment extends BaseViewModel {

	// region Properties

	private HomeMenuAdapter adapter;

	// endregion

	// region Constructors

	public VMHomeMasterFragment(Activity activity) {
		super(activity);
	}

	// endregion

	// region Get / Set

	public HomeMenuAdapter getAdapter() {

		if (adapter == null) {

			adapter = new HomeMenuAdapter(getActivity());

			String[] headers = getActivity().getResources().getStringArray(
					R.array.home_menu_items);

			ArrayList<HomeMenuItem> items = new ArrayList<HomeMenuItem>();

			for (int i = 0; i < headers.length; i++) {
				HomeMenuItem item = new HomeMenuItem(HomeMenuItem.IDs[i],
						headers[i]);
				items.add(item);
			}

			adapter.updateItems(items);
		}

		return adapter;
	}

	// endregion

}
