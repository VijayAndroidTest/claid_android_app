package com.example.claid;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claid.adapter.confirmation_Adapter;

public class  confirmation_screen extends AppCompatActivity {
    TextView textView_hight,textView_weight;
  private confirmation_Adapter adapter;
     private  String[] imagess={

             Constant.cro_pose[0], Constant.cro_pose[2], Constant.cro_pose[4], Constant.cro_pose[6]
     };

    private  String[] imagess2={
            Constant.cro_pose[1], Constant.cro_pose[3], Constant.cro_pose[5],  Constant.cro_pose[7]
    };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirmation_screen);
        ViewPager viewPager=findViewById(R.id.viewpager);
        textView_hight=findViewById(R.id.textView_height);
        textView_weight=findViewById(R.id.textView_weight);
        textView_weight.setText(""+Constant.weight);
        textView_hight.setText(""+Constant.height);
        adapter=new confirmation_Adapter(confirmation_screen.this,imagess,imagess2,Constant.pose,Constant.pose2);
        viewPager.setAdapter(adapter);


    }


    public  void okk(View view)
    {
        Intent myIntent = new Intent(confirmation_screen.this, measurement.class);

        confirmation_screen.this.startActivity(myIntent);

    }

    public  void back(View view){
        Intent myIntent = new Intent(confirmation_screen.this, photography_pages.class);

        confirmation_screen.this.startActivity(myIntent);

    }
}
