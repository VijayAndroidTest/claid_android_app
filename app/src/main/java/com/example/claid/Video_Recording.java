package com.example.claid;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

public class Video_Recording extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CAPTURE = 1;

    Uri videouri =null;
    Camera camera;
    private VideoView mVideoView;
    private AccessibilityService applicationContext;
    private MediaRecorder mediaRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__recording);
       //mVideoView=findViewById(R.id.videoView2);

        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        camera.setDisplayOrientation(90);
        camera.unlock();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setCamera(camera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        CamcorderProfile camcorderProfile_HQ = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        mediaRecorder.setProfile(camcorderProfile_HQ);
        dispatchTakeVideoIntent();

    }



    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_VIDEO_CAPTURE && resultCode==RESULT_OK)
        {
           videouri=data.getData();
        }

        mVideoView.setVideoURI(videouri);
        mVideoView.start();
    }
}
