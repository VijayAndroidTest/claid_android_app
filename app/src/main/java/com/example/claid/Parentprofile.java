package com.example.claid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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

public class Parentprofile extends AppCompatActivity {
    String[] age,id,weight,height,username,child_id;
    EditText editText_name,editText_age,editText_height,editText_weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentprofile);

        editText_age=findViewById(R.id.editText_age);
        editText_name=findViewById(R.id.editText_name);
        details();
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


        age = new String[jsonArray.length()];

        username = new String[jsonArray.length()];



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            age[i] = obj.getString("age");
            username[i] = obj.getString("name");

            editText_name.setText(username[i]);
            editText_age.setText(age[i]);

        }






    }
}
