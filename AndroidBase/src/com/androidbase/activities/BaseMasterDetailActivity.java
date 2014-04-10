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
package com.androidbase.activities;

import android.app.Fragment;
import android.os.Bundle;

import com.androidbase.R;
import com.androidbase.fragments.MasterFragment;
import com.androidbase.fragments.MasterFragment.OnItemSelectedCallback;
import com.androidbase.interfaces.ILifeCycle;
import com.androidbase.viewmodels.IViewModel;

public abstract class BaseMasterDetailActivity<T> extends BaseFragmentActivity
		implements ILifeCycle, IViewModel, OnItemSelectedCallback<T> {

	// region Properties

	private Boolean isInTwoPaneMode = null;

	private boolean isInDetail = false;

	// endregion

	// region Life Cycle

	@Override
	public void onBackPressed() {

		if (!isInTwoPaneMode() && isInDetail) {

			isInDetail = false;

			replace(getMasterFragment(),
					R.id.ab_masterDetailActivity_singlePane_container);
		} else {
			super.onBackPressed();
		}
	}

	// endregion

	// region Get / Set

	protected boolean isInTwoPaneMode() {
		if (isInTwoPaneMode == null) {
			isInTwoPaneMode = findViewById(R.id.ab_masterDetailActivity_twoPane_detailContainer) != null;
		}

		return isInTwoPaneMode;
	}

	public boolean isInDetail() {
		return isInDetail;
	}

	protected abstract MasterFragment<T> getMasterFragment();

	protected abstract Fragment getDetailFragment(T item);

	// endregion

	// region ILifeCycle

	@Override
	public void retrieveData(Bundle savedInstanceState) {

	}

	@Override
	public void initializeLayout() {

		if (isInTwoPaneMode()) {
			addFragment(getMasterFragment(),
					R.id.ab_masterDetailActivity_twoPane_listContainer);
		} else {
			addFragment(getMasterFragment(),
					R.id.ab_masterDetailActivity_singlePane_container);
		}

		setViewModelViews();
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

	// region OnItemSelectedCallback

	@Override
	public void onItemSelected(T item) {

		if (isInTwoPaneMode()) {
			replace(getDetailFragment(item),
					R.id.ab_masterDetailActivity_twoPane_detailContainer);
		} else {

			isInDetail = true;

			replace(getDetailFragment(item),
					R.id.ab_masterDetailActivity_singlePane_container);
		}
	}

	// endregion

}
