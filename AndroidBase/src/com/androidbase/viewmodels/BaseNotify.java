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
package com.androidbase.viewmodels;

import com.androidbase.binding.collections.BindingCollection;

public class BaseNotify {

	// region Properties

	private BindingCollection bindingCollection = new BindingCollection();

	private static final String tag = "BaseNotify";

	// endregion

	// region Methods

	// protected void onPropertyChanged(String propertyName, Object value) {
	//
	// List<BindingItem<?>> bindingItems = bindingCollection
	// .getByPropertyName(propertyName);
	//
	// BindingItem<?> bindingItem;
	//
	// for (int i = 0, count = bindingItems.size(); i < count; i++) {
	// bindingItem = bindingItems.get(i);
	//
	// Class<?> cls = bindingItem.getBindableView().getClass();
	//
	// try {
	// Method method = cls.getMethod(bindingItem.getSetMethodName(),
	// value.getClass());
	//
	// method.invoke(bindingItem.getBindableView(), value);
	//
	// } catch (Exception ex) {
	// Log.w(BaseNotify.tag, ex);
	// }
	// }
	//
	// }
	//
	// public void binding(View view, String propertyName, String setMethodName)
	// {
	//
	// bindingCollection
	// .add(new BindingItem(view, propertyName, setMethodName));
	// }
}
