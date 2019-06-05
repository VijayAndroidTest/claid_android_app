package com.example.claid.adapter;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claid.Game;
import com.example.claid.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.transform.Result;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStoragePublicDirectory;


public class CoverFlowAdapter extends BaseAdapter {
     Bitmap bitmap;

    String pathToFile;

    private ArrayList<Game> data;

    private AppCompatActivity activity;
    private static final int CAMERA_REQUEST = 1888;
    public CoverFlowAdapter(AppCompatActivity context, ArrayList<Game> objects) {
        this.activity = context;
        this.data = objects;
    }




    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Game getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        File imgFile = new  File("/storage/emulated/0/Pictures/20190415_1417497642145939075741252.jpg");

        bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        //   Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.front);



        ViewHolder viewHolder;


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_flow_view, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        //viewHolder.gameImage.setImageResource(data.get(position).getImageSource());

                viewHolder.gameImage.setImageBitmap(data.get(position).getImageSource());



        viewHolder.gameName.setText(data.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {

        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dispatchpicher();

              //  Toast.makeText(activity, "abc"+data.size(), Toast.LENGTH_SHORT).show();

              //  cam(position);



            }

        };
    }

    public  void cam(int pos){


        dispatchpicher();
        Toast.makeText(activity, "App"+pos, Toast.LENGTH_SHORT).show();

    }

    private void dispatchpicher(){


       // Toast.makeText(this.activity, "dd", Toast.LENGTH_SHORT).show();
        Log.d("path","path:"+pathToFile);
    }






    public static class ViewHolder {
        private TextView gameName;
        private ImageView gameImage;

        public ViewHolder(View v) {
            gameImage = (ImageView) v.findViewById(R.id.image);
            gameName = (TextView) v.findViewById(R.id.name);
        }
    }




}