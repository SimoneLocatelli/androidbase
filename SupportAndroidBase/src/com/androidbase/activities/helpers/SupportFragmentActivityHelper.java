package com.androidbase.activities.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.androidbase.dotnet.alias.CS;

public class SupportFragmentActivityHelper extends FragmentActivityHelper {

	// region Constructors

	public SupportFragmentActivityHelper(SherlockFragmentActivity activity) {
		super(activity);
	}

	// endregion

	// region Methods

	public void addFragment(Fragment fragment, int container) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(container, fragment);
		fragmentTransaction.commit();
	}

	public void remove(Fragment fragment) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
	}

	public void replace(Fragment fragment, int container) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(container, fragment);
		fragmentTransaction.commit();
	}

	// endregion

	// region Get / Set

	@Override
	public SherlockFragmentActivity getActivity() {
		return CS.as(super.getActivity());
	}

	// endregion

}
