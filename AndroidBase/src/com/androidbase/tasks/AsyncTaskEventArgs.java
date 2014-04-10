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

public class AsyncTaskEventArgs {

	// region Properties

	private AsyncResult result;

	// region Enums

	public enum AsyncResult {
		OK, ERROR
	}

	// endregion

	// region Constructors

	public AsyncTaskEventArgs(AsyncResult result) {

		setResult(result);
	}

	// endregion

	// region Get / Set

	public AsyncResult getResult() {

		return result;
	}

	protected void setResult(AsyncResult result) {

		this.result = result;
	}

	// endregion
}
