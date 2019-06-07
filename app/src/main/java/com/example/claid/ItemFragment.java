package com.example.claid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class ItemFragment extends Fragment {

    private static final String POSITON = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURE = "resource";

    private int screenWidth;
    private int screenHeight;

    private ArrayList<Game> games;

    private int[] imageArray =  {R.drawable.front_bose, R.drawable.back_up,
            R.drawable.left_side, R.drawable.left_side_up,R.drawable.back,
            R.drawable.rght_side, R.drawable.rght_side_up, R.drawable.front_bose};


    private String[] imageArray2={"/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg","/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg"};
    private String[]bose={"front_bose","back_bose","left_side","left_side_up","back","Right_bose","right side_up","front_bose_up"};

    public static Fragment newInstance(photography_pages context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);
        b.putFloat(SCALE, scale);

        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int postion = this.getArguments().getInt(POSITON);
        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2, screenHeight / 2);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragmentimage, container, false);

        TextView textView = (TextView) linearLayout.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pagerImg);

        textView.setText("" + bose[postion]);
        imageView.setLayoutParams(layoutParams);

       // imageView.setImageURI(Uri.parse("/storage/emulated/0/Pictures/20190422_1630101538136172347905772.jpg"));


        if (Constant.pos_1==0)
        {

            File imgFile = new  File(""+Constant.img_path_1);

            Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);

        }
        else {
            Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),imageArray[postion]);


            imageView.setImageBitmap(bitmap_bk);

        }



        //imageView.setImageDrawable(getResources().getDrawable(imageArray[postion]));

        //handling click event
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ozos_cam.class);
               // intent.putExtra(DRAWABLE_RESOURE, imageArray[postion]);
                startActivity(intent);
            }
        });

        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}
