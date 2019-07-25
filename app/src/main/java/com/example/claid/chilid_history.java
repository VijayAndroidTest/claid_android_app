package com.example.claid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class chilid_history extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private String[] name,age,sex,height,weight,fronturl,sideurl,id,date;
    private int json_val;
    SwipeMenuListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        setContentView(R.layout.activity_chilid_history);
        listView=findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        details();
    }

    void details () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.get_child_profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               //  Toast.makeText (chilid_history.this, ""+response, Toast.LENGTH_LONG ).show ( );

                try {
                    details_json( response);
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

        JSONObject jsonObj = new JSONObject(json);
        JSONArray jsonArray = jsonObj.getJSONArray("userdata");

       // Toast.makeText(this, "img"+jsonArray, Toast.LENGTH_SHORT).show();
        name=new String[jsonArray.length()];
        age=new String[jsonArray.length()];
        sex=new String[jsonArray.length()];
        height=new String[jsonArray.length()];
        weight=new String[jsonArray.length()];
        fronturl=new String[jsonArray.length()];
        sideurl=new String[jsonArray.length()];
        id=new String[jsonArray.length()];
        date=new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++)
        {

            JSONObject obj = jsonArray.getJSONObject(i);
            name[i] = obj.getString("name");
            age[i] = obj.getString("age");
            height[i] = obj.getString("height");
            weight[i] = obj.getString("weight");
            fronturl[i]=obj.getString("fronturl");
            sideurl[i]=obj.getString("sideurl");
            id[i]=obj.getString("userid");
            date[i]=obj.getString("date");

            // Toast.makeText(this, "vv", Toast.LENGTH_SHORT).show();

        }
        json_val=jsonArray.length();

        AppointmentAdapter2 adapter2=new AppointmentAdapter2();
        listView.setAdapter(adapter2);



        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
            /*    SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);*/

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background

                // set item width
                deleteItem.setWidth(170);

                // set a icon
                deleteItem.setIcon(R.drawable.binn);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };


        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                       String sid=id[position];
                       delete_child(sid);

                       // Toast.makeText(chilid_history.this, "id"+sid, Toast.LENGTH_SHORT).show();
                       // Log.d(TAG, "onMenuItemClick: clicked item " + index+"p"+position);
                        break;
                    case 1:
                       // Toast.makeText(chilid_history.this, ""+index+"p"+position, Toast.LENGTH_SHORT).show();
                       // Log.d(TAG, "onMenuItemClick: clicked item " + index+"p"+position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent myIntent = new Intent(chilid_history.this, measurement.class);
        chilid_history.this.startActivity(myIntent);
        Constant.mchild_id=id[i];
        //Toast.makeText(this, "id"+id[i], Toast.LENGTH_SHORT).show();

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
               // Toast.makeText(chilid_history.this, "ss"+name[position]+"  id  "+id[position], Toast.LENGTH_SHORT).show();

                convertView = getLayoutInflater().inflate(R.layout.history_temp, null);
                TextView mname=convertView.findViewById(R.id.textView_name);
                TextView mage=convertView.findViewById(R.id.textView_age);
                TextView mdate=convertView.findViewById(R.id.textView_date);
                TextView mhight=convertView.findViewById(R.id.textView_weight);
                ImageView img = convertView.findViewById(R.id.imageView_front);
                ImageView img2 = convertView.findViewById(R.id.imageView_side);
                mname.setText(name[position]+"("+age[position]+")");
                mage.setText(weight[position]+"-Kg");
                mdate.setText(date[position]);
                mhight.setText(height[position]+"-Cm");
                Picasso.with ( chilid_history.this ).load ( fronturl[position] ).into ( img);
                Picasso.with ( chilid_history.this ).load ( sideurl[position] ).into ( img2);





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
    public  void back(View view){

        Intent myIntent = new Intent(chilid_history.this, Profile.class);
        chilid_history.this.startActivity(myIntent);
    }




    void delete_child (final String cid) {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""+Url.delete_child, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                details();
                 //Toast.makeText (chilid_history.this, ""+response, Toast.LENGTH_LONG ).show ( );

                try {
                    details_json( response);
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

                params.put("child_id",cid );

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

}
