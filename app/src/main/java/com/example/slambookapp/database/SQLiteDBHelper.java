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
        final String CREATE_USER_TABLE = "CREATE TABLE '"+db_contract.User.USER_TABLE+"' (" +
                "'"+db_contract.User.ID+"' INTEGER PRIMARY KEY," +
                "'"+db_contract.User.COMPLETE_NAME+"' TEXT NOT NULL," +
                "'"+db_contract.User.USERNAME+"' TEXT NOT NULL," +
                "'"+db_contract.User.PASSWORD+"' TEXT NOT NULL," +
                "UNIQUE ('"+db_contract.User.ID+"') ON CONFLICT ABORT)";
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

        values.put(db_contract.User.COMPLETE_NAME, name);
        values.put(db_contract.User.USERNAME, username);
        values.put(db_contract.User.PASSWORD, password);

        long result = sqliteDatabase.insert(db_contract.User.USER_TABLE,null, values);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Cursor selectAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(db_contract.User.USER_TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
        return result;
    }

    public Cursor selectUserByID(String ID, String complete_name){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnsNeeded = {
                db_contract.User.ID,
                db_contract.User.COMPLETE_NAME
        }; // SELECT * FROM 'table_user' WHERE 'id' = ? OR 'complete_name' LIKE ?
        String selection = db_contract.User.ID+" = ? OR "+
                db_contract.User.COMPLETE_NAME+" LIKE ?";
        String[] selectionArgs = {ID,"%"+complete_name+"%"};

        //Sorting
        String orderBy = db_contract.User.ID+" DESC ";

        Cursor result = db.query(db_contract.User.USER_TABLE,
                columnsNeeded,//columns
                selection,
                selectionArgs,
                null,
                null,
                orderBy);
        return result;
    }
}
