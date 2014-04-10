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
package com.androidbase.views;

import java.util.Locale;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.androidbase.R;
import com.androidbase.app.ABApplication;
import com.androidbase.binding.BindingManager;
import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.interfaces.IBindable;
import com.androidbase.binding.properties.PropertyBinding;
import com.androidbase.dotnet.workers.StringUtils;
import com.androidbase.style.FontsHelper;

public class ABTextView extends TextView implements IBindable {

	// region Properties

	private boolean isUppercase = false;

	private String fontName = null;

	private Typeface typeFace = null;

	private BindingManager bindingManager;

	// endregion

	// region Constructors

	public ABTextView(Context context) {
		super(context);
	}

	public ABTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		loadAttributes(attrs);
		init();
	}

	public ABTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		loadAttributes(attrs);
		init();
	}

	// endregion

	// region Methods

	private void loadAttributes(AttributeSet attrs) {

		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.TextStyle);

		final int N = a.getIndexCount();
		for (int i = 0; i < N; ++i) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.TextStyle_uppercase:
				isUppercase = a.getBoolean(attr, false);

				break;
			case R.styleable.TextStyle_font:
				fontName = a.getString(attr);
				break;
			}
		}
		a.recycle();
	}

	private void init() {

		if (isUppercase) {
			setText(getText().toString());
		}

		initFont();
	}

	protected void initFont() {
		if (!StringUtils.isNullOrWhiteSpace(fontName) && !isInEditMode()) {
			typeFace = FontsHelper.loadFont(fontName);
			setTypeface(typeFace);
		}
	}

	// endregion

	// region IBindable

	@Override
	public void onBindingChanged(Bindings binding) {
		switch (binding) {
		case Text:
			getBindingManager().raise(binding,
					StringUtils.getValueOrEmptyString(getText()));
			break;
		default:
			throw new RuntimeException(getContext().getResources().getString(
					R.string.ab_exceptions_binding_bindingTypeNotSupportd));
		}
	}

	@Override
	public void binding(PropertyBinding property) {
		// binding(property.getBinding(), property);

		switch (property.getBinding()) {
		case Text:
			break;
		default:
			throw new IllegalArgumentException(
					ABApplication
							.getContext()
							.getString(
									R.string.ab_exceptions_binding_bindingTypeNotSupportd));
		}

		getBindingManager().addBinding(property.getBinding(), property);

		onPropertyChanged(property);
	}

	@Override
	public void onPropertyChanged(PropertyBinding propertyBinding) {

		switch (propertyBinding.getBinding()) {
		case Text:
			setText(StringUtils.getValueOrEmptyString(propertyBinding
					.getValue()));
			break;
		default:
			throw new RuntimeException(getContext().getResources().getString(
					R.string.ab_exceptions_binding_bindingTypeNotSupportd));

		}

	}

	// endregion

	// region Get / Set

	public BindingManager getBindingManager() {
		if (bindingManager == null) {
			bindingManager = new BindingManager();
		}

		return bindingManager;
	}

	@Override
	public void setText(CharSequence text, BufferType type) {

		String safeText = StringUtils.getValueOrEmptyString(text);

		safeText = (isUppercase) ? safeText.toUpperCase(Locale.getDefault())
				: safeText;

		super.setText(safeText, type);

		onBindingChanged(Bindings.Text);
	}

	public void setText(Object value) {
		setText(value.toString());
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		initFont();
	}
	// endregion

}
