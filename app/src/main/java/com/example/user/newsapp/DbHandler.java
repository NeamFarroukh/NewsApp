package com.example.user.newsapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 7/29/2019.
 */

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 15;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_News = "News";
    private static final String KEY_ID = "id";
    private static final String KEY_Title= "title";
    private static final String KEY_desc = "desc";
    private static final String KEY_image = "image";
    private static final String KEY_details = "details";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE = "CREATE TABLE " + TABLE_News + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Title + " TEXT,"
                + KEY_desc + " TEXT," + KEY_details + " TEXT," + KEY_image + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
//        insertNews("Sophie Turner","Sophie Turner And Joe Jonas Just Paid Tribute To Their Late Dog In The Sweetest Way",
//                "news1.jpg"," Sophie Turner and Joe Jonas found a beautiful way to pay tribute to their late dog, Waldo Picasso, by getting matching tattoos in his honour. The couple, who are currently mourning the loss of their Alaskan Klee Kai – after he was reportedly knocked down and killed last week in a “freak accident” in New York City – took to Instagram to share the sweet gesture.\n" +
//                        "\n" +
//                        "“I miss you, Waldo,” Turner wrote on her Instagram Story. “Rest in peace, my little baby.”");
//        insertNews("HRH The Duchess","HRH The Duchess of Sussex Introduces The September Issue In Her Own Words","news2.jpg","It was in early January, on a cold and blustery London day, that I sat down for a cup of tea with British Vogue’s editor-in-chief, Edward Enninful. Though we have several mutual friends, our first encounter had been years in the making, the impetus for which was my asking him to support an organisation I strongly believe in called Smart Works.It was in early January, on a cold and blustery London day, that I sat down for a cup of tea with British Vogue’s editor-in-chief, Edward Enninful. Though we have several mutual friends, our first encounter had been years in the making, the impetus for which was my asking him to support an organisation I strongly believe in called Smart Works.");
//        insertNews("Edward Enninful","Edward Enninful On Why HRH The Duchess Of Sussex Is The Ultimate Force For Change","news3.jpg","It began with an email received in the chilly depths of January. I was spending a few days in the Austrian mountains when a message arrived in my inbox out of the blue. The sender went by the simplest of monikers – “M” – and for a second I was confused, wondering who this mysterious, one-lettered correspondent could possibly be. I couldn’t have known then how the answer to this question would come to define an extraordinary few months in the life of this magazine, or that it would lead to HRH The Duchess of Sussex becoming the first person ever to guest edit a September issue of British Vogue.");
//        insertNews("Susan Devaney","Anne Hathaway Opens Up About That Instagram Post And Her Personal Fertility Journey","news4.jpg","Anne Hathaway wants other women to know that she, too, has faced issues trying to conceive. After taking to Instagram last week to announce that she’s expecting her second child, with her husband Adam Shulman, she’s since spoken candidly about her fertility struggles with both pregnancies.\n" +
//                "“It’s not for a movie... #2,” the actor joked in the post. “All kidding aside, for everyone going through infertility and conception hell, please know it was not a straight line to either of my pregnancies. Sending you extra love.”");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_News);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new news
    public void insertNews(String title, String desc, String image,String details){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_Title, title);
        cValues.put(KEY_desc, desc);
        cValues.put(KEY_image, image);
        cValues.put(KEY_details, details);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_News,null, cValues);
        db.close();
    }
    // Get news
    public ArrayList<HashMap<String, String>> GetNews(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> news = new ArrayList<>();
        String query = "SELECT title, desc, image, details FROM "+ TABLE_News ;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> n = new HashMap<>();
            n.put("title",cursor.getString(cursor.getColumnIndex(KEY_Title)));
            n.put("desc",cursor.getString(cursor.getColumnIndex(KEY_desc)));
            n.put("image",cursor.getString(cursor.getColumnIndex(KEY_image)));
            n.put("details",cursor.getString(cursor.getColumnIndex(KEY_details)));
            news.add(n);
        }
        return  news;
    }

    // Get news
    public ArrayList<String> GetNewsbyID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> news = new ArrayList<>();
        String query = "SELECT title, desc, image, details FROM "+ TABLE_News + "where id ="+id;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){

            news.add(cursor.getString(cursor.getColumnIndex(KEY_Title)));
            news.add(cursor.getString(cursor.getColumnIndex(KEY_desc)));
            news.add(cursor.getString(cursor.getColumnIndex(KEY_image)));
            news.add(cursor.getString(cursor.getColumnIndex(KEY_details)));

        }
        return  news;
    }


    // Delete news Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_News, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update news Details
    public int UpdateNewsDetails(String title,String desc,String details, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_desc, desc);
        cVals.put(KEY_Title, title);
        cVals.put(KEY_details, details);
        int count = db.update(TABLE_News, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}