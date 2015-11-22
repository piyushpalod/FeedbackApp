package com.feedbackapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

/**
 * Created by pshar10 on 11/22/2015.
 */
public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.video_view);
        Intent intent = getIntent();
//        String message = intent.getStringExtra("urivi");

        VideoView videoView = (VideoView)this.findViewById(R.id.videoView);
        //add controls to a MediaPlayer like play, pause.
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        //Set the path of Video or URI
        videoView.setVideoURI(Uri.parse("/storage/emulated/0/Pictures/MyCameraVideo/VID_20151122_044943.mp4"));

        //Set the focus
        videoView.requestFocus();

    }
}
