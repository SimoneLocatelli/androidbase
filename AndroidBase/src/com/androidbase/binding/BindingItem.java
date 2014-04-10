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
package com.androidbase.binding;

import java.lang.reflect.Field;

import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.interfaces.IBindable;
import com.androidbase.log.Log;

public class BindingItem<T> {

	// region Properties

	private final static String tag = "BindingItem";

	private IBindable<T> bindableView;
	private Bindings binding;
	private Object object;
	private Field field;

	// endregion

	// region Constructors

	public BindingItem(IBindable<T> bindableView, Bindings binding,
			Object object, String fieldName) {
		this.bindableView = bindableView;
		this.binding = binding;

		this.object = object;

		try {
			field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
		} catch (NoSuchFieldException ex) {
			throw new RuntimeException(ex);
		}
	}

	// endregion

	// region Get / Set

	public Bindings getBinding() {
		return binding;
	}

	// endregion

	@SuppressWarnings("unchecked")
	public T getValue() {
		try {
			return (T) field.get(object);
		} catch (Exception ex) {
			Log.w(BindingItem.tag, ex);
		}
		return null;
	}

	public void setValue(T value) {
		try {
			field.set(object, value);
		} catch (Exception ex) {
			Log.e(BindingItem.tag, ex);
		}
	}

	public IBindable<T> getBindableView() {
		return bindableView;
	}

	public void setBindableView(IBindable<T> bindableView) {
		this.bindableView = bindableView;
	}
}
