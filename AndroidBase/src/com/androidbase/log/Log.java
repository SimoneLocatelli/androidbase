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

public class Log {

	// region Properties

	public static boolean enableLog = true;

	public static boolean enableLogDebug = true && Log.enableLog;

	public static boolean enableLogError = true && Log.enableLog;

	public static boolean enableLogInfo = true && Log.enableLog;

	public static boolean enableLogVerbose = true && Log.enableLog;

	public static boolean enableLogWarning = true && Log.enableLog;

	public static final String Separator = "---------------------------";

	// endregion

	// endregion

	// region Constructors

	protected Log() {

	}

	// endregion

	// region Methods

	// region Debug

	public static void d(String tag, String msg) {
		if (Log.enableLogDebug) {
			android.util.Log.d(tag, msg);
		}
	}

	public static void d(String tag, Throwable tr) {
		Log.d(tag, "Exception: ", tr);
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (Log.enableLogDebug) {
			android.util.Log.d(tag, msg, tr);
		}
	}

	// endregion

	// region Error

	public static void e(String tag, String msg) {
		if (Log.enableLogError) {
			android.util.Log.e(tag, msg);
		}
	}

	public static void e(String tag, Throwable tr) {
		Log.e(tag, "Exception: ", tr);
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (Log.enableLogError) {
			android.util.Log.e(tag, msg, tr);
		}
	}

	// endregion

	// region Info

	public static void i(String tag, String msg) {
		if (Log.enableLogInfo) {
			android.util.Log.i(tag, msg);
		}
	}

	public static void i(String tag, Throwable tr) {
		Log.i(tag, "Exception: ", tr);
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (Log.enableLogInfo) {
			android.util.Log.i(tag, msg, tr);
		}
	}

	// endregion

	// region Verbose

	public static void v(String tag, String msg) {
		if (Log.enableLogVerbose) {
			android.util.Log.v(tag, msg);
		}
	}

	public static void v(String tag, Throwable tr) {
		Log.v(tag, "Exception: ", tr);
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (Log.enableLogVerbose) {
			android.util.Log.v(tag, msg, tr);
		}
	}

	// endregion

	// region Warning

	public static void w(String tag, String msg) {
		if (Log.enableLogWarning) {
			android.util.Log.w(tag, msg);
		}
	}

	public static void w(String tag, Throwable tr) {
		Log.w(tag, "Exception: ", tr);
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (Log.enableLogWarning) {
			android.util.Log.w(tag, msg, tr);
		}
	}

	// endregion

	// endregion

}
