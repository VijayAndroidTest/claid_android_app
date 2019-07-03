package com.example.claid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.Map;

public class measurement extends AppCompatActivity {
    ListView listView;
    private int json_val;
    private String[] mname,ename,mvalues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              //  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_measurement);
        listView=findViewById(R.id.listview);
        details();



    }


    void details () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.getmeasurements, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 // Toast.makeText (measurement.this, "measurement: "+response, Toast.LENGTH_LONG ).show ( );

                try {
                    details_json(response);
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

                params.put("order_id","8" );

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    private void details_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        mname = new String[jsonArray.length()];
        ename= new String[jsonArray.length()];
        mvalues= new String[jsonArray.length()];




        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            mname[i] = obj.getString("mname");
            ename[i] = obj.getString("ename");
            mvalues[i] = obj.getString("mvalues");
        }





    }






    public  void chat(View view){

        Intent myIntent = new Intent(measurement.this, Messages.class);

        measurement.this.startActivity(myIntent);
    }

    public void three_d(View view){
        Intent myIntent = new Intent(measurement.this, three_d_body.class);

        measurement.this.startActivity(myIntent);


    }


    class AppointmentAdapter2 extends BaseAdapter


    {




        @Override
        public int getCount() {

            return json_val;
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
               // convertView = getLayoutInflater().inflate(R.layout.image_layout, null);
               // ImageView img = convertView.findViewById(R.id.imageView);


                //Picasso.with ( measurement.this ).load ( mname[position] ).into ( img);





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
}
