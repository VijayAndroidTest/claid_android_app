package com.example.claid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claid.Previewscreen;
import com.example.claid.R;
import com.example.claid.confirmation_screen;
import com.example.claid.photography_pages;

public class confirmation_Adapter extends PagerAdapter {


    Activity activity;
    String[] images,images2,mpose,mpose2;
    LayoutInflater inflater;

    public confirmation_Adapter(Activity activity,String[] imagess,String[] imagess2,String[]pose,String[] pose2){
        this.activity=activity;
       this.images=imagess;
        this.images2=imagess2;
        this.mpose=pose;
        this.mpose2=pose2;


    }




    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        inflater=(LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate((R.layout.confirmation_templet),container,false);
        ImageView image,image2;

        TextView textView_hight,textView_weight,textView_pose_left,textView_pose_right;

        image =(ImageView)itemView.findViewById(R.id.imageView2);
        image2 =(ImageView)itemView.findViewById(R.id.imageView3);

      Animation animSlide = AnimationUtils.loadAnimation(activity,
                R.anim.slide);
        image.startAnimation(animSlide);
        image2.startAnimation(animSlide);

        textView_hight=(TextView)itemView.findViewById(R.id.textView_height) ;
        textView_weight=(TextView)itemView.findViewById(R.id.textView_weight) ;
        textView_pose_left=(TextView)itemView.findViewById(R.id.textView_pose_left) ;
        textView_pose_right=(TextView)itemView.findViewById(R.id.textView_pose_right) ;
      //  image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            image.setImageURI(Uri.parse(images[position]));
            image2.setImageURI(Uri.parse(images2[position]));
            textView_pose_left.setText(mpose[position]);
            textView_pose_right.setText(mpose2[position]);
;

        image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(activity, Previewscreen.class);
                myIntent.putExtra("img_index", position);
                myIntent.putExtra("ref", 1);
                activity.startActivity(myIntent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(activity, Previewscreen.class);
                myIntent.putExtra("img_index", position);
                myIntent.putExtra("ref", 2);
                activity.startActivity(myIntent);
            }
        });

        container.addView(itemView);
return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ( (ViewPager)container).removeView((View)object);
    }
}
