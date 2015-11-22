package com.feedbackapp;

/**
 * Created by priyanka on 11/22/2015.
 */

        import java.util.ArrayList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "reviewsManager";

    // Contacts table name
    private static final String TABLE_DATA = "feedbacks";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_VOTE = "vote";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_REVIEW_TYPE = "review_type";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_VOTE + " TEXT,"
                + KEY_REVIEW + " TEXT," + KEY_REVIEW_TYPE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new feedback
    void addFeedBack(FeedBack feedback) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, feedback.get_fname()); //  Name
        values.put(KEY_LNAME, feedback.get_lname());
        values.put(KEY_VOTE, feedback.get_vote());
        values.put(KEY_REVIEW, feedback.get_review()); //  review file path
        values.put(KEY_REVIEW_TYPE, feedback.get_review_type());
        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    FeedBack getFeedBack(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATA, new String[] { KEY_ID,
                        KEY_FNAME, KEY_LNAME, KEY_VOTE, KEY_REVIEW }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        FeedBack feedback = new FeedBack(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
        // return contact
        return feedback;
    }

    // Getting All feedbacks
    public List<FeedBack> getAllFeedBacks() {
        List<FeedBack> feedBackList = new ArrayList<FeedBack>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FeedBack feedback = new FeedBack();
                feedback.set_id(Integer.parseInt(cursor.getString(0)));
                feedback.set_fname(cursor.getString(1));
                feedback.set_lname(cursor.getString(2));
                feedback.set_vote(cursor.getString(3));
                feedback.set_review(cursor.getString(4));
                feedback.set_review_type(Integer.parseInt(cursor.getString(5)));
                // Adding feedback to list
                feedBackList.add(feedback);
            } while (cursor.moveToNext());
        }

        // return contact list
        return feedBackList;
    }

    // Updating single feedback
    public int updateFeedBack(FeedBack feedback) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, feedback.get_fname());
        values.put(KEY_REVIEW, feedback.get_review());

        // updating row
        return db.update(TABLE_DATA, values, KEY_ID + " = ?",
                new String[] { String.valueOf(feedback.get_id()) });
    }

    // Deleting single feedback
    public void deleteFeedBack(FeedBack feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_ID + " = ?",
                new String[] { String.valueOf(feedback.get_id())});
        db.close();
    }


    // Getting feedback Count
    public int getFeedBackCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
