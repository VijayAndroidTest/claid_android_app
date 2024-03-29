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
import android.os.Handler;
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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.claid.adapter.CoverFlowAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class photography_pages extends AppCompatActivity
        implements AdapterView.OnItemClickListener, View.OnTouchListener,
        CompoundButton.OnCheckedChangeListener {
    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;
    int img_no;
    ToggleButton tButton;
    public  static final int RequestPermissionCode  = 1;
    private static final int CAMERA_REQUEST = 1888;
    private String pathToFile;
    VideoView videoView;
    Button button_play,button_cam,button_video,button_help,button_next;
    Switch switch_tc;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    LinearLayout linearLayout;
    public final static int LOOPS = 1000;
    public CarouselPagerAdapter cpaadapter;
    public ViewPager pager;
    public static int count = 8; //ViewPager items size
    public static int FIRST_PAGE = 8;
    private Bitmap bitmap;
    String[] error,imagedate,pos_name,file;
    DisplayMetrics metrics = new DisplayMetrics();
    int pageMargin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photography_pagess);


        log();
        pager=(ViewPager)findViewById(R.id.myviewpager) ;

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        pageMargin = ((metrics.widthPixels / 4) * 2);
      //  Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
        pager.setPageMargin(-pageMargin);
        cpaadapter = new CarouselPagerAdapter(this, getSupportFragmentManager());

       // cpaadapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(cpaadapter);
        cpaadapter.notifyDataSetChanged();
        pager.addOnPageChangeListener(cpaadapter);
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(3);
        EnableRuntimePermission();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        videoView=findViewById(R.id.videoView3);
        linearLayout=findViewById(R.id.vidlyout);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        videoView.setVisibility(View.GONE);
        button_play=findViewById(R.id.button_play);
        button_play.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        button_play.setVisibility(View.GONE);
       //  button_help=findViewById(R.id.help3);
       // button_help.setVisibility(View.VISIBLE);
        button_cam=findViewById(R.id.button_camara);
        button_next=findViewById(R.id.button9);
       // button_next.setTextColor(R.color.hintcolor);
        button_video=findViewById(R.id.button_video);
        button_video.getBackground().setAlpha(10);
        switch_tc=findViewById(R.id.switch1);
        switch_tc.setOnCheckedChangeListener(this);

        try {
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String Astatus = sharedpreferences.getString("name", "");

            Constant.demo_viediostates = Integer.parseInt(Astatus);

        }
        catch (Exception e){}
        Demo_video();
//      coverFlow.setVisibility(View.GONE);
        log();


        if(Constant.vid_cam == 0) {
         videoView.setVisibility(View.GONE);

          //  log();
            linearLayout.setVisibility(View.GONE);
            button_play.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
          //  button_help.setVisibility(View.INVISIBLE);
            /*New Code*/
//            button_cam.setBackgroundResource(R.drawable.camera_on_onclick);
//            button_cam.getBackground().setAlpha(250);
//            button_video.getBackground().setAlpha(100);
//            button_next.getBackground().setAlpha(100);
//            button_next.setEnabled(false);


            /*Previous Code*/

      //button_video.setBackgroundResource(R.drawable.video_on_onclick);

            button_cam.setBackgroundResource(R.drawable.camera_on_onclick);
            button_cam.getBackground().setAlpha(250);
            button_video.getBackground().setAlpha(10);
            button_next.getBackground().setAlpha(100);
            button_next.setEnabled(false);
            button_video.setBackgroundResource(R.drawable.video_onclick);
            button_video.getBackground().setAlpha(175);
            button_cam.setBackgroundResource(R.drawable.camera_on_onclick);
            button_cam.getBackground().setAlpha(250);
            pager.setVisibility(View.GONE);

        }
        else {
        log();

        }

            videoView.setOnTouchListener(this);


            if(Constant.viediostates==1) {

                linearLayout.setVisibility(View.GONE);
               // coverFlow.setVisibility(View.GONE);
                button_play.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                Uri vid_uri= Uri.parse("/sdcard/myvideo.mp4");
                videoView.setVideoPath(String.valueOf(vid_uri));
                videoView.start();

            }
            else {

            }


        }


        void Demo_video(){

        if(Constant.demo_viediostates==0) {
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("name","1");
            editor.apply();
            Intent myIntent = new Intent(photography_pages.this, video_view_url.class);
            photography_pages.this.startActivity(myIntent);
        }

        }

/* For shown all phose images images  */

    void next_activity() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(photography_pages.this, photography_pages.class);

                photography_pages.this.startActivity(myIntent);
                finish();

            }
        }, 1);
    }

         public void back(View view)
         {

              Intent myIntent = new Intent(photography_pages.this, Profile.class);
              photography_pages.this.startActivity(myIntent);
         }
              public void help_video_url(View view)
              {

                Intent myIntent = new Intent(photography_pages.this, video_view_url.class);
                photography_pages.this.startActivity(myIntent);


                }


            private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
            return new FeatureCoverFlow.OnScrollPositionListener() {
                @Override
                public void onScrolledToPosition(int position) {
                    img_no=position+1;
                    //Constant.pose_no=position;
                  Toast.makeText(photography_pages.this, "pos_no"+position, Toast.LENGTH_SHORT).show();
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

        if (ActivityCompat.shouldShowRequestPermissionRationale(photography_pages.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {

            Toast.makeText(photography_pages.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(photography_pages.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(photography_pages.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(photography_pages.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        }
        else {

            ActivityCompat.requestPermissions(photography_pages.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
       // img_no=position+1;
     //   Toast.makeText(this, "pos"+position, Toast.LENGTH_SHORT).show();

      /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);


        File photo_file=null;
        photo_file=createphotofile();



        if(photo_file!=null){



            pathToFile=photo_file.getAbsolutePath();
            Uri Pathtouri = (Uri) FileProvider.getUriForFile(photography_pages.this,"com.example.claid",photo_file);


            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Pathtouri);
          startActivityForResult(cameraIntent, CAMERA_REQUEST);

        }ConstantTwo
*/

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST) {

        //  image_path_in_constant(img_no,pathToFile);

        }
    }

   public void  okk(View view) {
        if(Constant.img_path_front.length()==0){}

     next_act();
   }

   void next_act() {
       Intent myIntent = new Intent(photography_pages.this, confirmation_screen.class);

       photography_pages.this.startActivity(myIntent);
   }

   @SuppressLint("WrongConstant")
   public void click_video(View view)

   {

       linearLayout.setVisibility(View.GONE);
       button_play.setVisibility(View.GONE);
       pager.setVisibility(View.GONE);

     //  button_help.setVisibility(View.VISIBLE);
       button_video.setBackgroundResource(R.drawable.video_on_onclick);
       button_video.getBackground().setAlpha(250);
       button_cam.setBackgroundResource(R.drawable.camera_onclick);
       button_cam.getBackground().setAlpha(175);

       Constant.viediostates = 1;
      // coverFlow.setVisibility(View.GONE);

   }

    public void click_camara(View view)

    {
      //  Toast.makeText(this, "pos"+Constant.cro_pose, Toast.LENGTH_SHORT).show();
       // log();

       linearLayout.setVisibility(View.GONE);
        button_play.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);

      //  button_help.setVisibility(View.INVISIBLE);

        pager.setVisibility(View.VISIBLE);
        button_video.setBackgroundResource(R.drawable.video_onclick);
        button_video.getBackground().setAlpha(175);
        button_cam.setBackgroundResource(R.drawable.camera_on_onclick);
        button_cam.getBackground().setAlpha(250);
//       coverFlow.setVisibility(View.VISIBLE);

    }




    public  void  play(View view) {
        Intent myIntent = new Intent(photography_pages.this, ozos_vidcam.class);
        photography_pages.this.startActivity(myIntent);
        videoView.setVisibility(View.GONE);
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

/* Service call method to specify url, method, */
    void log () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
      //  Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""
                +Url.getcarousel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               //  Toast.makeText (photography_pages.this, "res: "+response, Toast.LENGTH_LONG ).show ( );

                try {
                    log_json(response);

                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(photography_pages.this, "無效的用戶名或密碼", Toast.LENGTH_SHORT).show();

            }
        }) {

/* Body value from service url session is 21 */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("session", Constant.child_id);

                return params;
            }

/*Headers value from service url Authorization is : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImMxZjY1....*/
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", " Bearer "+Constant.lgg_api);
                return headers;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    private void log_json(String json) throws JSONException
    {

                JSONArray jsonArray = new JSONArray(json);
                error = new String[jsonArray.length()];
                pos_name = new String[jsonArray.length()];
                /*8 poses string from Constant class*/
                Constant.cro_pose = new String[jsonArray.length()];
                file = new String[jsonArray.length()];
                String[] date = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);

                //Constant.cro_pose[i] = null;
            /*Array of objects from json service*/
                pos_name[i] = obj.getString("name");
                file[i] = obj.getString("file");
                /*Matching the Constant string name with service file name*/
                Constant.cro_pose[i] = obj.getString("file");
                Log.d("cor", ":" + obj.getString("file"));

               // pager.setVisibility(View.VISIBLE);

        }

         pager.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if(switch_tc.isChecked()) {


    if(Constant.pos_0 == 1 && Constant.pos_1 ==1 && Constant.pos_2 == 1 && Constant.pos_3 ==1 &&
            Constant.pos_4 == 1 && Constant.pos_5 ==1
    && Constant.pos_6 == 1 && Constant.pos_7 ==1)
    {
        button_next.getBackground().setAlpha(250);
        button_next.setEnabled(true);

    }
    else {
        Toast.makeText(this, "All Pose's Are Mandatory", Toast.LENGTH_SHORT).show();
        switch_tc.setChecked(false);
       }

}else {
    button_next.getBackground().setAlpha(100);
    button_next.setEnabled(false);
    button_next.setTextColor(R.color.hintcolor);


}
    }
}


