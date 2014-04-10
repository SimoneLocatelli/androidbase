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

import com.androidbase.log.Log;
import com.androidbase.tasks.AsyncTaskEventArgs.AsyncResult;

public class WaitTask extends AsyncTask<Void, Void, Void> {

	// region Properties

	private final static String tag = "WaitTask";

	private final long durationInMs;

	private AsyncResult taskResult;

	private ITaskEnded listener;

	// endregion

	// region Constructors

	public WaitTask(long durationInMs) {

		this.durationInMs = (durationInMs < 0) ? 0 : durationInMs;

		taskResult = AsyncResult.OK;
	}

	public WaitTask(long durationInMs, ITaskEnded listener) {

		setOnTaskEndedListener(listener);

		this.durationInMs = (durationInMs < 0) ? 0 : durationInMs;

		taskResult = AsyncResult.OK;
	}

	// region endregion

	// Overrides

	@Override
	protected Void doInBackground(Void... arg) {

		try {
			Thread.sleep(durationInMs);
		} catch (InterruptedException ex) {
			taskResult = AsyncResult.ERROR;

			Log.w(WaitTask.tag, ex);
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {

		super.onPostExecute(result);

		if (listener != null) {
			listener.onTaskEnded(this, new AsyncTaskEventArgs(taskResult));
		}
	}

	// endregion

	// region Get / Set

	public void setOnTaskEndedListener(ITaskEnded listener) {

		this.listener = listener;
	}
	// endregion

}