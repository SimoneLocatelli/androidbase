/*
 * Copyright 2013 Simone Locatelli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidbase.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbase.R;
import com.androidbase.fragments.helpers.FragmentHelper;
import com.androidbase.injectors.ViewInjector;
import com.androidbase.interfaces.ILifeCycle;
import com.androidbase.viewmodels.IViewModel;

public abstract class BaseFragment extends Fragment implements ILifeCycle,
		IViewModel {

	// region Properties

	protected String tag = "FragmentBase";

	protected BaseFragment fragment;

	private boolean logAndroidLifeCycle = false;

	private View view;

	private FragmentHelper fragmentHelper;

	// endregion

	// region Life Cycle

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState, int layoutResource, int tagResource) {

		fragment = this;

		tag = getString(tagResource);

		return inflater.inflate(layoutResource, container, false);
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

		fragmentHelper = new FragmentHelper(getActivity());

		retrieveData(savedInstanceState);
		initializeLayout();
		initializeValues();
		attachEvents();
	}

	// endregion

	// region Methods

	public void addFragment(Fragment fragment, int container) {
		fragmentHelper.addFragment(fragment, container);
	}

	public void remove(Fragment fragment) {
		fragmentHelper.remove(fragment);
	}

	public void replace(Fragment fragment, int container) {
		fragmentHelper.replace(fragment, container);
	}

	// endregion

	// region ILifeCycle

	@Override
	public void retrieveData(Bundle savedInstanceState) {
	}

	@Override
	public void initializeLayout() {
		ViewInjector.inject(getView(), getViewModel());
	}

	@Override
	public void initializeValues() {
	}

	@Override
	public void attachEvents() {
	}

	@Override
	public void initializingFinished() {
	}

	// endregion

}
