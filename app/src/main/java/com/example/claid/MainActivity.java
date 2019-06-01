package com.example.claid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private VideoView videoBG;
    EditText editText_username,editText_password;
    ProgressBar progressBar;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    String STpass,STname;
    String[] error,api_key,user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
      getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        videoBG = (VideoView) findViewById(R.id.videoView);
        editText_password= findViewById(R.id.editText_pass);
        editText_username=findViewById(R.id.editText_user);
        progressBar=findViewById(R.id.progressBar2);

        Uri uri = Uri.parse("android.resource://" // First start with this,
                + getPackageName()
                + "/"
                + R.raw.intro);

        videoBG.setVideoURI(uri);

        videoBG.start();


        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;

                mMediaPlayer.setLooping(true);

                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();

        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMediaPlayer.release();
        mMediaPlayer = null;
    }
    public void ok(View view)

    {
      //  progressBar.setVisibility(View.VISIBLE);
        log();


    }

    public void create_ac (View view)

    {
        Intent myIntent = new Intent(MainActivity.this, signup.class);

        MainActivity.this.startActivity(myIntent);

    }


    public  void forgot_password(View view){
        Intent myIntent = new Intent(MainActivity.this, forget.class);

        MainActivity.this.startActivity(myIntent);


    }


    void log () {
        STpass = editText_password.getText().toString();
        STname = editText_username.getText().toString();
      //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText (MainActivity.this, "res: "+response, Toast.LENGTH_LONG ).show ( );

                try {
                    log_json("["+response+"]");
                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "無效的用戶名或密碼", Toast.LENGTH_SHORT).show();

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", STname);
                params.put("password", STpass);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    private void log_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        error = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        user_id = new String[jsonArray.length()];






        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            error[i] = obj.getString("error");
            api_key[i] = obj.getString("access_token");
            user_id[i]=obj.getString("user_id");




        }
        try {
            Constant.lgg_api = api_key[0];
            Constant.lgg_api = api_key[0];
            Constant.user_id=user_id[0];
          if(error[0].equals("0")){

             // Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
              Intent myIntent = new Intent(MainActivity.this, Profile.class);

              MainActivity.this.startActivity(myIntent);


          }
          else {
              Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
          }
            // Toast.makeText ( this, "vv"+constant.sms_states, Toast.LENGTH_SHORT ).show ( );

        }catch (Exception e){

           Toast.makeText(this, "Error Code : Msg-201 ", Toast.LENGTH_SHORT).show();
        }


    }

}
