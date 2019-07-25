package com.example.claid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.claid.adapter.CoverFlowAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class ozos_cam extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private String pathToFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozos_cam);

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);


        File photo_file=null;
        photo_file=createphotofile();



        if(photo_file!=null){



            pathToFile=photo_file.getAbsolutePath();
            Uri Pathtouri = (Uri) FileProvider.getUriForFile(ozos_cam.this,"com.example.claid",photo_file);


            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Pathtouri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }
}


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST) {



           // image_path_in_constant(img_no,pathToFile);

        }
    }



    private File createphotofile(){
        String name =new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir= getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try {
            image=File.createTempFile(name,".jpg",storageDir);
           image_path_in_constant(1,""+File.createTempFile(name,".jpg",storageDir));

        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
        return image;

    }

    public void image_path_in_constant(int mimg_no,String path) {
        Log.d("path","path:"+pathToFile);
        if (mimg_no == 1) {
            Constant.pos_1 = 1;
           // Constant.img_path_1= path;

        }
        if (mimg_no == 2) {
            Constant.pos_2 = 1;
           // Constant.img_path_2 = path;

        }
        if (mimg_no == 3) {

            Toast.makeText(this, "img-3", Toast.LENGTH_SHORT).show();
            Constant.pos_3 = 1;
           // Constant.img_path_3 = path;


        }


        if (mimg_no == 4) {
            Constant.pos_4 = 1;
           // Constant.img_path_4= path;

        }
        if (mimg_no == 5) {
            Constant.pos_5 = 1;
          //  Constant.img_path_5 = path;


        }
        if (mimg_no == 6) {

            Toast.makeText(this, "img-3", Toast.LENGTH_SHORT).show();
            Constant.pos_6 = 1;
           // Constant.img_path_6 = path;


        }


        if (mimg_no == 7) {
            Constant.pos_7 = 1;
           // Constant.img_path_7= path;


        }
        if (mimg_no == 8) {
            Constant.pos_8 = 1;
           // Constant.img_path_8 = path;

        }


    }



  /*  void upload (final Bitmap bitmaps) {

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

    }*/


}
