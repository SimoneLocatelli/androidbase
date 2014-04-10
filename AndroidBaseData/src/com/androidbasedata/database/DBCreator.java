package com.androidbasedata.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidbase.app.ABApplication;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.log.Log;

public abstract class DBCreator extends SQLiteOpenHelper {

	// region Properties

	private final static String tag = "DBCreator";

	// endregion

	// region Constructors

	public DBCreator(String name, int version) {
		super(ABApplication.getContext(), name, null, version);
	}

	public DBCreator(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public DBCreator(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	// endregion

	// region Overrides
	@Override
	public void onCreate(SQLiteDatabase db) {
		Class<?>[] contracts = getContracts();

		for (Class<?> contract : contracts) {

			BaseContract contractInstance;
			try {

				contractInstance = CS.as(contract.newInstance());

				if (contractInstance == null) {
					throw new RuntimeException(
							"getContracts() elements must inherit from BaseContract class");
				}

				String script = contractInstance.getCreateScript();

				Log.i(DBCreator.tag, script);

				db.execSQL(script);

			} catch (Exception ex) {
				Log.e(DBCreator.tag, ex);

				throw new RuntimeException(ex);

			}

		}
	}

	// endregion

	// region Get / Set

	public abstract Class<?>[] getContracts();

	// endregion

}
