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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.androidbase.R;
import com.androidbase.app.ABApplication;
import com.androidbase.binding.BindingManager;
import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.interfaces.IBindable;
import com.androidbase.binding.properties.PropertyBinding;
import com.androidbase.dotnet.workers.StringUtils;

public class ABEditText extends EditText implements IBindable {

	// region Properties

	protected BindingManager bindingManager;

	// endregion

	// region Constructors

	public ABEditText(Context context) {
		super(context);
	}

	public ABEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ABEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// endregion

	// region Overrides

	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);

		onBindingChanged(Bindings.Text);
	}

	// endregion

	// region IBindable

	@Override
	public void onBindingChanged(Bindings binding) {
		switch (binding) {
		case Text:
			getBindingManager().raise(binding, getText());
			break;
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

	// @Override
	// public void binding(Bindings binding, PropertyBinding<?> property) {
	//
	// switch (binding) {
	// case Text:
	// break;
	// default:
	// throw new IllegalArgumentException(
	// ABApplication
	// .getContext()
	// .getString(
	// R.string.ab_exceptions_binding_bindingTypeNotSupportd));
	// }
	//
	// getBindingManager().addBinding(binding, property);
	// onPropertyChanged(property);
	// }

	@Override
	public void onPropertyChanged(PropertyBinding bindingItem) {

		switch (bindingItem.getBinding()) {
		case Text:
			setText(StringUtils.getValueOrEmptyString(bindingItem.getValue()));
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
		super.setText(text, type);

		onBindingChanged(Bindings.Text);
	}

	public void setText(String text) {
		setText((CharSequence) text);
	}

	// endregion

}
