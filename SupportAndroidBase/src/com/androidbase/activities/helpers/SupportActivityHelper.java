package com.androidbase.activities.helpers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockExpandableListActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.injectors.ViewId;
import com.androidbase.utils.annotations.AnnotationUtils;
import com.androidbase.utils.annotations.FieldAnnotation;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbase.viewmodels.IViewModel;

public class SupportActivityHelper extends ActivityHelper {

	// region Properties

	private Activity activity;

	// endregion

	// region Constructors

	public SupportActivityHelper(Activity activity) {
		super(activity);
	}

	// endregion

	// region Methods

	// region cancelAsyncTask

	@Override
	public void cancelAsyncTask(AsyncTask<?, ?, ?> task) {
		if (task != null) {
			task.cancel(true);
		}
	}

	// endregion

	// region findViewById

	@Override
	public <T extends View> T findViewById(int id) {
		T item = CS.as(getActivity().findViewById(id));

		return item;
	}

	// endregion

	// region setViewModelViews

	@Override
	public void setViewModelViews() {
		BaseViewModel viewModel = ((IViewModel) getActivity()).getViewModel();

		ArrayList<FieldAnnotation<ViewId>> a = AnnotationUtils.findFields(
				viewModel, ViewId.class);

		for (FieldAnnotation<ViewId> item : a) {
			try {
				item.getField().set(
						viewModel,
						item.getField().getType()
								.cast(findViewById(item.getAnnotation().id())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// endregion

	// region Resources Methods

	@Override
	public String[] getStringArray(int stringArrayResource) {
		return getActivity().getResources().getStringArray(stringArrayResource);
	}

	// endregion

	// region startActivity

	@Override
	public void startActivity(Class<?> activityClass) {
		getActivity().startActivity(new Intent(getActivity(), activityClass));
	}

	@Override
	public void startActivityForResult(Class<?> activityClass, int requestCode) {
		getActivity().startActivityForResult(
				new Intent(getActivity(), activityClass), requestCode);
	}

	// endregion

	// region showUpButton

	@Override
	public void showUpButton() {

		if (getActivity() instanceof SherlockActivity) {

			((SherlockActivity) getActivity()).getSupportActionBar()
					.setDisplayHomeAsUpEnabled(true);

		} else if (getActivity() instanceof SherlockFragmentActivity) {

			((SherlockFragmentActivity) getActivity()).getSupportActionBar()
					.setDisplayHomeAsUpEnabled(true);

		} else if (getActivity() instanceof SherlockExpandableListActivity) {

			((SherlockExpandableListActivity) getActivity())
					.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		} else if (getActivity() instanceof SherlockListActivity) {

			((SherlockListActivity) getActivity()).getSupportActionBar()
					.setDisplayHomeAsUpEnabled(true);

		} else if (getActivity() instanceof SherlockPreferenceActivity) {
			((SherlockPreferenceActivity) getActivity()).getSupportActionBar()
					.setDisplayHomeAsUpEnabled(true);

		} else {
			super.showUpButton();
		}

	}

	// endregion

	// endregion

	// region Get / Set

	@Override
	public Activity getActivity() {
		return activity;
	}

	@Override
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	// endregion

}
