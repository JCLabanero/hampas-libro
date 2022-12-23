package com.example.slambookapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "database.db";
    private static int VERSION = 9;
    Context context;

    public SQLiteDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase dbs = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_USER_TABLE = "CREATE TABLE '"+ DB_Contract.User.USER_TABLE+"' (" +
                "'"+ DB_Contract.User.ID+"' INTEGER PRIMARY KEY," +
                "'"+ DB_Contract.User.COMPLETE_NAME+"' TEXT NOT NULL," +
                "'"+ DB_Contract.User.USERNAME+"' TEXT NOT NULL," +
                "'"+ DB_Contract.User.PASSWORD+"' TEXT NOT NULL," +
                "UNIQUE ('"+ DB_Contract.User.ID+"') ON CONFLICT ABORT)";
        final String CREATE_QUESTION_TABLE = "CREATE TABLE '"+DB_Contract.Question.QUESTION_TABLE+"' (" +
                "'"+DB_Contract.Question.ID+"' INTEGER PRIMARY KEY," +
                "'"+DB_Contract.Question.QUESTION +"' TEXT NOT NULL," +
                "'"+DB_Contract.Question.USER_ID+"' INTEGER NOT NULL," +
                " FOREIGN KEY ('"+DB_Contract.Question.USER_ID+"') REFERENCES " +
                "'"+DB_Contract.User.USER_TABLE+"' ('"+DB_Contract.User.ID+"') ON DELETE CASCADE ON UPDATE CASCADE," + // CASCADE not a good practice
                "UNIQUE ('"+DB_Contract.Question.ID+"') ON CONFLICT ABORT)";
        final String CREATE_ANSWER_TABLE = "CREATE TABLE '"+DB_Contract.Answer.ANSWER_TABLE+"' (" +
                "'"+DB_Contract.Answer.ID+"' INTEGER PRIMARY KEY," +
                "'"+DB_Contract.Answer.ANSWER +"' TEXT NOT NULL," +
                "'"+DB_Contract.Answer.QUESTION_ID+"' INTEGER NOT NULL," +
                " FOREIGN KEY ('"+DB_Contract.Answer.QUESTION_ID+"') REFERENCES " +
                "'"+DB_Contract.Question.QUESTION_TABLE+"' ('"+DB_Contract.Question.ID+"') ON DELETE CASCADE ON UPDATE CASCADE," + // CASCADE not a good practice
                " FOREIGN KEY ('"+DB_Contract.Answer.USER_ID+"') REFERENCES " +
                "'"+DB_Contract.User.USER_TABLE+"' ('"+DB_Contract.User.ID+"') ON DELETE CASCADE ON UPDATE CASCADE,"+
                "UNIQUE ('"+DB_Contract.Answer.ID+"') ON CONFLICT ABORT)";
        try {
            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
            sqLiteDatabase.execSQL(CREATE_QUESTION_TABLE);
            sqLiteDatabase.execSQL(CREATE_ANSWER_TABLE);
            Toast.makeText(context, "Database created", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }/*final String CREATE_USER_TABLE = "CREATE TABLE 'table_name' (" + "'user_id' INTEGER PRIMARY KEY," + "'user_full_name' TEXT NOT NULL," + "'user_name' TEXT NOT NULL," + "'user_password' TEXT NOT NULL," + "UNIQUE ('user_id') ON CONFLICT ABORT)";*/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldDatabase, int newDatabase) {
        final String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+DB_Contract.User.USER_TABLE;
        final String DROP_QUESTION_TABLE = "DROP TABLE IF EXISTS "+DB_Contract.Question.QUESTION_TABLE;
        final String DROP_ANSWER_TABLE = "DROP TABLE IF EXISTS "+DB_Contract.Answer.ANSWER_TABLE;
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        sqLiteDatabase.execSQL(DROP_QUESTION_TABLE);
        sqLiteDatabase.execSQL(DROP_ANSWER_TABLE);
        onCreate(sqLiteDatabase);
    }//For updating table, drop the first table then auto create the updated one VERSION=2

    public boolean insertAnswer(String answer, Integer id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DB_Contract.Answer.ANSWER, answer);
        values.put(DB_Contract.Answer.QUESTION_ID,id);

        long result = sqLiteDatabase.insert(DB_Contract.Answer.ANSWER_TABLE,null,values);
        return result!=-1;
    }

    public boolean insertQuestion(String question, Integer id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DB_Contract.Question.QUESTION, question);
        values.put(DB_Contract.Question.USER_ID, id);

        long result = sqLiteDatabase.insert(DB_Contract.Question.QUESTION_TABLE,null,values);
        return result != -1;
    }

    public boolean insertIntoUserTable(String name, String username, String password){
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DB_Contract.User.COMPLETE_NAME, name);
        values.put(DB_Contract.User.USERNAME, username);
        values.put(DB_Contract.User.PASSWORD, password);

        long result = sqliteDatabase.insert(DB_Contract.User.USER_TABLE,null, values);
        return result != -1;
    }
    public Cursor selectAllAnswer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(DB_Contract.Answer.ANSWER_TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
        return result;
    }

    public Cursor selectAllQuestion() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(DB_Contract.Question.QUESTION_TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
        return result;
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
    public boolean deleteAnswerByRowNumber(String rowNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,DB_Contract.Answer.ANSWER_TABLE);
        Toast.makeText(context, "count "+count, Toast.LENGTH_SHORT).show();
//        String selection = DB_Contract.Answer.ANSWER_TABLE+"=?";
//        String[] selectionArgs = {};
        return false;
    }
    public boolean deleteAnswer(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = DB_Contract.Answer.ANSWER_TABLE+"=?";
        String[] selectionArgs = {ID};
        int affected = db.delete(DB_Contract.Answer.ANSWER_TABLE,selection,selectionArgs);
        return affected>0;
    }
    public boolean deleteQuestion(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = DB_Contract.Question.ID+"=?";
        String[] selectionArgs = {ID};
        int affected = db.delete(DB_Contract.Question.QUESTION_TABLE,selection,selectionArgs);
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
