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

import com.androidbase.binding.enums.Bindings;
import com.androidbase.log.Log;

public class StringProperty extends PropertyBinding<String> {

	// region Properties

	protected String tag = "StringProperty";

	// endregion

	// region Constructors

	public StringProperty(Object object, String propertyName, Bindings binding) {
		super(object, propertyName, binding);
	}

	// endregion

	// region Overrides

	@Override
	public void setValue(Object value) {
		try {
			super.setValue(value.toString());
		} catch (Exception ex) {
			Log.e(tag, ex);
		}
	}

	@Override
	public void setValueWithouthRaise(Object value) {
		try {
			super.setValueWithouthRaise(value.toString());
		} catch (Exception ex) {
			Log.e(tag, ex);
		}
	}

	// endregion

}
