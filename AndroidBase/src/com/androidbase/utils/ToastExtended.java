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
package com.androidbase.utils;

import android.content.Context;
import android.widget.Toast;

import com.androidbase.app.ABApplication;

public class ToastExtended {

	// region Constructors

	protected ToastExtended() {
	}

	// endregion

	// region Methods

	public static void show(String text) {
		Toast.makeText(ABApplication.getContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

	public static void show(int resourceString) {
		Context context = ABApplication.getContext();
		Toast.makeText(context,
				context.getResources().getString(resourceString),
				Toast.LENGTH_SHORT).show();
	}

	// endregion

}
