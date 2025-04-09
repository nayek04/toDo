package com.example.todo.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.todo.model.TODOModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = " TODO_DATABASE";
    private static final String TABLE_NAME = "TODO_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TASK";
    private static final String COL_3 = "STATUS";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TASK TEXT, STATUS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + "TABLE_NAME");
        onCreate(db);

    }

    public void insertTask(TODOModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, model.getTask());
        contentValues.put(COL_3, 0);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public void updateTask(int id, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, task);

        db.update(TABLE_NAME, contentValues, "ID+?", new String[]{String.valueOf(id)});
    }

    public void updateStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, status);
        db.update(TABLE_NAME, contentValues, " ID=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID=?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public ArrayList<TODOModel> getALLTasks() {
        Cursor cursor = null;
        List<TODOModel> modelList = new ArrayList<>();

        SQLiteDatabase db = null;
        db= this.getWritableDatabase();
        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    TODOModel todoModel = new TODOModel();
                    todoModel.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                    todoModel.setTask(cursor.getString(cursor.getColumnIndex(COL_2)));
                    todoModel.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
                    modelList.add(todoModel);
                } while (cursor.moveToNext());
            }

            db.setTransactionSuccessful(); // Mark as successful if everything goes well
        } catch (Exception e) {
            Log.e("DB_ERROR", "Failed to fetch data: " + e.getMessage());
        } finally {
            db.endTransaction(); // Always end transaction
            if (cursor != null) {
                cursor.close(); // Always close the cursor
            }
        }

        return new ArrayList<>(modelList);
    }
}