package com.example.sqllitetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private MydatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MydatabaseHelper(this, "essay.db", null, 2);
		Button creatDatabase = (Button) findViewById(R.id.creat);
		Button addData = (Button) findViewById(R.id.add);
		Button update = (Button) findViewById(R.id.update);
		Button deletedata = (Button) findViewById(R.id.delete);
		Button querydata = (Button) findViewById(R.id.query);
		
		addData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				//��ʼ��װ��һ������
				values.put("title", "������ʵϰ����Ƹ");
				values.put("time", 2015.08);
				values.put("author", "the teacher of zhaowenjing");
				values.put("content", "�㻹��Ϊʵϰ�Ҳ��������������𣿿����ϵ��������");
				//�����һ������
				db.insert("essay", null, values);
				
				values.clear();
				
				//��ʼ��װ�ڶ�������
				values.put("title", "���齻�����и�");
				values.put("time", 2015.09);
				values.put("author", "����Բ");
				values.put("content", "ͨ����ζ��齻������ѧ���˺ܶ࣬����Ķ�һ��������Һܴ�����������һ���ú�Ŭ��");
				//����ڶ�������
				db.insert("essay", null, values);
			}
		});
		
		creatDatabase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbHelper.getWritableDatabase();
			}
		});
		
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("time", 2016.09);
				db.update("essay", values, "title = ?", new String[]{"������ʵϰ����Ƹ"});			
			}
		});
		
		deletedata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("essay", "time > ?", new String[] {"2016"});			
			}
		});
		
		querydata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				//��ѯessay�����е�����
				Cursor cursor = db.query("essay", null, null, null, 
						null, null, null);
				if(cursor.moveToFirst()){					
					do{
						//����Cursor����ȡ�����ݲ���ӡ
						String title = cursor.getString(cursor.getColumnIndex("title"));
						String author = cursor.getString(cursor.getColumnIndex("author"));
						String content = cursor.getString(cursor.getColumnIndex("content"));
						Float time = cursor.getFloat(cursor.getColumnIndex("time"));
						
						Log.d("MainActivity", "essay title is" + title);
						Log.d("MainActivity", "essay author is" + author);
						Log.d("MainActivity", "essay time is" + time);
						Log.d("MainActivity", "essay content is" + content);
					}while(cursor.moveToNext());
				}				
				cursor.close();
			}	
		});
	}

}
