package com.example.slambookapp.database;

import android.provider.BaseColumns;

public class DB_Contract {
    public static class User implements BaseColumns {
        //table
        public static String USER_TABLE = "user_table";
        //columns
        public static String ID = "user_id";
        public static String COMPLETE_NAME = "user_full_name";
        public static String USERNAME = "user_name";
        public static String PASSWORD = "user_password";
    }
    public static class Question implements BaseColumns {
        public static String QUESTION_TABLE = "question_table";
        public static String ID = "question_id";
        public static String USER_ID = "user_id";
    }
    public static class Answer implements BaseColumns {
        public static String ANSWER_TABLE = "answer_table";
        public static String ID = "answer_id";
        public static String USER_ID = "user_id";
        public static String QUESTION_ID = "question_id";
    }
    /*public static class Post implements BaseColumns {
        //table
        public static String POST_TABLE = "post_table";
        //columns
        public static String ID = "post_id";
        public static String TEXT = "post_text";
        public static String DATE = "post_date";
        public static String USER_ID = "user_id";
    }*/
}
