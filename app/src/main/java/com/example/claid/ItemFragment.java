package com.example.claid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemFragment extends Fragment {

    private static final String POSITON = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURE = "resource";
    private static Object photography_pages;
    private int screenWidth;
    private int screenHeight;

    private float Width,height;
    List<Bitmap> month = new ArrayList<Bitmap>();
    private ArrayList<Game> games;
    public static String []cro_pose ;



//    private String[]bose={"1.左姿势","2.颈部左","3.返回姿势",
//            "4.裆","5.右边","6.右颈","7.前姿势","8.颈前",};
    // Showig only

    private String[]bose={"LEFT SIDE","NECK LEFT","BACK POSE","CROATCH",
            "RIGHT SIDE","NECK RIGHT","FRONT POSE","NECK FRONT"};


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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int postion = this.getArguments().getInt(POSITON);

        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(screenWidth /2, screenHeight /2);

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragmentimage,
                container, false);
        TextView textView = (TextView) linearLayout.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pagerImg);
        textView.setText("" + bose[postion]);
         Constant.pose_name=bose[postion];
         imageView.setLayoutParams(layoutParams);
//       Toast.makeText(container.getContext(), "cc"+Constant.cro_pose[postion]+ "pos  :"+postion, Toast.LENGTH_SHORT).show();
 //      imageView.setImageURI(Uri.parse(String.valueOf(Constant.cro_pose[postion])));

//        if (Constant.cro_pose[postion]==null) {
//
//            Picasso.with(container.getContext()).load(cro_pose[postion]).into(imageView);
//            Constant.pose_no = postion;
//            Constant.pose_name = bose[postion];
//
//        }


   if (Constant.cro_pose[postion]!=null) {

   // imageView.setImageURI(Uri.parse(String.valueOf(Constant.cro_pose[postion])));
    Picasso.with(container.getContext()).load(Constant.cro_pose[postion]).into(imageView);
    Constant.pose_no = postion;
    Constant.pose_name = bose[postion];


     }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pose_val(postion);
                if(Constant.pos_0 == 1) {

                    Log.d("Width", "Width:" + bose[postion]);
                    Constant.pose_name = bose[postion];
                    Constant.pose_no = postion;
                    Intent intent = new Intent(getActivity(), zoso_cam3.class);
                    //intent.putExtra(DRAWABLE_RESOURE, imageArray[postion]);
                    startActivity(intent);}
                     else {
                        Toast.makeText(container.getContext(), "First You Take Left Side Poses Mandatory", Toast.LENGTH_SHORT).show();

                    }

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


    void pose_val(int mimg_no){


        if (mimg_no == 0) {
            Constant.pos_0 = 1;
            // Constant.img_path_1= path;

        }
        if (mimg_no == 1) {
            Constant.pos_1 = 1;
            // Constant.img_path_2 = path;

        }
        if (mimg_no == 2) {


            Constant.pos_2 = 1;
            // Constant.img_path_3 = path;


        }


        if (mimg_no == 3) {
            Constant.pos_3 = 1;
            // Constant.img_path_4= path;

        }
        if (mimg_no == 4) {
            Constant.pos_4 = 1;
            //  Constant.img_path_5 = path;


        }
        if (mimg_no == 5) {

            Constant.pos_5 = 1;
            // Constant.img_path_6 = path;


        }


        if (mimg_no == 6) {
            Constant.pos_6 = 1;
            // Constant.img_path_7= path;


        }
        if (mimg_no == 7) {
            Constant.pos_7 = 1;
            // Constant.img_path_8 = path;

        }

    }


}
