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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationUtils {

	// region Constructors

	protected AnnotationUtils() {

	}

	// endregion

	// region Methods

	@SuppressWarnings("unchecked")
	public static <T extends Annotation, I> ArrayList<MethodAnnotation<T>> findMethods(
			I item, Class<? extends Annotation> annotationClass) {

		final ArrayList<MethodAnnotation<T>> methods = new ArrayList<MethodAnnotation<T>>();

		Class<?> cls = item.getClass();

		while (cls != Object.class) {

			final List<Method> allMethods = new ArrayList<Method>(
					Arrays.asList(cls.getDeclaredMethods()));

			for (final Method method : allMethods) {

				if (method.isAnnotationPresent(annotationClass)) {
					T annotation = (T) method.getAnnotation(annotationClass);

					methods.add(new MethodAnnotation<T>(method, annotation));

				}
			}

			cls = cls.getSuperclass();
		}

		return methods;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Annotation, I> ArrayList<FieldAnnotation<T>> findFields(
			I item, Class<? extends Annotation> annotationClass) {

		final ArrayList<FieldAnnotation<T>> fields = new ArrayList<FieldAnnotation<T>>();

		Class<?> cls = item.getClass();

		while (cls != Object.class) {

			List<Field> allFields = new ArrayList<Field>(Arrays.asList(cls
					.getDeclaredFields()));

			for (Field field : allFields) {

				if (field.isAnnotationPresent(annotationClass)) {
					T annotation = (T) field.getAnnotation(annotationClass);

					fields.add(new FieldAnnotation<T>(field, annotation));

				}
			}

			cls = cls.getSuperclass();
		}

		return fields;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Annotation> FieldAnnotation<T> getAnnotation(
			Field field, Class<? extends Annotation> annotationClass) {

		if (field.isAnnotationPresent(annotationClass)) {
			T annotation = (T) field.getAnnotation(annotationClass);

			return new FieldAnnotation<T>(field, annotation);
		}

		return null;
	}

	// endregion
}