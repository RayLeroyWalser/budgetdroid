package com.equiplexdevelopers.budgetdroid;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "budgetManager";
	
	private static final String TABLE_BUDGET = "budget";
	
	private static final String TABLE_TARGET = "target";
	
	private static final String KEY_DATE = "date";
	private static final String KEY_AMOUNT = "amount";
	private static final String KEY_TYPE = "type";
	private static final String KEY_MONTHLY = "monthly";
	private static final String KEY_ANNUALY = "annualy";
	
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_BUDGET_TABLE ="CREATE TABLE "+TABLE_BUDGET +"("+KEY_DATE +"DATE ,"+KEY_AMOUNT+" NUMBER,"+KEY_TYPE+" TEXT"
				+")";
		db.execSQL(CREATE_BUDGET_TABLE);
		String CREATE_TARGET_TABLE ="CREATE TABLE "+TABLE_TARGET +"("+KEY_MONTHLY +"TEXT ,"+KEY_ANNUALY+" TEXT "
				+")";
		db.execSQL(CREATE_TARGET_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP IF EXISTS "+TABLE_BUDGET);
		db.execSQL("DROP IF EXISTS "+TABLE_TARGET);
			
		onCreate(db);
		
		
	}
	
	public void addTransaction(String type , String date , String Amount){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_DATE, date);
		values.put(KEY_TYPE, type);
		values.put(KEY_AMOUNT, Amount);
		db.insert(TABLE_BUDGET, null, values);
		db.close();
				
	}
	
	public String getincomepermonth(int month,int Year){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String incomeQuery = "SELECT sum(amount) as income from "+TABLE_BUDGET+"WHERE YEAR(date) = '"+Year+"'and type ='"+"income"+"'and MONTH(date) ='"+month+"'";
		
		Cursor cursor = db.rawQuery(incomeQuery, null);
		
		cursor.close();
		return cursor.toString();
			
	}

}
