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
package com.androidbase.activities.helpers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.androidbase.annotations.AnnotationUtils;
import com.androidbase.annotations.FieldAnnotation;
import com.androidbase.app.ApiVersionHelper;
import com.androidbase.app.ApiVersionHelper.Versions;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.injectors.ViewId;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbase.viewmodels.IViewModel;

public class ActivityHelper {

	// region Properties

	private Activity activity;

	// endregion

	// region Constructors

	public ActivityHelper(Activity activity) {
		setActivity(activity);
	}

	// endregion

	// region Methods

	// region cancelAsyncTask

	public void cancelAsyncTask(AsyncTask<?, ?, ?> task) {
		if (task != null) {
			task.cancel(true);
		}
	}

	// endregion

	// region findViewById

	public <T extends View> T findViewById(int id) {
		T item = CS.as(getActivity().findViewById(id));

		return item;
	}

	// endregion

	// region setViewModelViews

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

	public String[] getStringArray(int stringArrayResource) {
		return getActivity().getResources().getStringArray(stringArrayResource);
	}

	// endregion

	// region startActivity

	public void startActivity(Class<?> activityClass) {
		getActivity().startActivity(new Intent(getActivity(), activityClass));
	}

	public void startActivityForResult(Class<?> activityClass, int requestCode) {
		getActivity().startActivityForResult(
				new Intent(getActivity(), activityClass), requestCode);
	}

	// endregion

	// region showUpButton

	public void showUpButton() {

		if (ApiVersionHelper.isApiVersionSupported(Versions.Honeycomb)) {

			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}

	}

	// endregion

	// endregion

	// region Get / Set

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	// endregion

}
