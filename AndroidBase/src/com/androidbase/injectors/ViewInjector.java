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
package com.androidbase.injectors;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;

import com.androidbase.annotations.AnnotationUtils;
import com.androidbase.annotations.FieldAnnotation;

public class ViewInjector {

	// region Constructors

	protected ViewInjector() {

	}

	// endregion

	// region Methods

	public static void inject(Activity activity, Object object) {
		if (activity == null || object == null) {
			return;
		}

		ArrayList<FieldAnnotation<ViewId>> annotations = AnnotationUtils
				.findFields(object, ViewId.class);

		for (FieldAnnotation<ViewId> item : annotations) {
			try {
				item.getField().set(
						object,
						item.getField()
								.getType()
								.cast(activity.findViewById(item
										.getAnnotation().id())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void inject(View view, Object object) {
		if (view == null || object == null) {
			return;
		}
		ArrayList<FieldAnnotation<ViewId>> annotations = AnnotationUtils
				.findFields(object, ViewId.class);

		for (FieldAnnotation<ViewId> item : annotations) {
			try {
				item.getField().set(
						object,
						item.getField()
								.getType()
								.cast(view.findViewById(item.getAnnotation()
										.id())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// endregion

}
