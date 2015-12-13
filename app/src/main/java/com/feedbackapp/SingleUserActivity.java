package com.feedbackapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by pshar10 on 12/11/2015.
 */
public class SingleUserActivity extends Activity {

    Button audio_feed, video_feed, text_feed;
    //JSON Node names
    private static final String ENTITY_ID = "entityid";
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_VOTE = "vote";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_REVIEW_TYPE = "review_type";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_user_activity);
        DatabaseHandler db = new DatabaseHandler(this);
        // getting intent data
        Intent in = getIntent();
        // Get JSON values from previous intent
        String entity_id = in.getStringExtra(ENTITY_ID);
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.entity_id);
        lblName.setText(entity_id);

        video_feed = (Button)findViewById(R.id.buttonVideo);
        audio_feed = (Button)findViewById(R.id.buttonAudio);
        text_feed = (Button)findViewById(R.id.buttonText);

        audio_feed.setEnabled(true);
        text_feed.setEnabled(true);
        video_feed.setEnabled(true);

        video_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    audio_feed.setEnabled(false);
                    text_feed.setEnabled(false);
                    video_feed.setEnabled(false);
                    Intent in = new Intent(getApplicationContext(), VideocameraActivity.class);
                    startActivity(in);
                }
                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    audio_feed.setEnabled(true);
                    text_feed.setEnabled(true);
                    video_feed.setEnabled(true);
                    e.printStackTrace();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    audio_feed.setEnabled(true);
                    text_feed.setEnabled(true);
                    video_feed.setEnabled(true);
                    e.printStackTrace();
                }
            }
        });

        audio_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    audio_feed.setEnabled(false);
                    text_feed.setEnabled(false);
                    video_feed.setEnabled(false);
                    Intent in = new Intent(getApplicationContext(), Audio.class);
                    startActivity(in);

                }
                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    audio_feed.setEnabled(true);
                    text_feed.setEnabled(true);
                    video_feed.setEnabled(true);
                    e.printStackTrace();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    audio_feed.setEnabled(true);
                    text_feed.setEnabled(true);
                    video_feed.setEnabled(true);
                    e.printStackTrace();
                }
            }
        });

        text_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    audio_feed.setEnabled(false);
                    text_feed.setEnabled(false);
                    video_feed.setEnabled(false);
                    Intent in = new Intent(getApplicationContext(), VideocameraActivity.class);
                    startActivity(in);
                }
                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    audio_feed.setEnabled(true);
                    text_feed.setEnabled(true);
                    video_feed.setEnabled(true);
                    e.printStackTrace();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    audio_feed.setEnabled(true);
                    text_feed.setEnabled(true);
                    video_feed.setEnabled(true);
                    e.printStackTrace();
                }
            }
        });


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

