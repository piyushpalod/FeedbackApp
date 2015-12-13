package com.feedbackapp;

/**
 * Created by piyush on 11/21/2015.
 */
        import android.app.Activity;
        import android.media.MediaPlayer;
        import android.media.MediaRecorder;

        import android.os.Bundle;
        import android.os.Environment;

        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;

        import android.widget.ImageView;
        import android.widget.Toast;
        import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.util.List;


public class Audio extends Activity {
    Button play,stop,record;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private DatabaseHandler db;
    private static final int AUDIO_REVIEW_TYPE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        play=(Button)findViewById(R.id.button3);
        stop=(Button)findViewById(R.id.button2);
        record=(Button)findViewById(R.id.button);

        stop.setEnabled(false);
        play.setEnabled(false);

        java.util.Date date= new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording" + timeStamp + ".3gp";

        db = new DatabaseHandler(this);

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
        myAudioRecorder.setMaxDuration(30000);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();

                }

                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder  = null;

                stop.setEnabled(false);
                play.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio recorded successfully and data store in " + outputFile ,Toast.LENGTH_LONG).show();

                db.addFeedBack(new FeedBack("hiaudio", outputFile, AUDIO_REVIEW_TYPE));

                Log.d("Reading: ", "Reading all contacts..");
                List<FeedBack> feedback = db.getAllFeedBacks();

                for (FeedBack fb : feedback) {
                    String log = "Id: " + fb.get_id() + " ,f Name: " + fb.get_fname() + " ,l name: " + fb.get_lname() + " ,vote: " + fb.get_vote() + " ,review path: " + fb.get_review() + " ,review type: " + fb.get_review_type();
                    // Writing feedback to log
                    Log.d("Name: ", log);
                }

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException,SecurityException,IllegalStateException {
                MediaPlayer m = new MediaPlayer();

                try {
                    m.setDataSource(outputFile);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    m.prepare();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                m.start();
                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
