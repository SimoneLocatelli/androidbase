package com.androidbase.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.androidbase.activities.helpers.ActivityHelper;
import com.androidbase.injectors.ViewInjector;
import com.androidbase.interfaces.ILifeCycle;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbase.viewmodels.IViewModel;
import com.supportandroidbase.R;

public abstract class SupportBaseActivity extends SherlockActivity implements
		ILifeCycle, IViewModel {

	// region Properties

	protected String tag = "BaseActivity";

	protected SupportBaseActivity activity;

	private boolean logAndroidLifeCycle = false;

	private ActivityHelper activityHelper;

	// endregion

	// region Life Cycle

	protected final void onCreate(Bundle savedInstanceState,
			int layoutResource, int tagResource) {
		super.onCreate(savedInstanceState);

		activity = this;

		activityHelper = new ActivityHelper(activity);

		tag = getString(tagResource);

		setContentView(layoutResource);

		retrieveData(savedInstanceState);
		initializeLayout();
		initializeValues();
		attachEvents();
		initializingFinished();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		throw new RuntimeException(
				getString(R.string.ab_exceptions_activity_onCreate_cannotGetCalled));
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
