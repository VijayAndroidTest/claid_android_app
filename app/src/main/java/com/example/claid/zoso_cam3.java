package com.example.claid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;
import android.util.SizeF;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.HttpsURLConnection;

public class zoso_cam3 extends AppCompatActivity  implements  SensorEventListener {
    final String uploadFilePath = "";
    final String uploadFileName = "";
    private static final String TAG = "Test Camera";
    //String upLoadServerHttpsUri = "https://www.ozosmatrix.com/resolution/orientation.php";
    String upLoadServerHttpsUri = "https://www.ozosmatrix.com/resolution/upload.php";
    //String upLoadServerHttpsUri = "https://www.ozosmatrix.com/resolution/mobileupload.php";
    private static final String IMAGE_DIRECTORY = "/CustomImage";
    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;

    private Button capture;
    private ImageView imageView;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Context myContext;
    private LinearLayout cameraPreview;
    private boolean cameraFront = true;
    public static Bitmap bitmap;
    public String response_pixel = "";
    public String session="";
    String infoData;
    public int pic_height=0;
    public String res_str[];
    public static final String MyPREFERENCES = "session" ;
    String imageName = "Left Side Bose";
    public Integer count=0;
    public String userid="";
    private Bitmap bitmap1;
    private String output_file_name;
    private int[] imageArray =  {R.drawable.left_side_up, R.drawable.left_side,
            R.drawable.back, R.drawable.back_up,R.drawable.rght_side_up,R.drawable.rght_sidec,
            R.drawable.front_bose, R.drawable.front};
    private String[]bose={"FRONT POSE","BACK POSE","LEFT SIDE POSE","LEFT SIDE UP POSE","CROAUCH POSE","Right SIDE POSE","RIGHT SIDE UP POSE","FRONT POSE UP"};
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_zoso_cam3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        output_file_name = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + Constant.pose_name + ".jpeg";


        imageView=findViewById(R.id.imageView7);
        imageView.setImageResource(imageArray[Constant.pose_no]);




        session=randomString(30);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("sess", session);
        editor.commit();
        TextView edtText = (TextView) findViewById( R.id.textView );

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy( policy );
        }
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        myContext = this;

        mCamera = Camera.open();
        mCamera.setDisplayOrientation( 90 );


///////////////////////////////////////
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        int w = 0, h = 0;
        for (Camera.Size size : sizes) {
            if (size.width > w || size.height > h) {
                w = size.width;
                h = size.height;
            }

        }
        Log.d( "Width", "Width:" + w );
        Log.d( "Height", "Height:" + h );
        parameters.setPictureSize( w, h );
        Log.d( "Max Zoom", "Max Zoom:" + parameters.getMaxZoom() );

//sensor size
        SizeF sf = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sf = getCameraResolution( 0 );
        }

        Log.d( "sensor size", ":" + sf );


        mCamera.setParameters( parameters );
///////////////////////////////////////////


        cameraPreview = (LinearLayout) findViewById( R.id.cPreview );
        mPreview = new CameraPreview( myContext, mCamera );
        cameraPreview.addView( mPreview );

        capture = (Button) findViewById( R.id.btnCam );
        capture.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  capture.getBackground().setAlpha(0);
                /*ProgressBar progressBar;
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);*/
                Toast.makeText(myContext, "Please Wait Photo Uploading....", Toast.LENGTH_SHORT).show();

                TextView tv = (TextView) findViewById( R.id.textView );



                if(count.equals(0)){
                    tv.setText( "Left Side Bose uploading..." );
                }else if(count.equals(1)){
                    tv.setText( "Front Bose uploading..." );
                }else if(count.equals(2)){
                    tv.setText( "Back Side Bose uploading..." );
                }else if(count.equals(3)){
                    tv.setText( "Croauch Bose uploading..." );
                }else if(count.equals(4)){
                    tv.setText( "Ride Side Bose uploading..." );
                }


                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

                    releaseCamera();
                    chooseCamera();
                } else {

                }


                mCamera.takePicture( null, null, mPicture );

                //dispatchTakePictureIntent();
            }
        } );


        mCamera.startPreview();


    }


    public static final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static Random RANDOM = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }

        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private SizeF getCameraResolution(int camNum) {
        SizeF size = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            size = new SizeF( 0, 0 );
        }
        CameraManager manager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager = (CameraManager) getSystemService( Context.CAMERA_SERVICE );
        }
        try {
            String[] cameraIds = new String[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraIds = manager.getCameraIdList();
            }
            if (cameraIds.length > camNum) {
                CameraCharacteristics character = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    character = manager.getCameraCharacteristics( cameraIds[camNum] );
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    size = character.get( CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE );
                }
            }
        } catch (CameraAccessException e) {
            Log.e( "YourLogString", e.getMessage(), e );
        }
        return size;
    }


    private int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo( i, info );
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;

    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo( i, info );
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }

    public void onResume() {

        super.onResume();
        if (mCamera == null) {
            mCamera = Camera.open();
            /////////////////////////////////////////////////////
            Camera.Parameters parameters = mCamera.getParameters();
            List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
            int w = 0, h = 0;
            for (Camera.Size size : sizes) {
                if (size.width > w || size.height > h) {
                    w = size.width;
                    h = size.height;
                }

            }

            Log.d( "Width", "Width:" + w );

            Log.d( "Height", "Height:" + h );
            parameters.setPictureSize( w, h );
            // int customZoom=-90;
            // parameters.setZoom( parameters.getMaxZoom() );


            Log.d( "Max Zoom", "Max Zoom:" + parameters.getMaxZoom() );
            mCamera.setParameters( parameters );
            ///////////////////////////////////////////
            mCamera.setDisplayOrientation( 90 );
            mPicture = getPictureCallback();
            Log.d( "mPicture", "mPicture:" + mPicture );
            mPreview.refreshCamera( mCamera );
            Log.d( "nu", "null" );
        } else {
            Log.d( "nu", "no null" );
        }

    }


    public void chooseCamera() {
        //if the camera preview is the front

        Log.d( "Camera Front", "Camera Front:" + cameraFront );

        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open( cameraId );
                ////////////////////////////////////////////
                Camera.Parameters parameters = mCamera.getParameters();
                List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
                int w = 0, h = 0;
                for (Camera.Size size : sizes) {
                    if (size.width > w || size.height > h) {
                        w = size.width;
                        h = size.height;
                    }

                }

                Log.d( "Width", "Width:" + w );

                Log.d( "Height", "Height:" + h );
                parameters.setPictureSize( w, h );
                // int customZoom=-90;
                //parameters.setZoom( parameters.getMaxZoom() );

                //Set Rotation
               /* if(Build.MODEL.compareTo("Moto G (5S)")!=0 && Build.MODEL.compareTo("Nokia 7.1")!=0 && Build.MODEL.compareTo("ASUS_X00TD")!=0)
                {
                    parameters.setRotation( 90 );
                }
                else
                {
                    parameters.setRotation(180 );
                }
                */
                //parameters.setJpegQuality( 90 );
                //Set Rotation
                Log.d( "Max Zoom", "Max Zoom:" + parameters.getMaxZoom() );
                mCamera.setParameters( parameters );
                /////////////////////////////////////////////
                mCamera.setDisplayOrientation( 90 );
                mPicture = getPictureCallback();
                Log.d( "mPicture", "mPicture:" + mPicture );
                mPreview.refreshCamera( mCamera );
            }
        } else {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open( cameraId );
                ///////////////////////////////////////////////
                Camera.Parameters parameters = mCamera.getParameters();
                List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
                int w = 0, h = 0;
                for (Camera.Size size : sizes) {
                    if (size.width > w || size.height > h) {
                        w = size.width;
                        h = size.height;
                    }

                }

                Log.d( "Width", "Width:" + w );

                Log.d( "Height", "Height:" + h );
                parameters.setPictureSize( w, h );
                // int customZoom=-90;
                //parameters.setZoom( parameters.getMaxZoom() );

                //Set Rotation
               /* if(Build.MODEL.compareTo("Moto G (5S)")!=0 && Build.MODEL.compareTo("Nokia 7.1")!=0 && Build.MODEL.compareTo("ASUS_X00TD")!=0)
                {
                    parameters.setRotation( 90 );
                }
                else
                {
                    parameters.setRotation(180);
                }*/
                //parameters.setJpegQuality( 90 );
                //Set Rotation
                Log.d( "Max Zoom", "Max Zoom:" + parameters.getMaxZoom() );
                mCamera.setParameters( parameters );
                /////////////////////////////////////////////////
                mCamera.setDisplayOrientation( 90 );
                mPicture = getPictureCallback();

                Log.d( "mPicture", "mPicture:" + mPicture );
                mPreview.refreshCamera( mCamera );
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //when on Pause, release camera in order to be used from other applications
        releaseCamera();
    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback( null );
            mCamera.release();
            mCamera = null;
        }
    }

   private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                String timeStamp = new SimpleDateFormat( "yyyyMMdd_HHmmss").format( new Date( ));
               //output_file_name = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + Constant.pose_name + ".jpeg";

                File pictureFile = new File(output_file_name);
                if (

                        pictureFile.exists()) {
                   pictureFile.delete();
                }

                try {


                    Bitmap realImage = BitmapFactory.decodeByteArray(data, 0, data.length);


                    File wallpaperDirectory = new File(
                            Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY );
                   // ExifInterface exif=new ExifInterface(pictureFile.toString());

                   // Log.d("EXIF value", exif.getAttribute(ExifInterface.TAG_ORIENTATION));
                   /* if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")){
                        realImage= rotate(realImage, 90);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")){
                        realImage= rotate(realImage, 270);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")){
                        realImage= rotate(realImage, 180);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("0")){
                        realImage= rotate(realImage, 90);
                    }*/

                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    realImage.compress(Bitmap.CompressFormat.JPEG, 25, bytes);
                    FileOutputStream fo = new FileOutputStream(pictureFile);
                    fo.write( bytes.toByteArray() );
                    fo.close();

                   // up_image(output_file_name,Constant.pose_name,Constant.lgg_api,Constant.child_id);
                   uploadFileHttpsPost(output_file_name,Constant.pose_name,Constant.lgg_api,Constant.child_id);
                    //  ((ImageView) findViewById(R.id.imageview)).setImageBitmap(realImage);




                } catch (FileNotFoundException e) {
                    Log.d("Info", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("TAG", "Error accessing file: " + e.getMessage());
                }
            }
        };
        return picture;//picture;
            }

            public  Bitmap rotate(Bitmap bitmap, int degree) {
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                Matrix mtx = new Matrix();
                //       mtx.postRotate(degree);
                mtx.setRotate(degree);

                return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);


            }




    public String uploadFileHttpsPost(String sourceFileUri,String imageName,String token,String userid) {

        Log.d( TAG, "sourceFileUri: "+sourceFileUri );
        Log.d( TAG, "imageName: "+imageName );
        Log.d( TAG, "token: "+token );
        Log.d( TAG, "userid: "+userid );
        //upLoadServerHttpsUri="https://ozosmatrix.com/claid_revamp/api/upload";
        // upLoadServerHttpsUri="https://ozosmatrix.com/claid_revamp/api/uploadtest";
        //upLoadServerHttpsUri="https://www.ozosmatrix.com/resolution/upload.php";
        //upLoadServerHttpsUri = "https://www.ozosmatrix.com/resolution/orientation.php";
        //upLoadServerHttpsUri = "https://www.ozosmatrix.com/resolution/mobileupload.php";
        //upLoadServerHttpsUri = "https://ozosmatrix.com/claid_revamp/api/upload";
        upLoadServerHttpsUri="https://ozosmatrix.com/claid_revamp/v2/api/upload";
        Boolean result;

        // Get Preference value
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String sesssionvalue=sharedpreferences.getString("sess",null);
        HttpsURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File not exist :"
                    +uploadFilePath + "" + uploadFileName);
            return "";
        }
        else
        {
            int serverResponseCode = 0;
            try {
                String boundaryString = "----SomeRandomText";
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerHttpsUri);


                String fileUrl = sourceFileUri;
                Log.d( TAG, "Source URL: "+sourceFileUri );
                File logFileToUpload = new File(fileUrl);

                Log.d( TAG, "API URL: "+url );
                // Open a HTTP  connection to  the URL
                conn = (HttpsURLConnection) url.openConnection();

                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
                conn.setRequestProperty("authorization", "Bearer " + token);
                OutputStream outputStreamToRequestBody = conn.getOutputStream();
                BufferedWriter httpRequestBodyWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));

// Include value from the Image text  in the post data
                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"imagename\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(imageName);


// Include value from the Image text  in the post data
                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"information\"");
                httpRequestBodyWriter.write("\n\n");
                Camera.Parameters parameters = mCamera.getParameters();

                SizeF sf= null;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    sf = getCameraResolution(0);
                }


                String[] separated =sf.toString().split("x");

                String width_dim =separated[0]; // this will contain "Fruit"
                String height_dim=separated[1];
                // userid="100";
                //sesssionvalue="100";
                String infoData=parameters.getFocalLength()+"#"+parameters.getHorizontalViewAngle()+"#"+parameters.getVerticalViewAngle()+"#"+width_dim+"#"+height_dim+"#"+Build.MODEL+"#"+""+Constant.pose_no+"#"+Constant.child_id+"#"+Constant.pose_height+"#"+Constant.child_id;
                httpRequestBodyWriter.write(infoData);

                Log.i("info", "Data : "
                        + infoData);



// Include the section to describe the file
                httpRequestBodyWriter.write("\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data;"
                        + "name=\"uploaded_file\";"
                        + "filename=\""+ logFileToUpload.getName() +"\""
                        + "\nContent-Type: text/plain\n\n");
                httpRequestBodyWriter.flush();

// Write the actual file contents
                FileInputStream inputStreamToLogFile = new FileInputStream(sourceFileUri);


                byte[] dataBuffer = new byte[1024];
                while((bytesRead = inputStreamToLogFile.read(dataBuffer)) != -1) {
                    outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
                }

                outputStreamToRequestBody.flush();

// Mark the end of the multipart http request
                httpRequestBodyWriter.write("\n--" + boundaryString + "--\n");
                httpRequestBodyWriter.flush();

// Close the streams
                outputStreamToRequestBody.close();
                httpRequestBodyWriter.close();
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    // dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart form data necesssary after file data...
                // dos.writeBytes(lineEnd);
                // dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){
                    //showToast("File Upload Complete.");
                    Log.i("Response", "Response : "
                            + conn);



                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    Log.d( TAG, "Response Length: " +response.toString().length());
                    Log.d( TAG, "Response Result: " +response.toString());




                    response_pixel=response.toString();

                    JSONObject myResponse = new JSONObject(response.toString());

                    System.out.println("result after Reading JSON Response");
                    System.out.println("Error- "+myResponse.getString("error"));
                    System.out.println("Message- "+myResponse.getString("message"));
                    System.out.println("Result- "+myResponse.getString("result"));

                    res_str=myResponse.getString("result").split("#");
                    showresultView(myResponse.getString("result"));
                    pic_height=Integer.valueOf( res_str[1] );
                    Constant.pose_height = pic_height;


 /*

                    res_str=response_pixel.split("#");
                    showresultView(response_pixel);
                    pic_height=Integer.valueOf( res_str[1] );
*/
                    String cmp_res=res_str[0].trim();
                    Log.d( TAG, "pic_height: " +Integer.valueOf( res_str[1] ));
                    Log.d( TAG, "cmp_res: " +cmp_res);

                    File fdelete = new File(sourceFileUri);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            System.out.println("file Deleted :" + sourceFileUri);
                        } else {
                            System.out.println("file not Deleted :" + sourceFileUri);
                        }
                    }






                   /* if(cmp_res.equals("1")){
                        ShowImageNameView("Neck Front Pose")  ;
                        showAlert("Neck Front Pose uploading done" );
                    }else if(cmp_res.equals("2")){
                        ShowImageNameView("Left Side Pose")  ;
                        showAlert("Left Side Pose uploading done" );
                    }else if(cmp_res.equals("3")){
                        ShowImageNameView("Neck Left Side Pose")  ;
                        showAlert("Neck Left Side Pose uploading done" );
                    }else if(cmp_res.equals("4")){
                        ShowImageNameView("Back Side Pose")  ;
                        showAlert("Back Side Pose uploading done" );
                    }else if(cmp_res.equals("5")){
                        ShowImageNameView("Croatch Pose")  ;
                        showAlert("Croatch Pose uploading done" );
                    }else if(cmp_res.equals("6")){
                        ShowImageNameView("Right Side Pose ")  ;
                        showAlert("Right Side Pose uploading done" );
                    }else if(cmp_res.equals("7")){
                        ShowImageNameView("Neck Right Side Pose")  ;
                        showAlert("Neck Right Side Pose uploading done" );
                    }else if(cmp_res.equals("8")){
                        showAlert("All Pose uploading done" );
                        ShowImageNameView("All are Done");

                    }else{
                        showAlert("All Pose uploading done" );
                        ShowImageNameView("All are Done");

                    }*/

Constant.vid_cam=1;
                    Intent myIntent = new Intent(zoso_cam3.this, photography_pages.class);

                    zoso_cam3.this.startActivity(myIntent);


                    result=true;
                    /*ProgressBar progressBar;
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.GONE);*/
                }else{
                    //_loginButton.setEnabled(true);

                    result=false;
                }
                //close the streams //
                fileInputStream.close();
                //dos.flush();
                // dos.close();

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                //showToast("MalformedURLException.");
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);

                result=false;
            } catch (Exception e) {
                e.printStackTrace();

                result=false;
            }
            return "1";

        } // End else block
    }









    private void showAlert(final String text) {
        Log.e("showAlert", "showAlert: " + text);
        //final Dialog dialog = new Dialog(context);
        // dialog.setContentView(R.layout.activity_main);
        // dialog.dismiss();
        //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
        //dialog.show();
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(text);

    }

    private void showresultView(final String text) {
        Log.e("showresultView", "showresultView: " + text);
        TextView tv2 = (TextView) findViewById(R.id.ResultView);
        tv2.setText(text);
    }


    private void ShowImageNameView(final String text) {
        Log.e("ShowImageNameView", "ShowImageNameView: " + text);
        //final Dialog dialog = new Dialog(context);
        // dialog.setContentView(R.layout.activity_main);
        // dialog.dismiss();
        //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
        //dialog.show();
        TextView tv1 = (TextView) findViewById(R.id.ImageNameView);
        tv1.setText(text);
        imageName=text;

    }







    public Camera.PictureCallback getActivity() {
        return (Camera.PictureCallback) this;}

    @SuppressLint("ResourceAsColor")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() != Sensor.TYPE_ACCELEROMETER){
            return;
        }

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        double angle = (Math.atan2(y, Math.sqrt(x*x+z*z))/ (Math.PI / 180));
        // av_ang.add(String.valueOf(roundTwoDecimals(angle)));
        // Toast.makeText(this, "sen x"+x, Toast.LENGTH_SHORT).show();
        //ok((int) roundTwoDecimals(x));
        capture.setText(""+roundTwoDecimals(angle));


        if(roundTwoDecimals(angle) >=88){
capture.setEnabled(true);
            capture.getBackground().setAlpha(250);
            // capture.setText(""+roundTwoDecimals(angle));
            capture.setText("OK");
        }
        else if(z<=0) {
            capture.getBackground().setAlpha(75);
            capture.setText("-"+roundTwoDecimals(angle));
          //  capture.setEnabled(false);
           // capture.setTextColor(R.color.colorwhite);
         //   Toast.makeText(this, "sen x"+x +"  z"+z, Toast.LENGTH_SHORT).show();

        }
        else if(z>=0){
            capture.getBackground().setAlpha(100);
            capture.setText(""+roundTwoDecimals(angle));
          //  capture.setEnabled(false);
            // capture.setTextColor(R.color.colorwhite);
           // Toast.makeText(this, "sen x"+x + "  z"+z, Toast.LENGTH_SHORT).show();


        }

    }

    double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("##");

        return Double.valueOf(twoDForm.format(d));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    void up_image(String sourceFileUri, final String imageName, String token, String userid) throws FileNotFoundException {

        InputStream inputStream = (InputStream) getContentResolver().openInputStream(Uri.parse(sourceFileUri));
       final Bitmap bitmap1=BitmapFactory.decodeStream(inputStream);

        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, "https://ozosmatrix.com/claid_revamp/v2/api/upload", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(zoso_cam3.this, "res   :   "+response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("uploaded_file", image_to_string(bitmap1));
                params.put("imagename", imageName);
                params.put("information", "3.57#66.0365#51.9687#4.656#3.504#Nokia 6.1 Plus#0#320#0#320");
                // params.put("name", constant.complient_id);
                //  params.put("c_id", constant.complient_id);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", Constant.lgg_api);
                return headers;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(zoso_cam3.this);
        requestQueue.add(stringRequest);
        //Mysingleton.getmInstance(complaind_act.this).addtoRequestQue(request);
        // Volley.newRequestQueue(this).add(request);

    }

    private String image_to_string( Bitmap bitmap1){

        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }

}