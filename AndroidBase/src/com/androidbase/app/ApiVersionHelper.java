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

public final class ApiVersionHelper {

	// region Properties

	public enum Versions {
		Froyo(8), Gingerbread(9), Gingerbread_MR1(10), Honeycomb(11), Honeycomb_MR1(
				12), Honeycomb_MR2(13), IceCreamSandwich(14), IceCreamSandwich_MR1(
				15), JellyBean(16), JellyBean_MR1(17), JellyBean_MR2(18), KitKat(
				19);

		// region Properties

		private int versionCode;

		// endregion

		// region Constructors

		Versions(int versionCode) {
			this.versionCode = versionCode;
		}

		// endregion

		// region Get

		public int getVersionCode() {
			return versionCode;
		}

		// endregion
	}

	// endregion

	// region Constructors

	private ApiVersionHelper() {

	}

	// endregion

	// region Methods

	public static int getCurrentApiVersion() {

		return android.os.Build.VERSION.SDK_INT;
	}

	public static boolean isApiVersionSupported(Versions minimumApiVersion) {

		return (android.os.Build.VERSION.SDK_INT >= minimumApiVersion
				.getVersionCode());
	}

	public static boolean isApiVersionSupported(int minimumApiVersion) {

		return (android.os.Build.VERSION.SDK_INT >= minimumApiVersion);
	}

	// endregion
}
