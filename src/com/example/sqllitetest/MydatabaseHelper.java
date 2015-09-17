package com.example.sqllitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MydatabaseHelper extends SQLiteOpenHelper{

	public static final String CREATE_ESSAY = "create table essay (" 
			+ "id integer primary key autoincrement, "
			+ "title text, "
			+ "time real, "
			+ "author text, "
			+ "content text)";
	
	public static final String CREATE_CATEGORY = "create table category("
			+ "id integer primary key autoincrement, "
			+ "category_name text, "
			+ "category_code integer)";
	
	private Context mContext;
	
	public MydatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ESSAY);
		db.execSQL(CREATE_CATEGORY);
		Toast.makeText(mContext, "Creat successful", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists essay");
		db.execSQL("drop table if exists category");
		onCreate(db);
	}

}
