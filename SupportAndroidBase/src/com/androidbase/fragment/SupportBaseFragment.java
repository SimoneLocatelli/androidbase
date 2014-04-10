package com.androidbase.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbase.R;
import com.androidbase.injectors.ViewInjector;
import com.androidbase.interfaces.ILifeCycle;
import com.androidbase.viewmodels.IViewModel;

public abstract class SupportBaseFragment extends Fragment implements
		ILifeCycle, IViewModel {

	// region Properties

	protected String tag = "FragmentBase";

	protected SupportBaseFragment fragment;

	private boolean logAndroidLifeCycle = false;

	private View view;

	// endregion

	// region Life Cycle

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState, int layoutResource, int tagResource) {

		fragment = this;

		tag = getString(tagResource);

		view = inflater.inflate(layoutResource, container, false);

		// view.setVisibility(View.INVISIBLE);

		return view;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		throw new RuntimeException(
				getString(R.string.ab_exceptions_fragment_onCreateView_cannotGetCalled));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		retrieveData(savedInstanceState);
		initializeLayout();
		initializeValues();
		attachEvents();

		view.setVisibility(View.VISIBLE);
	}

	// endregion

	// region ILifeCycle

	@Override
	public void retrieveData(Bundle savedInstanceState) {
		ViewInjector.inject(getView(), getViewModel());
	}

	@Override
	public void initializeLayout() {
	}

	@Override
	public void initializeValues() {
	}

	@Override
	public void attachEvents() {
	}

	// endregion

}
