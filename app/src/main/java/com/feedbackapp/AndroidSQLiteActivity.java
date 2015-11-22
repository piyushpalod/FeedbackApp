package com.feedbackapp;

/**
 * Created by priyanka on 11/22/2015.
 */

        import java.util.List;
        import android.app.Activity;
        import android.os.Bundle;
        import android.util.Log;


public class AndroidSQLiteActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting feedback
        Log.d("Insert: ", "Inserting ..");
        db.addFeedBack(new FeedBack("1", "yes", 1));
        db.addFeedBack(new FeedBack("-1", "9199999999", 2));
        db.addFeedBack(new FeedBack("1", "9522222222", 3));
        db.addFeedBack(new FeedBack("-1", "9533333333", 1));

        // Reading all cfeeback
        Log.d("Reading: ", "Reading all contacts..");
        List<FeedBack> feedback = db.getAllFeedBacks();

        for (FeedBack fb : feedback) {
            String log = "Id: "+fb.get_id()+" ,f Name: " + fb.get_fname() + " ,l name: " + fb.get_lname()  + " ,vote: " + fb.get_vote()  + " ,review path: " + fb.get_review() + " ,review type: " + fb.get_review_type();
            // Writing feedback to log
            Log.d("Name: ", log);

        }
    }
}