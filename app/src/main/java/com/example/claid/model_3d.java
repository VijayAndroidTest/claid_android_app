package com.example.claid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.claid.adapter.CoverFlowAdapter3d;

import java.io.File;
import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class model_3d extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter3d adapter;
    private ArrayList<Game3d> gamess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_model_3d);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter3d(this, gamess);
        coverFlow.setAdapter(adapter);

    }


    private void settingDummyData() {
        //Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();

        try {
            gamess.clear();
        }catch (Exception e){}
        gamess = new ArrayList<>();

        if (Constant.pos_1==1)
        {
            File imgFile = new  File(""+Constant.img_path_1);

            Bitmap bitmap= BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "Right "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Righr"));
        }
        if (Constant.pos_2==1)
        {
            File imgFile = new  File(""+Constant.img_path_2);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "Right Pose Up "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Righr Pose up"));
        }


        if (Constant.pos_3==1)
        {

            File imgFile = new  File(""+Constant.img_path_3);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "FRONT POSE1"));
        }
        else {
            Bitmap bitmap_fr = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_fr, "FRONT POSE"));
        }


        if (Constant.pos_4==1)
        {
            File imgFile = new  File(""+Constant.img_path_4);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "Hand Up"));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Hand Up"));
        }


        if (Constant.pos_5==1)
        {

            File imgFile = new  File(""+Constant.img_path_5);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "Left Pose"));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Left Pose"));
        }

        if (Constant.pos_6==1)
        {
            File imgFile = new  File(""+Constant.img_path_6);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "Left Pose Up"));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Left Pose Up"));
        }


        if (Constant.pos_7==1)
        {
            File imgFile = new  File(""+Constant.img_path_7);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "bake Pose "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Back Pose"));
        }
        if (Constant.pos_8==1)
        {
            File imgFile = new  File(""+Constant.img_path_8);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            gamess.add(new Game3d(bitmap, "bake Pose Up "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Back Pose Up"));
        }





    }
}
