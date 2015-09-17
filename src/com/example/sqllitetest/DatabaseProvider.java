package com.example.sqllitetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Switch;

public class DatabaseProvider extends ContentProvider{

	public static final int ESSAY_DIR = 0;
	public static final int ESSAY_ITEM = 1;
	public static final int CATEGORY_DIR = 2;
	public static final int CATEGORY_ITEM = 3;
	public static final String AUTHORITY = "com.example.sqllitetest.provider";
	
	public static UriMatcher uriMatcher;
	
	private MydatabaseHelper dbHelper;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "essay", ESSAY_DIR);
		uriMatcher.addURI(AUTHORITY, "essay/#", ESSAY_ITEM);
		uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
		uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
	}
	
	@Override
	public boolean onCreate() {
		dbHelper = new MydatabaseHelper(getContext(), "essay.db", null, 2);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		//²éÑ¯Êý¾Ý
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = null;
		switch(uriMatcher.match(uri)){
		case ESSAY_DIR:
			cursor = db.query("essay", projection, selection, selectionArgs,
					null, null, sortOrder);
			break;
		case ESSAY_ITEM:
			String essayId = uri.getPathSegments().get(1);
			cursor = db.query("essay", projection, "id = ?", new String[] {essayId},
					null, null, sortOrder);
			break;
		case CATEGORY_DIR:
			cursor =db.query("Category", projection, selection, selectionArgs, 
					null, null, sortOrder);
			break;
		case CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			cursor = db.query("Category", projection, "id = ?", new String[] {categoryId},
					null, null, sortOrder);
			break;
		default:
			break;
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
