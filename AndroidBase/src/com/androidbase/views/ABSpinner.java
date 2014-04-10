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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.androidbase.R;
import com.androidbase.app.ABApplication;
import com.androidbase.binding.BindingManager;
import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.interfaces.IBindable;
import com.androidbase.binding.properties.PropertyBinding;

public class ABSpinner extends Spinner implements IBindable,
		OnItemSelectedListener {

	// region Properties

	private BindingManager bindingManager;

	// endregion

	// region Constructors

	public ABSpinner(Context context) {
		super(context);
		init();
	}

	public ABSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ABSpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	// endregion

	// region Overrides

	// endregion

	// region Methods

	private void init() {
		super.setOnItemSelectedListener(this);
	}

	// endregion

	// region IBindable

	@Override
	public void onBindingChanged(Bindings binding) {
		switch (binding) {
		case SelectedItem:
			getBindingManager().raise(binding, getSelectedItem());
			break;
		}
	}

	@Override
	public void binding(PropertyBinding property) {
		// binding(property.getBinding(), property);

		switch (property.getBinding()) {
		case SelectedItem:
			break;
		default:
			throw new IllegalArgumentException(
					ABApplication
							.getContext()
							.getString(
									R.string.ab_exceptions_binding_bindingTypeNotSupportd));
		}

		getBindingManager().addBinding(property.getBinding(), property);
	}

	// @Override
	// public void binding(Bindings binding, PropertyBinding<?> property) {
	//
	// switch (binding) {
	// case SelectedItem:
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
	// }

	@Override
	public void onPropertyChanged(PropertyBinding bindingItem) {

	}

	// endregion

	// region Get / Set

	public BindingManager getBindingManager() {
		if (bindingManager == null) {
			bindingManager = new BindingManager();
		}

		return bindingManager;
	}

	// endregion

	// region OnItemSelectedListener

	@Override
	public void onItemSelected(AdapterView<?> adapter, View view, int position,
			long id) {

		onBindingChanged(Bindings.SelectedItem);
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapter) {
	}

	// endregion

}
