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
import com.androidbase.interfaces.ICopiable;
import com.androidbase.log.Log;

public class ObjectProperty extends PropertyBinding<Object> {

	// region Properties

	protected String tag = "ObjectProperty";

	// endregion

	// region Constructors

	public ObjectProperty(Object object, String propertyName) {
		super(object, propertyName);
	}

	public ObjectProperty(Object object, String propertyName, Bindings binding) {
		super(object, propertyName, binding);
	}

	// endregion

	// region Overrides

	@Override
	public void setValue(Object value) {

		try {
			Object fieldObject = getValue();

			if (fieldObject instanceof ICopiable) {
				((ICopiable) fieldObject).copy(value);
			} else {

				super.setValue(value);
			}

		} catch (Exception ex) {
			Log.e(tag, ex);
		}
	}

	@Override
	public void setValueWithouthRaise(Object value) {
		try {
			Object fieldObject = getValue();

			if (fieldObject instanceof ICopiable) {
				((ICopiable) fieldObject).copy(value);
			} else {

				super.setValueWithouthRaise(value);
			}

		} catch (Exception ex) {
			Log.e(tag, ex);
		}
	}

	// endregion

}
