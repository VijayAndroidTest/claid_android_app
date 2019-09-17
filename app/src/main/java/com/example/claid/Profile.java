package com.example.claid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.example.claid.R.drawable.edit_text_focuses_bg;
import static com.example.claid.R.drawable.edit_text_normal_bg;

public class Profile extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    EditText editText_name,editText_age,editText_height,editText_weight;

    String[] country = new String[]{ "Head","mid_neck","neck_base"," Front Shoulder","Shoulder",
            " chest","underbust","neck_base"," Front Shoulder","Shoulder"};
    String[] age,id,weight,height,username,child_id;
    Button button_save,button_cam,button_female,button_male;
    TextView textView_male,textView_female;
    String sex="male";
ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        Button button_back=findViewById(R.id.button8);
        button_save=findViewById(R.id.button_save);
        button_cam=findViewById(R.id.button_camara);
        button_female=findViewById(R.id.button_female);
        button_male=findViewById(R.id.button_male);
        textView_female=(findViewById(R.id.textView16));
        textView_male=(findViewById(R.id.textView15));
        button_save.getBackground().setAlpha(50);
        button_cam.getBackground().setAlpha(50);
        button_save.setTextColor(Color.parseColor("#8A8687"));
        textView_female.setTextColor(Color.parseColor("#8A8687"));
        button_female.getBackground().setAlpha(100);
        button_save.setEnabled(true);
        progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        final Animation animTranslate2 = AnimationUtils.loadAnimation(this, R.anim.anim_translatee);
        final Animation animTranslate3 = AnimationUtils.loadAnimation(this, R.anim.anim_translateee);
        final Animation animTranslate4 = AnimationUtils.loadAnimation(this, R.anim.anim_translateeee);
        editText_age=findViewById(R.id.editText_age);
        editText_name=findViewById(R.id.editText_name);
        editText_height=findViewById(R.id.editText_height);
        editText_weight=findViewById(R.id.editText_weight);
        editText_name.setOnClickListener(this);
        editText_name.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {


        editText_weight.setBackgroundResource(edit_text_normal_bg);
        editText_age.setBackgroundResource(edit_text_normal_bg);
        editText_height.setBackgroundResource(edit_text_normal_bg);
        editText_name.setBackgroundResource(edit_text_focuses_bg);


        if(editText_weight.getText().toString().length()>=1 && editText_height.getText().toString().length()>=1 &&editText_age.getText().toString().length()>=1&&editText_name.getText().toString().length()>=1){

            button_save.getBackground().setAlpha(250);
            button_save.setTextColor(Color.parseColor("#ffffff"));
            button_save.setEnabled(true);


        }
        else {
            button_save.getBackground().setAlpha(50);
            button_save.setTextColor(Color.parseColor("#8A8687"));
            button_save.setEnabled(false);


        }

    }
});

    editText_age.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            editText_name.setBackgroundResource(edit_text_normal_bg);
            editText_weight.setBackgroundResource(edit_text_normal_bg);
            editText_height.setBackgroundResource(edit_text_normal_bg);
            editText_age.setBackgroundResource(edit_text_focuses_bg);
            try{
                if(100 >= Integer.parseInt(editText_age.getText().toString())){}
                else {
                    editText_age.setText("");
                    Toast.makeText(Profile.this, "Max age 100", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){ }


            if(editText_weight.getText().toString().length()>=1 && editText_height.getText().toString().length()>=1 &&editText_age.getText().toString().length()>=1&&editText_name.getText().toString().length()>=1){



                button_save.getBackground().setAlpha(250);
                button_save.setEnabled(true);
            }
            else {
                button_save.getBackground().setAlpha(50);
                button_save.setTextColor(Color.parseColor("#8A8687"));
                button_save.setEnabled(false);



            }


        }
    });


        editText_height.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {


            editText_name.setBackgroundResource(edit_text_normal_bg);
            editText_age.setBackgroundResource(edit_text_normal_bg);
            editText_weight.setBackgroundResource(edit_text_normal_bg);
            editText_height.setBackgroundResource(edit_text_focuses_bg);
          try{
            if(198 >= Integer.parseInt(editText_height.getText().toString())){}
            else {
                editText_height.setText("");
                Toast.makeText(Profile.this, "Max height 198 Cm", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){ }

            if(editText_weight.getText().toString().length()>=1 && editText_height.getText().toString().length()>=1 &&editText_age.getText().toString().length()>=1&&editText_name.getText().toString().length()>=1){



                button_save.getBackground().setAlpha(250);
                button_save.setTextColor(Color.parseColor("#ffffff"));
                button_save.setEnabled(true);
            }
            else {
                button_save.getBackground().setAlpha(50);
                button_save.setTextColor(Color.parseColor("#8A8687"));
                button_save.setEnabled(false);



            }


        }
    });

        editText_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                editText_name.setBackgroundResource(edit_text_normal_bg);
                editText_age.setBackgroundResource(edit_text_normal_bg);
                editText_height.setBackgroundResource(edit_text_normal_bg);
                editText_weight.setBackgroundResource(edit_text_focuses_bg);
                try{
                if(150 >= Integer.parseInt(editText_weight.getText().toString())){}
                else {
                    editText_weight.setText("");
                    Toast.makeText(Profile.this, "Max weight 150 Kg", Toast.LENGTH_SHORT).show();
                }
                }catch (Exception e){ }

                if(editText_weight.getText().toString().length()>=1 && editText_height.getText().toString().length()>=1 &&editText_age.getText().toString().length()>=1&&editText_name.getText().toString().length()>=1){



                    button_save.getBackground().setAlpha(250);
                    button_save.setTextColor(Color.parseColor("#ffffff"));
                    button_save.setEnabled(true);
                }
                else {
                    button_save.getBackground().setAlpha(50);
                    button_save.setTextColor(Color.parseColor("#8A8687"));
                    button_save.setEnabled(false);



                }



            }
        });
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.bodyparts,country
        );
    details();



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clickcamara (View view)

    {
        if(editText_name.getText().length()==0){
            Toast.makeText(this, "User Name Field is Empty\n用户名字段为空", Toast.LENGTH_SHORT).show();
            editText_name.requestFocus();
        }

        else {

            if(editText_age.getText().length()==0){
                Toast.makeText(this, "User age Field is  Empty\n用户年龄字段为空", Toast.LENGTH_SHORT).show();
                editText_age.requestFocus();
            }

            else {
                if(editText_height.getText().length()==0){
                    Toast.makeText(this, "User Height Field is Empty\n用户高度字段为空", Toast.LENGTH_SHORT).show();
                    editText_height.requestFocus();
                }

                else {
                    if(editText_weight.getText().length()==0){
                        Toast.makeText(this, "User Weight Field is Empty\n用户权重字段为空", Toast.LENGTH_SHORT).show();
                        editText_weight.requestFocus();
                    }

                    else {
                        Constant.mname=editText_name.getText().toString();
                        Constant.age=editText_age.getText().toString();
                        Constant.height=editText_height.getText().toString();
                        Constant.weight=editText_weight.getText().toString();
                        next_act();

                    }

                }

            }

        }
    }

    void next_act(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(Profile.this, photography_pages.class);

                Profile.this.startActivity(myIntent);
                finish();

            }
        }, 1);
    }

   public void back(View view){
        Intent myIntent = new Intent(Profile.this, MainActivity.class);

        Profile.this.startActivity(myIntent);

    }

    public void profile_inf(View view){
        Intent myIntent = new Intent(Profile.this, Parentprofile.class);

        Profile.this.startActivity(myIntent);

    }


    void details () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.Details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               //  Toast.makeText (Profile.this, "res: "+response, Toast.LENGTH_LONG ).show ( );

                try {
                    details_json("["+response+"]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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



                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    private void details_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        id = new String[jsonArray.length()];
        age = new String[jsonArray.length()];
        height = new String[jsonArray.length()];
        weight = new String[jsonArray.length()];
        username = new String[jsonArray.length()];



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            id[i] = obj.getString("id");
            age[i] = obj.getString("age");
            height[i] = obj.getString("height");
            weight[i] = obj.getString("weight");
            username[i] = obj.getString("username");



        }


        Constant.weight=weight[0];
        Constant.height=height[0];
        if(age[0].isEmpty()){}
        else {

           // editText_age.setText(age[0]);

        }

        if(height[0].isEmpty()){}
        else {

           // editText_height.setText(height[0]+" (Cm)");

        }

        if(weight[0].isEmpty()){}
        else {



        }
      //  editText_name.setText(username[0]);



    }




    void updat () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.crear_child, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
              Toast.makeText(Profile.this, "child"+response, Toast.LENGTH_SHORT).show();
                try {
                    details_json2("["+response+"]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                button_cam.getBackground().setAlpha(250);
                button_cam.setEnabled(true);
                button_save.setTextColor(Color.parseColor("#00ffde"));
                button_save.setText("Save");
                editText_age.setEnabled(false);
                editText_height.setEnabled(false);
                editText_name.setEnabled(false);
                editText_weight.setEnabled(false);

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

                params.put("name",editText_name.getText().toString());
                params.put("age", editText_age.getText().toString());
                params.put("weight", editText_weight.getText().toString());
                params.put("height", editText_height.getText().toString());
                params.put("sex", ""+sex);
                params.put("platform", "android");


                return params;
            }



        };
        Volley.newRequestQueue(this).add(request);

    }


    private void details_json2(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        child_id = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            child_id[i] = obj.getString("id");
        }
       Constant.child_id=child_id[0];
    }

    public void save(View view){
        progressBar.setVisibility(View.VISIBLE);

        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
        button_save.startAnimation(startAnimation);
        updat();
        button_save.setEnabled(false);
    }


    @Override
    public void onClick(View v) {


    }

    public void female_click(View view){
        sex="female";
        textView_male.setTextColor(Color.parseColor("#8A8687"));
        textView_female.setTextColor(Color.parseColor("#ffffff"));


        button_male.getBackground().setAlpha(100);
        button_female.getBackground().setAlpha(250);


    }


    public void male_click(View view){
        sex="male";
        textView_female.setTextColor(Color.parseColor("#8A8687"));
        textView_male.setTextColor(Color.parseColor("#ffffff"));


        button_male.getBackground().setAlpha(250);
        button_female.getBackground().setAlpha(100);


    }

    public void history(View view){
        Intent myIntent = new Intent(Profile.this, chilid_history.class);

        Profile.this.startActivity(myIntent);

    }
}
