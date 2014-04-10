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
package com.androidbase.binding.properties;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.interfaces.IBindable;
import com.androidbase.binding.interfaces.IPropertyChangedListener;
import com.androidbase.log.Log;

public abstract class PropertyBinding<T> {

	// region Properties

	private IBindable<T> bindableView;

	private Bindings binding;

	protected Object object;

	protected Field field;

	protected Class<?> cls;

	protected String tag = "ObjectProperty";

	protected ArrayList<IPropertyChangedListener<T>> propertyChangedListeners = null;

	// endregion

	// region Constructors

	public PropertyBinding(Object object, String propertyName) {

		this.object = object;

		try {
			field = object.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			cls = field.getType();
		} catch (Exception ex) {
			Log.w(tag, ex);
			throw new RuntimeException(ex);
		}
	}

	public PropertyBinding(Object object, String propertyName, Bindings binding) {

		this.object = object;
		this.binding = binding;

		try {
			field = object.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			cls = field.getType();
		} catch (Exception ex) {
			Log.w(tag, ex);
			throw new RuntimeException(ex);
		}
	}

	// endregion

	// region Methods

	@SuppressWarnings("unchecked")
	public T getValue() {
		try {
			return (T) field.get(object);
		} catch (Exception ex) {
			Log.w(tag, ex);
		}
		return null;
	}

	public void setValue(Object value) {
		try {
			field.set(object, value);

			if (bindableView != null) {
				bindableView.onPropertyChanged(this);
			}

			callPropertyChangedListeners();

		} catch (Exception ex) {
			Log.e(tag, ex);
		}
	}

	public void setValueWithouthRaise(Object value) {
		try {
			field.set(object, value);

			callPropertyChangedListeners();
		} catch (Exception ex) {
			Log.e(tag, ex);
		}
	}

	protected void callPropertyChangedListeners() {
		if (propertyChangedListeners != null) {
			for (IPropertyChangedListener<T> listener : propertyChangedListeners) {
				listener.onPropertyChanged(this);
			}
		}
	}

	// endregion

	// region Get / Set

	public Bindings getBinding() {
		return binding;
	}

	public PropertyBinding<T> setBinding(Bindings binding) {
		this.binding = binding;

		return this;
	}

	public IBindable<T> getBindableView() {
		return bindableView;
	}

	public PropertyBinding<T> setBindableView(IBindable<T> bindableView) {
		this.bindableView = bindableView;
		bindableView.binding(this);

		return this;
	}

	public void addPropertyChangedListener(IPropertyChangedListener<T> listener) {
		if (propertyChangedListeners == null) {
			propertyChangedListeners = new ArrayList<IPropertyChangedListener<T>>();
		}

		propertyChangedListeners.add(listener);
	}

	// endregion
}
