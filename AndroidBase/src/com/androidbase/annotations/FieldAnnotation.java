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
package com.androidbase.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class FieldAnnotation<T extends Annotation> {

	// region Properties

	private Field field;
	private T annotation;

	// endregion

	// region Constructors

	public FieldAnnotation(Field field, T annotation) {

		this.field = field;
		this.annotation = annotation;
	}

	// endregion

	// region Get / Set

	public Field getField() {

		field.setAccessible(true);

		return field;
	}

	public T getAnnotation() {
		return annotation;
	}

	// endregion
}