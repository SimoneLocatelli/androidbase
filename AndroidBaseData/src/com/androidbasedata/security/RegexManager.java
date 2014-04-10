package com.androidbasedata.security;

public final class RegexManager {

	// region Properties

	public static final String EmailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}"
			+ "\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\"
			+ ".)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	// endregion

	// region Constructors

	protected RegexManager() {

	}

	// endregion

	// region Methods

	public static boolean isValid(String input, String regex) {
		return input.matches(regex);
	}

	public static boolean isValidEmail(String input) {
		return input.matches(RegexManager.EmailRegex);
	}

	// endregion

}
