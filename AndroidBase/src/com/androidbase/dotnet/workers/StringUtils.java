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
package com.androidbase.dotnet.workers;

public class StringUtils {

	// region Properties

	public static final String Empty = "";

	// endregion

	// region Constructors

	protected StringUtils() {

	}

	// endregion

	// region Methods

	// region isNullOrEmpty

	public static boolean isNullOrEmpty(Object value) {
		return (value == null) || StringUtils.isNullOrEmpty(value.toString());
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null) || value.length() == 0;
	}

	// endregion

	// region isNullOrWhiteSpace

	public static boolean isNullOrWhiteSpace(Object value) {
		return (value == null)
				|| StringUtils.isNullOrWhiteSpace(value.toString());
	}

	public static boolean isNullOrWhiteSpace(String value) {
		return (value == null) || value.trim().length() == 0;
	}

	// endregion

	// region getValueOrEmptyString

	public static String getValueOrEmptyString(Object value) {
		return (value == null) ? StringUtils.Empty : StringUtils
				.getValueOrEmptyString(value.toString());
	}

	public static String getValueOrEmptyString(String value) {
		return (value == null) ? StringUtils.Empty : value;
	}

	// endregion

	// endregion

}
