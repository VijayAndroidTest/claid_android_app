package com.example.claid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

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

        if (Constant.img_path_neck_right.length()!=1)
        {
            File imgFile = new  File(""+Constant.img_path_left);

            Bitmap bitmap= BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "Right "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Righr"));
        }
        if (Constant.img_path_right.length()!=1)
        {
            File imgFile = new  File(""+Constant.img_path_front);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "Right Pose Up "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Righr Pose up"));
        }


        if (Constant.img_path_front.length()!=0)
        {

            File imgFile = new  File(""+Constant.img_path_back);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "FRONT POSE1"));
        }
        else {
            Bitmap bitmap_fr = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_fr, "FRONT POSE"));
        }


        if (Constant.img_path_front.length()!=1)
        {
            File imgFile = new  File(""+Constant.img_path_croauch);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "Hand Up"));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Hand Up"));
        }


        if (Constant.img_path_neck_left.length()!=1)
        {

            File imgFile = new  File(""+Constant.img_path_right);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "Left Pose"));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Left Pose"));
        }

        if (Constant.img_path_left.length()!=1)
        {
            File imgFile = new  File(""+Constant.img_path_neck_left);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "Left Pose Up"));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Left Pose Up"));
        }


        if (Constant.img_path_back.length()!=1)
        {
            File imgFile = new  File(""+Constant.img_path_neck_right);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "bake Pose "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Back Pose"));
        }
        if (Constant.img_path_croauch.length()!=1)
        {
            File imgFile = new  File(""+Constant.img_path_neck_front);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
            gamess.add(new Game3d(bitmap, "bake Pose Up "));
        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.slider_img);
            gamess.add(new Game3d(bitmap_bk, "Back Pose Up"));
        }





    }
}
