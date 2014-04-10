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
package com.androidbase.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

public abstract class ABApplication extends Application {

	// region Properties

	private static volatile Context context;
	private static volatile ABApplication application;

	private static volatile Activity activity;

	protected String tag = "ApplicationBase";

	// endregion

	// region Life Cycle

	@Override
	public void onCreate() {

		super.onCreate();

		ABApplication.application = this;

		ABApplication.context = getApplicationContext();

		initApplication();
	}

	// endregion

	// region Methods

	protected void initApplication() {

	}

	// endregion

	// region Get / Set

	public static ABApplication getApplication() {

		return ABApplication.application;
	}

	public static Context getContext() {

		return ABApplication.context;
	}

	public static LayoutInflater getStaticLayoutInflater() {
		return LayoutInflater.from(ABApplication.context);
	}

	public abstract ApplicationModes getApplicationMode();

	public static ApplicationModes getApplicationModeStatic() {
		return ABApplication.getApplication().getApplicationMode();
	}

	public static Activity getCurrentActivity() {
		return ABApplication.activity;
	}

	public static void setCurrentActivity(Activity activity) {
		ABApplication.activity = activity;
	}

	public static void clearCurrentActivity() {
		ABApplication.setCurrentActivity(null);
	}

	// endregion
}
