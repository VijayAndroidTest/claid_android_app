package com.example.claid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class measurement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              //  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_measurement);



    }

    public  void chat(View view){

        Intent myIntent = new Intent(measurement.this, Messages.class);

        measurement.this.startActivity(myIntent);
    }
}
