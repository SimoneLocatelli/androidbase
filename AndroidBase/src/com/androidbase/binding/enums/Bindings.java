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
package com.androidbase.binding.enums;

public enum Bindings {

	Text(0), SelectedItem(1);

	// region Properties

	private final int value;

	// endregion

	// region Constructors

	private Bindings(int value) {
		this.value = value;
	}

	// endregion

	// region Get / Set

	public int getValue() {
		return value;
	}

	// endregion

}
