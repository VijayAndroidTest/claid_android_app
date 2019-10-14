package com.example.claid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
    LinearLayout linearLayout_ll3d;
    private int json_val;
    private String[] name,ename,mvalues,url_path;
    Button button_3d,button_measurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_measurement);

        listView=findViewById(R.id.listview);
        button_3d=findViewById(R.id.button4);
        button_measurement=findViewById(R.id.button6);
        linearLayout_ll3d=findViewById(R.id.ll3d);
        details();
        button_3d.setBackgroundColor(getResources().getColor(R.color.button_shado));
        button_measurement.getBackground().setAlpha(250);
        button_3d.getBackground().setAlpha(150);
        button_3d.setTextColor(getResources().getColor(R.color.button_shado));



    }


    void details () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST,
                ""+Url.getmeasurements, new Response.Listener<String>() {
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

/*  Confirm ID check here */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                  params.put("cid",Constant.child_id);
             //   params.put("oid",Constant.order_id);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }


    private void details_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        name = new String[jsonArray.length()];
        ename= new String[jsonArray.length()];
        mvalues= new String[jsonArray.length()];
        url_path= new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            name[i] = obj.getString("name");

            mvalues[i] = obj.getString("mvalues");
            url_path[i]=obj.getString("url_path");
        }
        json_val=jsonArray.length();
        AppointmentAdapter2 adapter2=new AppointmentAdapter2();
        listView.setAdapter(adapter2);


    }



    public  void back(View view){



        Intent myIntent = new Intent(measurement.this, confirmation_screen.class);

        measurement.this.startActivity(myIntent);
    }


    public  void chat(View view){



        Intent myIntent = new Intent(measurement.this, Messages.class);

        measurement.this.startActivity(myIntent);
    }
    public  void confirm(View view){

        Intent myIntent = new Intent(measurement.this, MainActivity.class);
        measurement.this.startActivity(myIntent);
    }

    public void three_d(View view){
        button_3d.setBackgroundColor(getResources().getColor(R.color.button_no_shado));
        button_measurement.setBackgroundColor(getResources().getColor(R.color.button_no_shado));
        button_3d.getBackground().setAlpha(250);
        button_measurement.getBackground().setAlpha(100);
        button_measurement.setTextColor(getResources().getColor(R.color.hintcolor));
        button_3d.setTextColor(getResources().getColor(R.color.colorwhite));
        Intent myIntent = new Intent(measurement.this, three_d_body.class);
        measurement.this.startActivity(myIntent);


    }
    public void measure(View view){
        button_3d.setBackgroundColor(getResources().getColor(R.color.button_no_shado));
        button_measurement.setBackgroundColor(getResources().getColor(R.color.button_no_shado));
        button_measurement.getBackground().setAlpha(250);
        button_3d.getBackground().setAlpha(100);
        button_measurement.setTextColor(getResources().getColor(R.color.colorwhite));
        button_3d.setTextColor(getResources().getColor(R.color.hintcolor));
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
                convertView = getLayoutInflater().inflate(R.layout.measurements_temp, null);
                TextView part_body=convertView.findViewById(R.id.part_body);
                TextView measure=convertView.findViewById(R.id.measurents);
                ImageView img = convertView.findViewById(R.id.imageView5);
                part_body.setText(name[position]);
                measure.setText(mvalues[position]);
               Picasso.with ( measurement.this ).load ( url_path[position] ).into ( img);


            }

            return convertView;
        }





    }
}
