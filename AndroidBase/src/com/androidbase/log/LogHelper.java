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
package com.androidbase.log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogHelper<T> {

	// region Properties

	private ArrayList<LogElement> logElements;

	private final String tag;

	// endregion

	// region Constructors

	public LogHelper(String tag) {

		this.tag = tag;
	}

	// endregion

	// region Methods

	private ArrayList<LogElement> getLogElements(T obj) {

		Class<?> cls = obj.getClass();

		final ArrayList<LogElement> logElements = new ArrayList<LogElement>();

		while (cls != Object.class) {

			final List<Method> allMethods = new ArrayList<Method>(
					Arrays.asList(cls.getDeclaredMethods()));

			for (final Method method : allMethods) {

				if (method.isAnnotationPresent(LogAnnotation.class)) {
					LogAnnotation logAnnotation = method
							.getAnnotation(LogAnnotation.class);

					logElements
							.add(new LogElement(logAnnotation.name(), method));

				}

			}

			cls = cls.getSuperclass();
		}

		return logElements;
	}

	public void log(T obj) {

		if (logElements == null) {
			logElements = getLogElements(obj);
		}

		Log.i(tag, "------------------------");
		Log.i(tag, "class: " + obj.getClass().getName());

		for (LogElement item : logElements) {

			try {
				Log.i(tag, item.name + ": " + (item.method.invoke(obj)));
			} catch (Exception ex) {
				Log.w(tag, ex);
			}
		}
		Log.i(tag, "------------------------");

	}

	public void log(ArrayList<T> list) {

		Log.i(tag, "------------------------");
		Log.i(tag, "Items count: " + list.size());

		if (list.size() > 0) {
			if (logElements == null) {
				logElements = getLogElements(list.get(0));
			}

			Log.i(tag, "class: " + list.get(0).getClass().getName());

			int i = 0;

			for (T item : list) {

				Log.i(tag, "index: " + i);
				i++;
				for (LogElement element : logElements) {

					try {
						Log.i(tag,
								element.name + ": "
										+ (element.method.invoke(item)));
					} catch (Exception ex) {
						Log.w(tag, ex);
					}
				}
			}

		}

		Log.i(tag, "------------------------");

	}

	public static <T> void log(String tag, T obj) {

		if (obj != null) {
			new LogHelper<T>(tag).log(obj);
		}
	}

	public static <T> void log(String tag, ArrayList<T> obj) {
		if (obj != null) {
			new LogHelper<T>(tag).log(obj);
		}
	}

	// endregion

	// region Internal Class

	private class LogElement {

		// region Properties

		private final String name;
		private final Method method;

		// endregion

		// region Constructors

		public LogElement(String name, Method method) {

			this.name = name;
			this.method = method;
		}

		// endregion
	}

	// endregion

}
