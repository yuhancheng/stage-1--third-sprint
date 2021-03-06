package com.example.newapps;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbAdapter {
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_ROWID = "_id";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private static final String DATABASE_CREATE1 = "create table user (_id integer primary key autoincrement, "
			+ "username text not null, password text not null);";
	private static final String DATABASE_NAME = "database";
	private static final String DATABASE_TABLE1 = "user";

	private static final int DATABASE_VERSION = 1;
	private final Context mCtx;
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE1);
		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS user");
			onCreate(db);
		}	
	}
	public UserDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}
	//写入权限
	public UserDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void closeclose() {
		mDbHelper.close();
	}
	//创建用户
	public long createUser(String username, String password) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USERNAME, username);
		initialValues.put(KEY_PASSWORD, password);		
		return mDb.insert(DATABASE_TABLE1, null, initialValues);
	}		
	public Cursor getAllNotes() {
		return mDb.query(DATABASE_TABLE1, new String[] { KEY_ROWID, KEY_USERNAME,
				KEY_PASSWORD }, null, null, null, null, null);
	}
    //查询方法
	public Cursor getDiary(String username) throws SQLException {

		Cursor mCursor =mDb.query(true, DATABASE_TABLE1, new String[] { KEY_ROWID, KEY_USERNAME,
				KEY_PASSWORD }, KEY_USERNAME + "='" + username+"'", null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	//删除用户方法
	public void deleUser(String username){
		mDb.delete(DATABASE_TABLE1, KEY_USERNAME + "="+"?", new String[] {username});
	}
}
