package com.example.claid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.claid.adapter.ConnectivityReceiver;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.claid.R.drawable.edit_text_focuses_bg;
import static com.example.claid.R.drawable.edit_text_normal_bg;

public class MainActivity extends AppCompatActivity implements
        ConnectivityReceiver.ConnectivityReceiverListener {

    //dhilipan

    private VideoView videoBG;
    EditText editText_username,editText_password;
    ProgressBar progressBar;
    MediaPlayer mMediaPlayer;
    LinearLayout linearLayout;
    int mCurrentVideoPosition;
    String STpass,STname;
    String[] error,api_key,user_id;
    private static final int STORAGE_PERMISSION_CODE = 2342;
    Button button_go;
    String tok;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        tok = FirebaseInstanceId.getInstance().getToken();

        if (tok!=null)

        // Log.v(":Tokes",token.getText().toString());
        Log.i(":Tokes", tok);


        checkConnection();
        Constant.vid_cam=0;
        requestStoragePermission();

        videoBG = (VideoView) findViewById(R.id.videoView);
        linearLayout=findViewById(R.id.linearlayout);
        linearLayout.setGravity(Gravity.CENTER );
        editText_password= findViewById(R.id.editText_pass);
        editText_username=findViewById(R.id.editText_user);

       STname = editText_username.getText().toString();
       STpass = editText_password.getText().toString();
       // button_go.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        progressBar=findViewById(R.id.progressBar2);
        button_go=findViewById(R.id.button_go);
        button_go.getBackground().setAlpha(100);


        editText_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {


                if (editText_password.getText().toString().length()>=1 ) {
                    button_go.getBackground().setAlpha(250);
                    //  Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                    // Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing to do here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // user is typing: reset already started timer (if existing)
                if (editText_password.getText().toString().length()>=1 ) {
                    button_go.getBackground().setAlpha(250);
                    //  Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                    //  Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }

            }
        });


        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (editText_password.getText().toString().length()>=1 ) {
                    button_go.getBackground().setAlpha(250);
                    // Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                    //  Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }



//                editText_name.setBackgroundResource(edit_text_normal_bg);
//                editText_phonenumber.setBackgroundResource(edit_text_normal_bg);
//                editText_cpass.setBackgroundResource(edit_text_normal_bg);
//                editText_pass.setBackgroundResource(edit_text_focuses_bg);



            }

        });

        progressBar.setVisibility(View.GONE);

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


      //mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

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
        progressBar.setVisibility(View.VISIBLE);
        button_go.animate().rotation(button_go.getRotation()+180).start();

        // Service Call
        log();


        //Firebase


        tok = FirebaseInstanceId.getInstance().getToken();
        // Log.v(":Tokes",token.getText().toString());
        Log.i(":Tokes", tok);


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
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""
                +Url.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
             //  Toast.makeText (MainActivity.this, "res: "+response, Toast.LENGTH_LONG ).show ( );

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
                params.put("fcm_token", tok);
                params.put("os", "1");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    private void log_json(String json) throws JSONException
    {

        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        user_id = new String[jsonArray.length()];
        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            error[i] = obj.getString("error");
            api_key[i] = obj.getString("access_token");
            try
            {
            user_id[i]=obj.getString("user_id");
            }
            catch (Exception e){}
        }
        try {
            Constant.lgg_api = api_key[0];
            Constant.lgg_api = api_key[0];
            Constant.user_id=user_id[0];
          if(error[0].equals("0"))
          {
             // Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
              Intent myIntent = new Intent(MainActivity.this, Profile.class);

              MainActivity.this.startActivity(myIntent);


          }
              else
                  {
                  Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
                  }
                // Toast.makeText ( this, "vv"+constant.sms_states, Toast.LENGTH_SHORT ).show ( );

        }
            catch (Exception e)
            {
                Toast.makeText(this, "Error Code : Msg-201 ", Toast.LENGTH_SHORT).show();
            }


    }


    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE)
        {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else
                {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        if (isConnected) {
            setContentView(R.layout.activity_main);

        } else {
            setContentView(R.layout.internet_screen);
    }
    }



    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

}
