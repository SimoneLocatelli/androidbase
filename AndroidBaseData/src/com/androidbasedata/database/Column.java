package com.androidbasedata.database;

import java.lang.reflect.Field;

public final class Column {

	// region Properties

	private ColumnTypes type;
	private String name;
	private Field field;

	// endregion

	// region Constructors

	public Column(String name, ColumnTypes type, Field field) {

		this.type = type;
		this.name = name;
		this.field = field;
	}

	// endregion

	// region Get

	public ColumnTypes getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Field getField() {

		field.setAccessible(true);

		return field;
	}

	// endregion
}
