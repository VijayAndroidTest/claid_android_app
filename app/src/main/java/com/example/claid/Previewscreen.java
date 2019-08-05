package com.example.claid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Previewscreen extends AppCompatActivity {
    ImageView imageView_preview;
    TextView textView_poss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_previewscreen);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("img_index", 0);
        int ref = mIntent.getIntExtra("ref", 0);
        imageView_preview=findViewById(R.id.imageView4);
        textView_poss=findViewById(R.id.textView9);
        Picasso.with (Previewscreen.this ).load (Constant.d[intValue]).into (imageView_preview);

        if(ref==1)
        {

            Picasso.with (Previewscreen.this ).load (Constant.imagess[intValue]).into (imageView_preview);
            textView_poss.setText(Constant.pose[intValue]);

        }
        if(ref==2)
        {
            Toast.makeText(this, "ref-2"+intValue, Toast.LENGTH_SHORT).show();
            Picasso.with (Previewscreen.this ).load (Constant.imagess2[intValue]).into (imageView_preview);
            textView_poss.setText(Constant.pose2[intValue]);

        }


    }
    public  void backcon(View view){
        finish();
    }
}
