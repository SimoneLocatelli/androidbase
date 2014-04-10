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
package com.androidbase.binding.collections;

import java.util.ArrayList;

import com.androidbase.binding.properties.PropertyBinding;

public class PropertyCollection extends ArrayList<PropertyBinding<?>> {

	// region UID

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228050312461013627L;

	// endregion

	// region Methods

	public void updateProperties(Object value) {
		for (int i = 0, count = size(); i < count; i++) {
			get(i).setValueWithouthRaise(value);
		}
	}

	// endregion

}
