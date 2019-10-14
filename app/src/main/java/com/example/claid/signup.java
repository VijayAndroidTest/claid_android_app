package com.example.claid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.claid.R.drawable.edit_text_focuses_bg;
import static com.example.claid.R.drawable.edit_text_normal_bg;

public class
signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    EditText editText_name,editText_email,editText_phonenumber,editText_pass,editText_cpass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms
    Spinner spinner_state;
    Button button_go;
    String[] name,st_id,error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

       // spinner_state=findViewById(R.id.spinner);
        editText_cpass=findViewById(R.id.editText_cpassword);
        editText_email=findViewById(R.id.editText_email);
        editText_name=findViewById(R.id.editText_name);
        editText_phonenumber=findViewById(R.id.editText_phonenumer);
        editText_pass=findViewById(R.id.editText_password);
        button_go=findViewById(R.id.button_ok);
        button_go.getBackground().setAlpha(100);
      //  spinner_state.setOnItemSelectedListener(this);
        editText_name.setHint(R.string.uNameChinese);

        getstate();

        editText_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // user typed: start the timer
               /* timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // do your actual work here
                        signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toast();

                            }
                        });

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                        // hide keyboard as well?
                        // InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        // in.hideSoftInputFromWindow(searchText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 1000); // 600ms delay before the timer executes the "run" method from TimerTask*/

                if (editText_cpass.getText().toString().length()>=1 && editText_phonenumber.getText().toString().length()>=1 && editText_name.getText().toString().length()>=1 && editText_pass.getText().toString().length()>=1) {
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
                if (editText_cpass.getText().toString().length()>=1 && editText_phonenumber.getText().toString().length()>=1 && editText_name.getText().toString().length()>=1 && editText_pass.getText().toString().length()>=1) {
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



        editText_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (editText_cpass.getText().toString().length()>=1 && editText_phonenumber.getText().toString().length()>=1 && editText_name.getText().toString().length()>=1 && editText_pass.getText().toString().length()>=1) {
                    button_go.getBackground().setAlpha(250);
                   // Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                  //  Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }



                editText_name.setBackgroundResource(edit_text_normal_bg);
                editText_phonenumber.setBackgroundResource(edit_text_normal_bg);
                editText_cpass.setBackgroundResource(edit_text_normal_bg);
                editText_pass.setBackgroundResource(edit_text_focuses_bg);
                
                
                if ( isValidPassword(editText_pass.getText().toString())==true) {

                 //   Toast.makeText(signup.this, " valid password ", Toast.LENGTH_SHORT).show();
                }

            else {

                    //Toast.makeText(signup.this, "invalid password ", Toast.LENGTH_SHORT).show();
                }  
            }
            
        });

        editText_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (editText_cpass.getText().toString().length()>=1 && editText_phonenumber.getText().toString().length()>=1 && editText_name.getText().toString().length()>=1 && editText_pass.getText().toString().length()>=1) {
                    button_go.getBackground().setAlpha(250);
                   // Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                    //Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }


                editText_pass.setBackgroundResource(edit_text_normal_bg);
                editText_phonenumber.setBackgroundResource(edit_text_normal_bg);
                editText_cpass.setBackgroundResource(edit_text_normal_bg);
                editText_name.setBackgroundResource(edit_text_focuses_bg);


                if ( isValidPassword(editText_pass.getText().toString())==true){

                   // Toast.makeText(signup.this, " valid password ", Toast.LENGTH_SHORT).show();
                }

                else {

                  //  Toast.makeText(signup.this, "invalid password ", Toast.LENGTH_SHORT).show();
                }
            }

        });
        editText_cpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (editText_cpass.getText().toString().length()>=1 && editText_phonenumber.getText().toString().length()>=1 && editText_name.getText().toString().length()>=1 && editText_pass.getText().toString().length()>=1) {
                    button_go.getBackground().setAlpha(250);
                  //  Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                  //  Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }


                editText_name.setBackgroundResource(edit_text_normal_bg);
                editText_phonenumber.setBackgroundResource(edit_text_normal_bg);
                editText_pass.setBackgroundResource(edit_text_normal_bg);
                editText_cpass.setBackgroundResource(edit_text_focuses_bg);


                if ( isValidPassword(editText_pass.getText().toString())==true){

                   // Toast.makeText(signup.this, " valid password ", Toast.LENGTH_SHORT).show();
                }

                else {

                   // Toast.makeText(signup.this, "invalid password ", Toast.LENGTH_SHORT).show();
                }
            }

        });
        editText_phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



                if (editText_cpass.getText().toString().length()>=1 && editText_phonenumber.getText().toString().length()>=1 && editText_name.getText().toString().length()>=1 && editText_pass.getText().toString().length()>=1) {
                    button_go.getBackground().setAlpha(250);
                   // Toast.makeText(signup.this, " name "+"1", Toast.LENGTH_SHORT).show();
                }
                else {

                    button_go.getBackground().setAlpha(50);
                    button_go.setEnabled(true);
                   // Toast.makeText(signup.this, " name "+"0", Toast.LENGTH_SHORT).show();

                }



                editText_name.setBackgroundResource(edit_text_normal_bg);
                editText_cpass.setBackgroundResource(edit_text_normal_bg);
                editText_pass.setBackgroundResource(edit_text_normal_bg);
                editText_phonenumber.setBackgroundResource(edit_text_focuses_bg);


                if ( isValidPassword(editText_pass.getText().toString())==true){

                   // Toast.makeText(signup.this, " valid password ", Toast.LENGTH_SHORT).show();
                }

                else {

                   // Toast.makeText(signup.this, "invalid password ", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


void toast() {
    editText_phonenumber.requestFocus();
}


    public void go(View view) {

        button_go.animate().rotation(button_go.getRotation()+360).start();
       if(editText_name.getText().toString().isEmpty()){


       }
       else {


           if(editText_phonenumber.getText().toString().isEmpty()){


           }
           else {

               if(editText_email.getText().toString().isEmpty()){


               }
               else {

                   if(editText_pass.getText().toString().isEmpty()){


                   }
                   else {

                       if(editText_cpass.getText().toString().isEmpty()){


                       }
                       else {
                           log();




                       }


                   }


               }


           }


       }

//        editText_name.setBackgroundResource(edit_text_normal_bg);
//        editText_phonenumber.setBackgroundResource(edit_text_normal_bg);
//        editText_pass.setBackgroundResource(edit_text_normal_bg);
//        editText_cpass.setBackgroundResource(edit_text_normal_bg);
//
//
//        editText_name.setCursorVisible(false);
//        editText_phonenumber.setCursorVisible(false);
//        editText_pass.setCursorVisible(false);
//        editText_cpass.setCursorVisible(false);
    }




    void log () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""
                +Url.register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText (signup.this, "res: "+response, Toast.LENGTH_LONG ).show ( );

try {
    log_json("["+response+"]");

}catch (Exception e){}


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText (signup.this, "Error: ", Toast.LENGTH_LONG ).show ( );

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", editText_phonenumber.getText().toString());
                params.put("password", editText_pass.getText().toString());
                params.put("email", editText_email.getText().toString());
                params.put("confirm_password", editText_cpass.getText().toString());
                params.put("mobile", editText_phonenumber.getText().toString());

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

    void getstate () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.state, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    getstate_json(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText (signup.this, "url error ", Toast.LENGTH_LONG ).show ( );

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

    private void getstate_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        st_id = new String[jsonArray.length()];
        name = new String[jsonArray.length()];



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            st_id[i] = obj.getString("id");
            name[i] = obj.getString("name");




        }

        AppointmentAdapter adapter = new AppointmentAdapter();
     //   spinner_state.setAdapter(adapter);

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

      //  Toast.makeText(this, "id"+st_id[position], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    class AppointmentAdapter extends BaseAdapter {



        @Override
        public int getCount() {
            return st_id.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            convertView = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.sta, null);
                TextView qst = convertView.findViewById(R.id.textView13);

                qst.setText( name[position]);



                ViewGroup vg=(ViewGroup) convertView;






                        // start_num();



               /* convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewGroup vg=(ViewGroup) view;
                        RatingBar   mrating =vg.findViewById(R.id.ratingBar);
                        mrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            public void onRatingChanged(RatingBar ratingBar, float rating,
                                                        boolean fromUser) {


                            }
                        });





                    // RatingBar   mrating =vg.findViewById(R.id.ratingBar);
                        Toast.makeText(Star_rating.this, "Rat"+mrating.getRating(), Toast.LENGTH_SHORT).show();
                    }
                });*/

            }

            return convertView;
        }

    }


    void log_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];

        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            error[i] = obj.getString("error");

        }
        try {

            if(error[0].equals("0")){
                // Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(signup.this, MainActivity.class);

                signup.this.startActivity(myIntent);


            }
            else {
                //Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText ( this, "vv"+constant.sms_states, Toast.LENGTH_SHORT ).show ( );

        }catch (Exception e){

            //Toast.makeText(this, "Error Code : Msg-201 ", Toast.LENGTH_SHORT).show();
        }


    }
}
