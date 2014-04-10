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
package com.androidbase.viewmodels;

import android.app.Activity;

public class BaseViewModel extends BaseNotify {

	// region Enums

	public enum ActivityState {
		FirstLoading, NoConnection, Error, Loaded, Updating
	}

	// endregion

	// region Properties

	private ActivityState activityState = ActivityState.FirstLoading;

	private Activity activity;

	// endregion

	// region Constructors

	public BaseViewModel() {

	}

	public BaseViewModel(Activity activity) {
		setActivity(activity);
	}

	// endregion

	// region Get / Set

	public ActivityState getActivityState() {
		return activityState;
	}

	public void setActivityState(ActivityState activityState) {
		this.activityState = activityState;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	// endregion
}
