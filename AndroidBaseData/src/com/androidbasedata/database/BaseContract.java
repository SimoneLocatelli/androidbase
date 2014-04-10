package com.androidbasedata.database;

import java.util.ArrayList;

import com.androidbase.annotations.AnnotationUtils;
import com.androidbase.annotations.FieldAnnotation;
import com.androidbase.dotnet.workers.StringUtils;

public abstract class BaseContract {

	// region Properties

	private String tableName;

	private String[] allColumns;

	private final static String createScriptTemplate = "CREATE TABLE IF NOT EXISTS @@TABLENAME@@ (@@COLUMNS@@);";
	private final static String foreignKeyScriptTemplate = " FOREIGN KEY(@@COLUMNNAME@@) REFERENCES @@FOREIGNTABLENAME@@(@@FOREIGNCOLUMNNAME@@)";

	// endregion

	// region Base Columns

	@DatabaseField(name = "ID", type = ColumnTypes.Integer)
	private int id;

	// endregion

	// region Constructors

	public BaseContract(String tableName) {

		this.tableName = tableName;

		retrieveAllColumns();
	}

	// endregion

	// region Methods

	private void retrieveAllColumns() {

		ArrayList<FieldAnnotation<DatabaseField>> fields = AnnotationUtils
				.findFields(this, DatabaseField.class);

		setAllColumns(new String[fields.size()]);

		for (int i = 0; i < fields.size(); i++) {
			getAllColumns()[i] = fields.get(i).getAnnotation().name();
		}
	}

	private String buildCreateScript() {
		ArrayList<FieldAnnotation<DatabaseField>> fieldAnnotations = AnnotationUtils
				.findFields(this, DatabaseField.class);

		String script = new String(BaseContract.createScriptTemplate);

		StringBuilder stringBuilder = new StringBuilder();

		for (FieldAnnotation<DatabaseField> fieldAnnotation : fieldAnnotations) {

			DatabaseField databaseField = fieldAnnotation.getAnnotation();

			stringBuilder.append(databaseField.name());

			switch (databaseField.type()) {
			case Blob:
				stringBuilder.append(" INTEGER");
				break;
			case Boolean:
				stringBuilder.append(" INTEGER");
				break;
			case Double:
				stringBuilder.append(" REAL");
				break;
			case Float:
				stringBuilder.append(" REAL");
				break;
			case Integer:
				stringBuilder.append(" INTEGER");
				break;
			case Short:
				stringBuilder.append(" INTEGER");
				break;
			case String:
				stringBuilder.append(" TEXT");
				break;
			}

			if (databaseField.primaryKey()) {
				stringBuilder.append(" PRIMARY KEY");
			} else {

				if (!databaseField.nullable()) {
					stringBuilder.append(" NOT");
				}

				stringBuilder.append(" NULL");
			}

			if (databaseField.autoincrement()) {
				stringBuilder.append(" AUTOINCREMENT");
			}

			if (!StringUtils.isNullOrWhiteSpace(databaseField.deafultValue())) {
				stringBuilder.append(" DEFAULT '"
						+ databaseField.deafultValue() + "'");
			}

			if (!StringUtils.isNullOrWhiteSpace(databaseField.check())) {
				stringBuilder.append(" CHECK(" + databaseField.check() + ")");
			}

			stringBuilder.append(",");
		}

		String columns = stringBuilder.toString();

		for (FieldAnnotation<DatabaseField> fieldAnnotation : fieldAnnotations) {

			FieldAnnotation<ForeignKey> foreignKeyAnnotation = AnnotationUtils
					.getAnnotation(fieldAnnotation.getField(), ForeignKey.class);

			if (foreignKeyAnnotation != null) {

				String foreignKeyScript = BaseContract.foreignKeyScriptTemplate
						.replace("@@COLUMNNAME@@",
								fieldAnnotation.getAnnotation().name())
						.replace(
								"@@FOREIGNTABLENAME@@",
								foreignKeyAnnotation.getAnnotation()
										.foreignTable())
						.replace(
								"@@FOREIGNCOLUMNNAME@@",
								foreignKeyAnnotation.getAnnotation()
										.foreignColumn());

				stringBuilder.append(foreignKeyScript);

			}
		}

		if (columns.endsWith(",")) {
			columns = columns.substring(0, columns.length() - 1);
		}

		script = script.replace("@@TABLENAME@@", getTableName());
		script = script.replace("@@COLUMNS@@", columns);

		return script;
	}

	// endregion

	// region Get / Set

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getAllColumns() {
		return allColumns;
	}

	public void setAllColumns(String[] allColumns) {
		this.allColumns = allColumns;
	}

	public String getCreateScript() {
		return buildCreateScript();
	}

	// endregion

	// region Base Columns Get / Set

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// endregion

}
