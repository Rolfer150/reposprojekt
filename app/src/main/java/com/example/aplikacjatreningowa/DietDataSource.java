//package com.example.aplikacjatreningowa;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DietDataSource {
//    private SQLiteDatabase database;
//    private DatabaseHelper dbHelper;
//
//    public DietDataSource(Context context) {
//        dbHelper = new DatabaseHelper(context);
//    }
//
//    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        dbHelper.close();
//    }
//
//    public long addDiet(Diet diet) {
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.COLUMN_NAME, diet.getName());
//        values.put(DatabaseHelper.COLUMN_BODY_TYPE, diet.getBodyType());
//        values.put(DatabaseHelper.COLUMN_DESCRIPTION, diet.getDescription());
//
//        return database.insert(DatabaseHelper.TABLE_DIET, null, values);
//    }
//
//    public void deleteDiet(long dietId) {
//        database.delete(DatabaseHelper.TABLE_DIET, DatabaseHelper.COLUMN_ID + " = " + dietId, null);
//    }
//
//    public List<Diet> getAllDiets() {
//        List<Diet> diets = new ArrayList<>();
//        Cursor cursor = database.query(DatabaseHelper.TABLE_DIET, null, null, null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                Diet diet = cursorToDiet(cursor);
//                diets.add(diet);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        return diets;
//    }
//
//    private Diet cursorToDiet(Cursor cursor) {
//        Diet diet = new Diet();
//        diet.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
//        diet.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
//        diet.setBodyType(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BODY_TYPE)));
//        diet.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)));
//        return diet;
//    }
//}

