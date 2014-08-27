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
	
	private static final String KEY_DATE = "transactiondate";
	private static final String KEY_AMOUNT = "amount";
	private static final String KEY_TYPE = "type";
	private static final String KEY_MONTHLY = "monthly";
	private static final String KEY_ANNUALY = "annualy";
	private static final String KEY_ID = "id";
	
	
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_BUDGET_TABLE ="CREATE TABLE "+TABLE_BUDGET +"("+KEY_ID +" NUMBER DEFAULT AUTO_INCREAMENT ,"+KEY_DATE +" DATETIME DEFAULT CURRENT_TIMESTAMP ,"+KEY_AMOUNT+" NUMBER,"+KEY_TYPE+" TEXT"
				+")";
		String CREATE_TARGET_TABLE ="CREATE TABLE "+TABLE_TARGET +"("+ KEY_ID+" NUMBER DEFAULT AUTO_INCREMENT ,"+KEY_MONTHLY +" NUMBER ,"+KEY_ANNUALY+" NUMBER"+")";
		
		
		db.execSQL(CREATE_TARGET_TABLE);
		db.execSQL(CREATE_BUDGET_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP IF EXISTS "+TABLE_TARGET);
		db.execSQL("DROP IF EXISTS "+TABLE_BUDGET);
		
			
		onCreate(db);
		
		
	}
	
	public void addTransaction(String type , java.util.Date datet , int Amount){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
	    values.put(KEY_TYPE, type);
		values.put(KEY_AMOUNT, Amount);
		db.insert(TABLE_BUDGET, null, values);
		db.close();
				
	}
	public void addTarget(int monthly,int annualy){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from target");
		ContentValues values = new ContentValues();
	    values.put(KEY_MONTHLY, monthly);
		values.put(KEY_ANNUALY, annualy);
		db.insert(TABLE_TARGET, null, values);
		db.close();
				
	}
	public int monthlytarget(){
		
        SQLiteDatabase db = this.getReadableDatabase();
		
		String targetQuery = "SELECT target."+KEY_MONTHLY+" from "+TABLE_TARGET +" ORDER BY id DESC";
		
		Cursor cursor = db.rawQuery(targetQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
			
	}
    public int annualtarget(){
		
        SQLiteDatabase db = this.getReadableDatabase();
		
		String targetQuery = "SELECT annualy from "+TABLE_TARGET+" ORDER BY id DESC";
		
		Cursor cursor = db.rawQuery(targetQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
			
	}
	
	public int getincomepermonth(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String incomeQuery = "SELECT sum(amount) as income from "+TABLE_BUDGET+" where type = 'income' and strftime('%m', transactiondate) = strftime('%m', CURRENT_TIMESTAMP)";
		
		Cursor cursor = db.rawQuery(incomeQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
			
	}
	public int getannualincome(){
	SQLiteDatabase db = this.getReadableDatabase();
	
	String incomeQuery = "SELECT sum(amount) as income from "+TABLE_BUDGET+" where type = 'income' and strftime('%Y', transactiondate) = strftime('%Y', CURRENT_TIMESTAMP)";
	
	Cursor cursor = db.rawQuery(incomeQuery, null);
	
	if(cursor.moveToFirst()){
	return cursor.getInt(0);
	}
	return (Integer) 0;
	}
	public int getannualexpense(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String incomeQuery = "SELECT sum(amount) as income from "+TABLE_BUDGET+" where type = 'expense' and strftime('%Y', transactiondate) = strftime('%Y', CURRENT_TIMESTAMP)";
		
		Cursor cursor = db.rawQuery(incomeQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
		}
		
	
	public int getexpensepermonth(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String incomeQuery = "SELECT sum(amount) as expense from "+TABLE_BUDGET+" where type = 'expense' and strftime('%m', transactiondate) = strftime('%m', CURRENT_TIMESTAMP)";
		
		Cursor cursor = db.rawQuery(incomeQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
			
	}
	public int getavgexpenses(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String incomeQuery = "SELECT avg(amount) as avgexpense from "+TABLE_BUDGET+" where type = 'expense' and strftime('%m', transactiondate) = strftime('%m', CURRENT_TIMESTAMP)";
		
		Cursor cursor = db.rawQuery(incomeQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
			
	}
	public int getavgincome(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String incomeQuery = "SELECT avg(amount) as avgincome from "+TABLE_BUDGET+" where type = 'income' and strftime('%m', transactiondate) = strftime('%m', CURRENT_TIMESTAMP)";
		
		Cursor cursor = db.rawQuery(incomeQuery, null);
		
		if(cursor.moveToFirst()){
		return cursor.getInt(0);
		}
		return (Integer) 0;
			
	}

}
