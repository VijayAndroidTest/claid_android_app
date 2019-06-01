package com.example.claid;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyAdapter  extends PagerAdapter {
    private String image_resources=(Constant.img_path_1);


    @Override
    public int getCount() {
        return image_resources.length();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {


        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
