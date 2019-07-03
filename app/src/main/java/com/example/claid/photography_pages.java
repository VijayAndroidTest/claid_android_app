package com.example.claid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.claid.adapter.CoverFlowAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class photography_pages extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnTouchListener {
    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;
    int img_no;
    ToggleButton tButton;
    public  static final int RequestPermissionCode  = 1;
    private static final int CAMERA_REQUEST = 1888;
    private String pathToFile;
    VideoView videoView;
    Button button_play,button_cam,button_video,button_help;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    LinearLayout linearLayout;
    public final static int LOOPS = 1000;
    public CarouselPagerAdapter cpaadapter;
    public ViewPager pager;
    public static int count = 8; //ViewPager items size
    public static int FIRST_PAGE = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photography_pagess);
        pager=(ViewPager)findViewById(R.id.myviewpager) ;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 4) * 2);
        pager.setPageMargin(-pageMargin);
        cpaadapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(cpaadapter);
        cpaadapter.notifyDataSetChanged();
        pager.addOnPageChangeListener(cpaadapter);
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(2);
        EnableRuntimePermission();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        videoView=findViewById(R.id.videoView3);
        linearLayout=findViewById(R.id.vidlyout);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        videoView.setVisibility(View.VISIBLE);
        button_play=findViewById(R.id.button_play);
        button_play.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        button_play.setVisibility(View.VISIBLE);
        button_help=findViewById(R.id.help3);
        button_help.setVisibility(View.VISIBLE);
//      coverFlow.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
        button_cam=findViewById(R.id.button_camara);
        button_video=findViewById(R.id.button_video);
        button_video.setBackgroundResource(R.drawable.video_on_onclick);
        button_video.getBackground().setAlpha(250);
        button_cam.getBackground().setAlpha(100);
        pager.setVisibility(View.GONE);
        settingDummyData();
           // adapter = new CoverFlowAdapter(this, games);
           // coverFlow.setAdapter(adapter);
           // coverFlow.setOnScrollPositionListener(onScrollListener());
          //  coverFlow.setOnItemClickListener(this);
            videoView.setOnTouchListener(this);

try {
    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    String Astatus = sharedpreferences.getString("name", "");

    Constant.demo_viediostates = Integer.parseInt(Astatus);



}catch (Exception e){}

            Demo_video();



            if(Constant.viediostates==1){

                linearLayout.setVisibility(View.VISIBLE);
               // coverFlow.setVisibility(View.GONE);
                button_play.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                Uri vid_uri= Uri.parse("/sdcard/myvideo.mp4");
                videoView.setVideoPath(String.valueOf(vid_uri));
                videoView.start();

            }
            else {



            }


        }


        void Demo_video(){

        if(Constant.demo_viediostates==0){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("name","1");
            editor.apply();
            Intent myIntent = new Intent(photography_pages.this, video_view_url.class);
            photography_pages.this.startActivity(myIntent);
        }


        }


         public void back(View view){

              Intent myIntent = new Intent(photography_pages.this, Profile.class);
              photography_pages.this.startActivity(myIntent);


          }




            private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
            return new FeatureCoverFlow.OnScrollPositionListener() {
                @Override
                public void onScrolledToPosition(int position) {
                    img_no=position+1;
            //       Toast.makeText(photography_pages.this, "spos"+img_no, Toast.LENGTH_SHORT).show();
                    Log.v("MainActiivty", "position: " + position);


                }

                @Override
                public void onScrolling() {
                    Log.i("MainActivity", "scrolling");

                }
            };
        }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(photography_pages.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(photography_pages.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(photography_pages.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

        private void settingDummyData() {
            //Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();

try {
    games.clear();
}catch (Exception e){}
            games = new ArrayList<>();

            if (Constant.pos_1==1)
            {
                File imgFile = new  File(""+Constant.img_path_1);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "Right "));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.rght_side);
                games.add(new Game(bitmap_bk, "Righr"));
            }
            if (Constant.pos_2==1)
            {
                File imgFile = new  File(""+Constant.img_path_2);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "Right Pose Up "));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.rght_side_up);
                games.add(new Game(bitmap_bk, "Righr Pose up"));
            }


            if (Constant.pos_3==1)
            {

                File imgFile = new  File(""+Constant.img_path_3);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "FRONT POSE1"));
            }
            else {
                Bitmap bitmap_fr = BitmapFactory.decodeResource(getResources(),R.drawable.front_bose);
                games.add(new Game(bitmap_fr, "FRONT POSE"));
            }


            if (Constant.pos_4==1)
            {
                File imgFile = new  File(""+Constant.img_path_4);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "Hand Up"));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.hand_up);
                games.add(new Game(bitmap_bk, "Hand Up"));
            }


            if (Constant.pos_5==1)
            {
                File imgFile = new  File(""+Constant.img_path_5);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "Left Pose"));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.left_side);
                games.add(new Game(bitmap_bk, "Left Pose"));
            }

            if (Constant.pos_6==1)
            {
                File imgFile = new  File(""+Constant.img_path_6);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "Left Pose Up"));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.left_side);
                games.add(new Game(bitmap_bk, "Left Pose Up"));
            }


            if (Constant.pos_7==1)
            {
                File imgFile = new  File(""+Constant.img_path_7);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "bake Pose "));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.back);
                games.add(new Game(bitmap_bk, "Back Pose"));
            }
            if (Constant.pos_8==1)
            {
                File imgFile = new  File(""+Constant.img_path_8);

                Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                games.add(new Game(bitmap, "bake Pose Up "));
            }
            else {
                Bitmap bitmap_bk = BitmapFactory.decodeResource(getResources(),R.drawable.back_up);
                games.add(new Game(bitmap_bk, "Back Pose Up"));
            }





        }







    @Override
    public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
       // img_no=position+1;
     //   Toast.makeText(this, "pos"+position, Toast.LENGTH_SHORT).show();

      Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);


        File photo_file=null;
        photo_file=createphotofile();



        if(photo_file!=null){



            pathToFile=photo_file.getAbsolutePath();
            Uri Pathtouri = (Uri) FileProvider.getUriForFile(photography_pages.this,"com.example.claid",photo_file);


            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Pathtouri);
           startActivityForResult(cameraIntent, CAMERA_REQUEST);

        }


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST) {



            image_path_in_constant(img_no,pathToFile);

        }
    }

    void upload (final Bitmap bitmaps) {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.upload, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText (photography_pages.this, "res: "+response, Toast.LENGTH_LONG ).show ( );




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Toast.makeText(MainActivity.this, "無效的用戶名或密碼", Toast.LENGTH_SHORT).show();

            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", " Bearer "+Constant.lgg_api);
                return headers;
            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("title", Constant.user_id);
                params.put("file", image_to_string(bitmaps));
                params.put("imagename", "x");



                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

    private String image_to_string( Bitmap bitmap1){

        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] imageBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }


    private File createphotofile(){
        String name =new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir= getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try {
            image=File.createTempFile(name,".jpg",storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }
   public void image_path_in_constant(int mimg_no,String path) {
       Log.d("path","path:"+pathToFile);
       if (mimg_no == 1) {
           Constant.pos_1 = 1;
           Constant.img_path_1= path;
           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }

           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
         //  coverFlow.setAdapter(adapter);
       }
       if (mimg_no == 2) {
           Constant.pos_2 = 1;
           Constant.img_path_2 = path;

           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }
       if (mimg_no == 3) {

           Toast.makeText(this, "img-3", Toast.LENGTH_SHORT).show();
           Constant.pos_3 = 1;
           Constant.img_path_3 = path;

           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }


       if (mimg_no == 4) {
           Constant.pos_4 = 1;
           Constant.img_path_4= path;

           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           //adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }
       if (mimg_no == 5) {
           Constant.pos_5 = 1;
           Constant.img_path_5 = path;

           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }
       if (mimg_no == 6) {

           Toast.makeText(this, "img-3", Toast.LENGTH_SHORT).show();
           Constant.pos_6 = 1;
           Constant.img_path_6 = path;

           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }


       if (mimg_no == 7) {
           Constant.pos_7 = 1;
           Constant.img_path_7= path;

           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }
       if (mimg_no == 8) {
           Constant.pos_8 = 1;
           Constant.img_path_8 = path;
           try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(path));
               Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
               upload(bitmap);


           } catch (IOException e) {
               e.printStackTrace();
           }
           games.clear();
           settingDummyData();

           adapter = new CoverFlowAdapter(this, games);
          // coverFlow.setAdapter(adapter);
       }


   }

   public void  okk(View view){
        if(Constant.img_path_1.length()==0){}

     next_act();
   }

   void next_act(){
       Intent myIntent = new Intent(photography_pages.this, confirmation_screen.class);

       photography_pages.this.startActivity(myIntent);
   }

   @SuppressLint("WrongConstant")
   public void click_video(View view)

   {

       linearLayout.setVisibility(View.VISIBLE);
       button_play.setVisibility(View.VISIBLE);
       pager.setVisibility(View.GONE);

       button_help.setVisibility(View.VISIBLE);
       button_video.setBackgroundResource(R.drawable.video_on_onclick);
       button_video.getBackground().setAlpha(250);
       button_cam.setBackgroundResource(R.drawable.camera_onclick);
       button_cam.getBackground().setAlpha(175);

       Constant.viediostates = 1;
      // coverFlow.setVisibility(View.GONE);

   }

    public void click_camara(View view)

    {

        linearLayout.setVisibility(View.GONE);
        button_play.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
        button_help.setVisibility(View.INVISIBLE);
        pager.setVisibility(View.VISIBLE);
        button_video.setBackgroundResource(R.drawable.video_onclick);
        button_video.getBackground().setAlpha(175);
        button_cam.setBackgroundResource(R.drawable.camera_on_onclick);
        button_cam.getBackground().setAlpha(250);
      //  coverFlow.setVisibility(View.VISIBLE);

    }

    public  void  play(View view){
        Intent myIntent = new Intent(photography_pages.this, ozos_vidcam.class);

        photography_pages.this.startActivity(myIntent);
        videoView.setVisibility(View.VISIBLE);
        button_play.setVisibility(View.GONE);
        Constant.viediostates=1;

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Uri vid_uri= Uri.parse("/sdcard/myvideo.mp4");
        videoView.setVideoPath(String.valueOf(vid_uri));
        videoView.start();
        return false;
    }
}


