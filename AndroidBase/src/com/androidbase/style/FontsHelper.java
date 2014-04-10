package com.androidbase.style;

import java.util.HashMap;

import android.graphics.Typeface;

import com.androidbase.app.ABApplication;

public final class FontsHelper {

	// Properties

	// private static final String tag = "FontsHelper";

	private static HashMap<String, Typeface> fonts = new HashMap<String, Typeface>();

	// region Constructors

	private FontsHelper() {

	}

	// endregion

	// region Methods

	public static synchronized Typeface loadFont(String fontName) {

		if (FontsHelper.fonts.containsKey(fontName)) {
			return FontsHelper.fonts.get(fontName);
		}

		Typeface typeface = Typeface.createFromAsset(ABApplication.getContext()
				.getAssets(), fontName);

		FontsHelper.fonts.put(fontName, typeface);

		return typeface;
	}

	// endregion

}
