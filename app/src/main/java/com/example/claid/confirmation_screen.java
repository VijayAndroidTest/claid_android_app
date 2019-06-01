package com.example.claid;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claid.adapter.confirmation_Adapter;

public class  confirmation_screen extends AppCompatActivity {
    TextView textView_hight,textView_weight;
  private confirmation_Adapter adapter;
     private  String[] imagess={

             Constant.img_path_3,Constant.img_path_5,Constant.img_path_7, Constant.img_path_1
     };
    private  String[] pose={

            "FRONT POSE"," BACK POSE ","LEFT POSE","RIGHT POSE"
    };
    private  String[] imagess2={
            Constant.img_path_4,Constant.img_path_6,Constant.img_path_8, Constant.img_path_2
    };
    private  String[] pose2={

            "FRONT UP","BACK UP","LEFT UP", "RIGHT UP"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirmation_screen);
        ViewPager viewPager=findViewById(R.id.viewpager);
        textView_hight=findViewById(R.id.textView_height);
        textView_weight=findViewById(R.id.textView_weight);
        textView_weight.setText("78");
        textView_hight.setText("165");
        adapter=new confirmation_Adapter(confirmation_screen.this,imagess,imagess2,pose,pose2);
        viewPager.setAdapter(adapter);

    }


    public  void okk(View view){
        Intent myIntent = new Intent(confirmation_screen.this, measurement.class);

        confirmation_screen.this.startActivity(myIntent);

    }
}
