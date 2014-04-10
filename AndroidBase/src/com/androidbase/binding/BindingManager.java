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

import java.util.HashMap;

import com.androidbase.binding.collections.PropertyCollection;
import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.properties.PropertyBinding;

public class BindingManager {

	// region Properties

	private HashMap<Bindings, PropertyCollection> bindings;

	// endregion

	// region Methods

	public void addBinding(Bindings binding, PropertyBinding<?> propertyBinding) {

		PropertyCollection propertyCollection = getPropertyCollection(binding);

		if (propertyCollection == null) {

			propertyCollection = new PropertyCollection();

			bindings.put(binding, propertyCollection);
		}

		propertyCollection.add(propertyBinding);
	}

	public void raise(Bindings binding, Object value) {

		PropertyCollection propertyCollection = getPropertyCollection(binding);

		if (propertyCollection != null) {
			propertyCollection.updateProperties(value);
		}

	}

	// endregion

	// region Get / Set

	protected PropertyCollection getPropertyCollection(Bindings binding) {
		if (bindings == null) {
			bindings = new HashMap<Bindings, PropertyCollection>();
		}

		if (!bindings.containsKey(binding)) {
			return null;
		}

		return bindings.get(binding);
	}

	// endregion

}
