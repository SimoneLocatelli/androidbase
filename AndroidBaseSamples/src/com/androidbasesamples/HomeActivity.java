package com.androidbasesamples;

import android.app.Fragment;
import android.os.Bundle;

import com.androidbase.activities.BaseMasterDetailActivity;
import com.androidbase.fragments.MasterFragment;
import com.androidbase.viewmodels.BaseViewModel;
import com.entity.HomeMenuItem;
import com.fragments.details.BindingSample1Fragment;
import com.fragments.details.BindingSample2Fragment;
import com.fragments.details.HelloWorldFragment;
import com.fragments.master.HomeMasterFragment;

public class HomeActivity extends BaseMasterDetailActivity<HomeMenuItem> {

	// region Properties

	private HomeDetailFragment detailFragment;

	// endregion

	// region Life Cycle

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_home,
				R.string.ab_exceptions_activity_onCreate_cannotGetCalled);
	}

	// endregion

	// region Get / Set

	@Override
	public BaseViewModel getViewModel() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected MasterFragment<HomeMenuItem> getMasterFragment() {
		return new HomeMasterFragment();
	}

	@Override
	protected Fragment getDetailFragment(HomeMenuItem item) {

		switch (item.getId()) {
		case HomeMenuItem.ID_HelloWorld:
			return new HelloWorldFragment();
		case HomeMenuItem.ID_BindingSample1:
			return new BindingSample1Fragment();
		case HomeMenuItem.ID_BindingSample2:
			return new BindingSample2Fragment();
		}

		throw new RuntimeException("Id not recognized");

		//
		//
		// if (isInTwoPaneMode()) {
		// if (detailFragment == null) {
		// detailFragment = new HomeDetailFragment();
		// }
		//
		// return detailFragment;
		// }
		//
		// return null;
		// (DetailFragment<HomeMenuItem>) getFragmentManager()
		// .findFragmentById(R.id.activity_home_fragment_detail);
	}
	// endregion

}
