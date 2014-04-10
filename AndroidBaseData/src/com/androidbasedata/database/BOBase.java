package com.androidbasedata.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.androidbase.annotations.AnnotationUtils;
import com.androidbase.annotations.FieldAnnotation;

public abstract class BOBase<T extends BaseContract> {

	// region Properties

	protected static final String TEXT_TYPE = " TEXT";
	protected static final String COMMA_SEP = ",";

	protected final Class<T> contract;

	protected SQLiteDatabase sqLiteDatabase;
	protected SQLiteOpenHelper sqLiteOpenHelper;
	protected Cursor cursor;
	protected String tableName;

	protected String tag = "BOBase";

	// endregion

	// region Constructors

	public BOBase(Class<T> contract, String tag) {
		this.contract = contract;

		sqLiteOpenHelper = getDbCreator();
		this.tag = tag;
	}

	// endregion

	// region Methods

	public void open() {

		sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
	}

	public void close() {

		sqLiteDatabase.close();
	}

	public ArrayList<T> selectAll() {

		try {

			T contractInstance = contract.newInstance();

			Cursor cursor = sqLiteDatabase.query(
					contractInstance.getTableName(),
					contractInstance.getAllColumns(), null, // The
					// columns
					// for
					// the
					// WHERE
					// clause
					null, // The values for the WHERE clause
					null, // don't group the rows
					null, // don't filter by row groups
					null // The sort order
					);

			ArrayList<T> items = readItems(cursor);

			return items;

		} catch (Exception ex) {
			Log.w(tag, ex);
		}

		return null;
	}

	public boolean checkRecordExistanceById(T entity) {

		try {

			T contractInstance = contract.newInstance();

			Cursor cursor = sqLiteDatabase.query(
					contractInstance.getTableName(), new String[] { "ID" },
					"ID = ?", // The columns for the WHERE clause
					new String[] { entity.getId() + "" }, // The values for the
															// WHERE clause
					null, // don't group the rows
					null, // don't filter by row groups
					null // The sort order
					);

			return getRecordCount(cursor) > 0;

		} catch (Exception ex) {
			Log.w(tag, ex);

			throw new RuntimeException(ex);
		}
	}

	public boolean checkRecordExistance(String column, String value) {

		try {

			T contractInstance = contract.newInstance();

			Cursor cursor = sqLiteDatabase.query(
					contractInstance.getTableName(), new String[] { "ID" },
					column + "= ?", // The columns for the WHERE clause
					new String[] { value }, // The values for the
											// WHERE clause
					null, // don't group the rows
					null, // don't filter by row groups
					null // The sort order
					);

			return getRecordCount(cursor) > 0;

		} catch (Exception ex) {
			Log.w(tag, ex);

			throw new RuntimeException(ex);
		}
	}

	public void insert(T entity) {

		try {
			ContentValues contentValues = buildContentValues(entity);

			sqLiteDatabase.insert(entity.getTableName(), null, contentValues);
		} catch (Exception ex) {
			Log.w(tag, ex);
			throw new RuntimeException(ex);
		}
	}

	public T insertWithEntity(T entity) {
		try {

			ContentValues contentValues = buildContentValues(entity);

			long rowId = sqLiteDatabase.insert(entity.getTableName(), null,
					contentValues);

			return getItemByRowId(rowId);

		} catch (Exception ex) {
			Log.w(tag, ex);
			throw new RuntimeException(ex);

		}
	}

	public void update(T entity) {

		try {
			ContentValues contentValues = buildContentValues(entity);

			sqLiteDatabase.update(entity.getTableName(), contentValues,
					"ID = ?", new String[] { entity.getId() + "" });
		} catch (Exception ex) {
			Log.w(tag, ex);
			throw new RuntimeException(ex);
		}
	}

	public T updateWithEntity(T entity) {
		try {

			ContentValues contentValues = buildContentValues(entity);

			long rowId = sqLiteDatabase.update(entity.getTableName(),
					contentValues, "ID = ?",
					new String[] { entity.getId() + "" });

			return getItemByRowId(rowId);

		} catch (Exception ex) {
			Log.w(tag, ex);
			throw new RuntimeException(ex);

		}
	}

	// region Helpers

	protected ContentValues buildContentValues(T entity)
			throws IllegalAccessException, IllegalArgumentException {
		ArrayList<FieldAnnotation<DatabaseField>> annotations = AnnotationUtils
				.findFields(entity, DatabaseField.class);

		ContentValues contentValues = new ContentValues();

		for (FieldAnnotation<DatabaseField> fieldAnnotation : annotations) {

			fieldAnnotation.getField().setAccessible(true);

			contentValues.put(fieldAnnotation.getAnnotation().name(),
					fieldAnnotation.getField().get(entity).toString());

		}

		return contentValues;
	}

	protected T getItemByRowId(long rowId) throws Exception {
		T entity = contract.newInstance();

		cursor = sqLiteDatabase.rawQuery(
				"SELECT rowid, ID FROM " + entity.getTableName(), null);

		if (cursor.getCount() > 0) {
			return readItem(cursor, entity);
		} else {
			return null;
		}

	}

	protected T readItem(Cursor cursor) throws Exception {

		return readItem(cursor, contract.newInstance());
	}

	protected T readItem(Cursor cursor, T entity) throws Exception {

		Column[] columns = new Column[cursor.getColumnCount()];

		ArrayList<FieldAnnotation<DatabaseField>> annotations = AnnotationUtils
				.findFields(contract.newInstance(), DatabaseField.class);

		for (FieldAnnotation<DatabaseField> fieldAnnotation : annotations) {
			DatabaseField annotation = fieldAnnotation.getAnnotation();

			int columnIndex = cursor.getColumnIndex(annotation.name());

			if (columnIndex != -1) {
				Column column = new Column(annotation.name(),
						annotation.type(), fieldAnnotation.getField());

				columns[columnIndex] = column;
			}
		}

		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];

			switch (column.getType()) {
			case Integer:
				column.getField().set(entity, cursor.getInt(i));
				break;

			case String:
				column.getField().set(entity, cursor.getString(i));
				break;

			case Boolean:
				column.getField().set(entity, cursor.getInt(i) > 0);
				break;

			case Float:
				column.getField().set(entity, cursor.getFloat(i));
				break;

			case Double:
				column.getField().set(entity, cursor.getDouble(i));
				break;

			case Short:
				column.getField().set(entity, cursor.getShort(i));
				break;

			case Blob:
				column.getField().set(entity, cursor.getBlob(i));
				break;

			default:
				break;
			}
		}

		return entity;
	}

	protected int getRecordCount(Cursor cursor) {
		int count = cursor.getCount();

		cursor.close();

		return count;
	}

	protected ArrayList<T> readItems(Cursor cursor) throws Exception {

		Column[] columns = new Column[cursor.getColumnCount()];

		ArrayList<FieldAnnotation<DatabaseField>> annotations = AnnotationUtils
				.findFields(contract.newInstance(), DatabaseField.class);

		for (FieldAnnotation<DatabaseField> fieldAnnotation : annotations) {
			DatabaseField annotation = fieldAnnotation.getAnnotation();

			int columnIndex = cursor.getColumnIndex(annotation.name());

			if (columnIndex != -1) {
				Column column = new Column(annotation.name(),
						annotation.type(), fieldAnnotation.getField());

				columns[columnIndex] = column;
			}
		}

		ArrayList<T> items = new ArrayList<T>();

		while (cursor.moveToNext()) {

			T item = contract.newInstance();

			for (int i = 0; i < columns.length; i++) {
				Column column = columns[i];

				switch (column.getType()) {
				case Integer:
					column.getField().set(item, cursor.getInt(i));
					break;

				case String:
					column.getField().set(item, cursor.getString(i));
					break;

				case Boolean:
					column.getField().set(item, cursor.getInt(i) > 0);
					break;

				case Float:
					column.getField().set(item, cursor.getFloat(i));
					break;

				case Double:
					column.getField().set(item, cursor.getDouble(i));
					break;

				case Short:
					column.getField().set(item, cursor.getShort(i));
					break;

				case Blob:
					column.getField().set(item, cursor.getBlob(i));
					break;

				default:
					break;
				}
			}

			items.add(item);
		}

		cursor.close();

		return items;
	}

	// endregion

	// endregion

	// region Get / Set

	protected abstract SQLiteOpenHelper getDbCreator();

	// endregion

}
