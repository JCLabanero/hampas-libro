package com.example.slambookapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "database.db";
    private static int VERSION = 1;
    Context context;

    public SQLiteDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase dbs = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*final String CREATE_USER_TABLE = "CREATE TABLE 'table_name' (" +
                "'user_id' INTEGER PRIMARY KEY," +
                "'user_full_name' TEXT NOT NULL," +
                "'user_name' TEXT NOT NULL," +
                "'user_password' TEXT NOT NULL," +
                "UNIQUE ('user_id') ON CONFLICT ABORT)";*/
        final String CREATE_USER_TABLE = "CREATE TABLE '"+ DB_Contract.User.USER_TABLE+"' (" +
                "'"+ DB_Contract.User.ID+"' INTEGER PRIMARY KEY," +
                "'"+ DB_Contract.User.COMPLETE_NAME+"' TEXT NOT NULL," +
                "'"+ DB_Contract.User.USERNAME+"' TEXT NOT NULL," +
                "'"+ DB_Contract.User.PASSWORD+"' TEXT NOT NULL," +
                "UNIQUE ('"+ DB_Contract.User.ID+"') ON CONFLICT ABORT)";
        try {
            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
            Toast.makeText(context, "Database created", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldDatabase, int newDatabase) {
        //For updating table, drop the first table then auto create the updated one VERSION=2
        /*final String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+db_contract.User.USER_TABLE;
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);*/
    }

    public boolean insertIntoTable(String name, String username, String password){
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DB_Contract.User.COMPLETE_NAME, name);
        values.put(DB_Contract.User.USERNAME, username);
        values.put(DB_Contract.User.PASSWORD, password);

        long result = sqliteDatabase.insert(DB_Contract.User.USER_TABLE,null, values);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Cursor selectAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(DB_Contract.User.USER_TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
        return result;
    }

    public Cursor selectUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnsNeeded = {
                DB_Contract.User.ID,
                DB_Contract.User.COMPLETE_NAME,
                DB_Contract.User.USERNAME,
                DB_Contract.User.PASSWORD
        };
        String selection = DB_Contract.User.USERNAME+" = ?";
        String[] selectionArgs = {username};
        Cursor result = db.query(DB_Contract.User.USER_TABLE,
                columnsNeeded,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return result;
    }

    public Cursor selectUserByIDOrName(String ID, String complete_name){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnsNeeded = {
                DB_Contract.User.ID,
                DB_Contract.User.COMPLETE_NAME,
                DB_Contract.User.USERNAME,
                DB_Contract.User.PASSWORD
        }; // SELECT * FROM 'table_user' WHERE 'id' = ? OR 'complete_name' LIKE ?
        String selection = DB_Contract.User.ID+" = ? OR "+// = ? AND
                DB_Contract.User.COMPLETE_NAME+" LIKE ?";
        String[] selectionArgs = {ID,"%"+complete_name+"%"};

        //Sorting
        String orderBy = DB_Contract.User.ID+" DESC ";

        Cursor result = db.query(DB_Contract.User.USER_TABLE,
                columnsNeeded,//columns
                selection,
                selectionArgs,
                null,
                null,
                orderBy);
        return result;
    }
    public Boolean updateUser(String ID, String name, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DB_Contract.User.COMPLETE_NAME,name);
        values.put(DB_Contract.User.USERNAME,username);
        values.put(DB_Contract.User.PASSWORD,password);

        String selection = DB_Contract.User.ID+" = ? ";
        String[] selectionArgs = {ID};

        int affected = db.update(DB_Contract.User.USER_TABLE,values,selection,selectionArgs);

        return affected > 0;
    }
    public Boolean deleteUser(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = DB_Contract.User.ID+" = ? ";
        String[] selectionArgs = {ID};
        int affected = db.delete(DB_Contract.User.USER_TABLE,selection,selectionArgs);
        return affected > 0;
    }
}
