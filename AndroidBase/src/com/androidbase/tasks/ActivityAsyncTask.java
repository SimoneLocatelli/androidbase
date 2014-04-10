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
package com.androidbase.tasks;

import android.os.AsyncTask;
import android.os.Bundle;

import com.androidbase.interfaces.ILifeCycle;

public class ActivityAsyncTask extends AsyncTask<Bundle, Void, Void> {

	// region Properties

	private ILifeCycle iLifeCycle;

	// endregion

	// region Constructors

	public ActivityAsyncTask(ILifeCycle iLifeCycle) {

		this.iLifeCycle = iLifeCycle;
	}

	// endregion

	// region Methods
	@Override
	protected Void doInBackground(Bundle... savedInstanceState) {

		iLifeCycle.retrieveData(savedInstanceState[0]);

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);

		iLifeCycle.initializeLayout();
		iLifeCycle.initializeValues();
		iLifeCycle.attachEvents();
		iLifeCycle.initializingFinished();
	}

	// endregion

}
