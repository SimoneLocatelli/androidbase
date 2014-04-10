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

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

import com.androidbase.R;
import com.androidbase.activities.helpers.ActivityHelper;
import com.androidbase.app.ABApplication;
import com.androidbase.injectors.ViewInjector;
import com.androidbase.interfaces.ILifeCycle;
import com.androidbase.tasks.ActivityAsyncTask;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbase.viewmodels.IViewModel;

public abstract class BaseActivityAsync extends Activity implements ILifeCycle,
		IViewModel {

	// region Properties

	protected String tag = "BaseActivity";

	protected BaseActivityAsync activity;

	private boolean logAndroidLifeCycle = false;

	private ActivityHelper activityHelper;

	private ActivityAsyncTask activityAsyncTask;

	// endregion

	// region Life Cycle

	protected final void onCreate(Bundle savedInstanceState,
			int layoutResource, int tagResource) {
		super.onCreate(savedInstanceState);

		ABApplication.setCurrentActivity(this);

		activity = this;

		activityHelper = new ActivityHelper(activity);

		tag = getString(tagResource);

		View view = getLayoutInflater().inflate(layoutResource, null);
		view.setVisibility(View.GONE);

		setContentView(view);

		setViewModelViews();

		activityAsyncTask = new ActivityAsyncTask(this);

		activityAsyncTask.execute(savedInstanceState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		throw new RuntimeException(
				getString(R.string.ab_exceptions_activity_onCreate_cannotGetCalled));
	}

	@Override
	protected void onResume() {
		super.onResume();

		ABApplication.setCurrentActivity(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(activity);
			return true;
		}

		return false;
	}

	@Override
	protected void onPause() {
		super.onPause();

		cancelAsyncTask(activityAsyncTask);
		ABApplication.clearCurrentActivity();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		ABApplication.clearCurrentActivity();
	}

	// endregion

	// region Methods

	// region ActivityHelper

	// region cancelAsyncTask

	public void cancelAsyncTask(AsyncTask<?, ?, ?> task) {
		if (task != null) {
			task.cancel(true);
		}
	}

	// endregion

	// region findViewById

	public <T extends View> T findViewByIdCasted(int id) {
		return activityHelper.findViewById(id);
	}

	// endregion

	// region Resources Methods

	public String[] getStringArray(int stringArrayResource) {
		return activityHelper.getStringArray(stringArrayResource);
	}

	// endregion

	// region setViewModelViews

	public void setViewModelViews() {
		ViewInjector.inject(activity, getViewModel());
	}

	// endregion

	// region startActivity

	public void startActivity(Class<?> activityClass) {
		activityHelper.startActivity(activityClass);
	}

	public void startActivityForResult(Class<?> activityClass, int requestCode) {
		activityHelper.startActivityForResult(activityClass, requestCode);
	}

	// endregion

	// endregion

	// endregion

	// region Get / Set

	@Override
	public abstract BaseViewModel getViewModel();

	// endregion

	// region ILifeCycle

	@Override
	public void retrieveData(Bundle savedInstanceState) {

	}

	@Override
	public void initializeLayout() {

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

}
