package com.androidbasedata.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import android.util.Log;

import com.wagnerandade.coollection.Coollection;
import com.wagnerandade.coollection.matcher.Matcher;

public class Mapper {

	// region Properties

	private static final String tag = "Mapper";

	// endregion

	// region Constructors

	public Mapper() {

	}

	// endregion

	// region Mapper with reflection methods...
	@SuppressWarnings("unchecked")
	public <O, D> D Map(O originEntity, Class<?> destinationClass) {
		Class<?> originClass = originEntity.getClass();

		D destinationEntity;
		try {
			destinationEntity = (D) destinationClass.newInstance();
		} catch (Exception ex) {

			Log.w(Mapper.tag, ex);
			return null;
		}

		// Get properties...

		List<Field> oProperties = Arrays
				.asList(originClass.getDeclaredFields());
		List<Field> dProperties = Arrays.asList(destinationClass
				.getDeclaredFields());

		// Get original values mappable...

		for (Field field : oProperties) {
			Field destinationField = Coollection.from(dProperties)
					.where("getName", Coollection.eq(field.getName()))
					.and("getType", new TypeMatcher(field.getType())).first();

			if (destinationField != null) {
				try {
					destinationField.setAccessible(true);
					field.setAccessible(true);

					destinationField.set(destinationEntity,
							field.get(originEntity));
				} catch (Exception ex) {
					Log.w(Mapper.tag, ex);
				}
			}
		}

		return destinationEntity;
	}

	// public List<D> Map<O, D>(List<O> originEntityList)
	// {
	// List<D> destinationEntityList = Activator.CreateInstance<List<D>>();
	//
	// //Get properties...
	// PropertyInfo[] oProperties = typeof(O).GetProperties();
	// PropertyInfo[] dProperties = typeof(D).GetProperties();
	//
	// //Get original values mappable...
	// foreach (O o in originEntityList)
	// {
	// D destinationEntity = Activator.CreateInstance<D>();
	// foreach (PropertyInfo oP in oProperties)
	// {
	// if (dProperties.FirstOrDefault(p => p.Name.ToLower() == oP.Name.ToLower()
	// && p.PropertyType == oP.PropertyType) != null)
	// {
	// typeof(D).GetProperty(oP.Name).SetValue(destinationEntity, oP.GetValue(o,
	// null), null);
	// }
	// }
	// destinationEntityList.Add(destinationEntity);
	// }
	// return destinationEntityList;
	// }
	// endregion

	// region Inner Class

	private class TypeMatcher implements Matcher

	{
		// region Properties

		private final Class<?> value;

		// endregion

		// region Constructors

		public TypeMatcher(Class<?> value) {
			this.value = value;
		}

		// endregion

		@Override
		public boolean match(Object value) {
			return this.value.equals(value);
		}

	}

	// endregion
}
