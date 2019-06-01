package com.example.claid;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class preview_screen extends AppCompatActivity {
    ImageView imageView_preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_screen);
        imageView_preview=findViewById(R.id.imageView4);
       // imageView_preview.setImageURI(Uri.parse(Constant.imagess[]));
    }
}
