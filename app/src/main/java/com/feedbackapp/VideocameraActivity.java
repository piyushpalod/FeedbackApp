package com.feedbackapp;

/**
 * Created by priyanka on 11/21/2015.
 */
        import java.io.File;
        import java.text.SimpleDateFormat;
        import java.util.List;

        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class VideocameraActivity extends Activity {

    private Uri fileUri;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private static final int VIDEO_REVIEW_TYPE = 1;
    public static VideocameraActivity ActivityContext =null;
    public static TextView output;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActivityContext = this;

        Button buttonRecording = (Button)findViewById(R.id.recording);
        output = (TextView)findViewById(R.id.output);

        buttonRecording.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                // create new Intentwith with Standard Intent action that can be
                // sent to have the camera application capture an video and return it.
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                // create a file to save the video
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

                // set the image file name
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                // set the video image quality to high
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);

                // start the Video Capture Intent
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);

            }});


    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){

        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){

        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraVideo");

        // Create the storage directory(MyCameraVideo) if it does not exist
        if (! mediaStorageDir.exists()){

            if (! mediaStorageDir.mkdirs()){

                output.setText("Failed to create directory MyCameraVideo.");

                Toast.makeText(ActivityContext, "Failed to create directory MyCameraVideo.",
                        Toast.LENGTH_LONG).show();

                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
                return null;
            }
        }


        // Create a media file name

        // For unique file name appending current timeStamp with file name
        java.util.Date date= new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());

        File mediaFile;

        if(type == MEDIA_TYPE_VIDEO) {

            // For unique video file name appending current timeStamp with file name
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");

        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // After camera screen this code will excuted
        DatabaseHandler db = new DatabaseHandler(this);

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                output.setText("Video File : " +data.getData());

                // Video captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                db.addFeedBack(new FeedBack("hi", data.getData().toString(), VIDEO_REVIEW_TYPE));

            } else if (resultCode == RESULT_CANCELED) {

                output.setText("User cancelled the video capture.");

                // User cancelled the video capture
                Toast.makeText(this, "User cancelled the video capture.",
                        Toast.LENGTH_LONG).show();

            } else {

                output.setText("Video capture failed.");

                // Video capture failed, advise user
                Toast.makeText(this, "Video capture failed.",
                        Toast.LENGTH_LONG).show();
            }
        }
        Log.d("Reading: ", "Reading all contacts..");
        List<FeedBack> feedback = db.getAllFeedBacks();

        for (FeedBack fb : feedback) {
            String log = "Id: "+fb.get_id()+" ,f Name: " + fb.get_fname() + " ,l name: " + fb.get_lname()  + " ,vote: " + fb.get_vote()  + " ,review path: " + fb.get_review() + " ,review type: " + fb.get_review_type();
            // Writing feedback to log
            Log.d("Name: ", log);

        }
    }

}