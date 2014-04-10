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
import java.util.List;

import com.androidbase.binding.BindingItem;
import com.wagnerandade.coollection.Coollection;

public class BindingCollection extends ArrayList<BindingItem<?>> {

	// region UID

	private static final long serialVersionUID = -4079513530930696072L;

	// endregion

	// region Methods

	public List<BindingItem<?>> getByPropertyName(String propertyName) {

		return Coollection.from(this)
				.where("getPropertyName", Coollection.eq(propertyName)).all();
	}

	// endregion

}
