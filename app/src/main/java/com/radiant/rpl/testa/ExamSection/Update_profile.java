package com.radiant.rpl.testa.ExamSection;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.radiant.rpl.testa.MainActivity;
import com.radiant.rpl.testa.MyNetwork;
import com.radiant.rpl.testa.NetworkStateReceiver;
import com.radiant.rpl.testa.Reverify;
import com.radiant.rpl.testa.Welcome_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class Update_profile extends AppCompatActivity{

    Spinner category,myspinner;
    EditText input_name,input_last_name,input_mobile_no,input_aadhar,input_bank_ac,input_ifsc_code,input_bank_username,input_pann;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    Button input_submit;
    CheckBox checkBox;
    ImageView input_aadharpic;
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_AADHAR_REQUEST = 1889;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    AwesomeValidation awesomeValidation;
    String gender,encodedphotoaadhar,categoryy;
    NetworkStateReceiver networkStateReceiver;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private android.app.AlertDialog progressDialog;
    String first_name,last_name,gender1,categoryselected,bankaccount,aadharselected,name_in_bank,ifsc_selected,mobile_no_selected,User_namee,stateiddd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        myspinner = findViewById(R.id.input_layout_gender_update);
        category=findViewById(R.id.input_layout_category_update);
        input_aadharpic=findViewById(R.id.input_photograph_aadhar_update);
        input_submit=findViewById(R.id.btn_signup_update);
        input_name=findViewById(R.id.input_name_update);
        input_last_name=findViewById(R.id.input_last_name_update);
        input_mobile_no=findViewById(R.id.input_mobile_no_update);
        input_aadhar=findViewById(R.id.input_aadhar_update);
        input_bank_ac=findViewById(R.id.input_bank_ac_update);
        input_ifsc_code=findViewById(R.id.input_ifsc_code_update);
        input_bank_username = findViewById(R.id.input_bank_username_update);
        input_pann=findViewById(R.id.input_pan_update);
        progressDialog = new SpotsDialog(Update_profile.this, R.style.Custom);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        checkBox = findViewById(R.id.checkBox_update);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedpreferences.contains("userid")) {
            User_namee=sharedpreferences.getString("userid", "");
            System.out.println("");
        }

        awesomeValidation.addValidation(Update_profile.this, R.id.input_name_update,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_last_name_update,"[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_bank_ac_update,"^[0-9]{6,18}$", R.string.err_msg_for_acno);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_ifsc_code_update,"^[a-zA-Z0-9]{5,14}$", R.string.err_msg_for_ifsc);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_mobile_no_update,"^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_bank_username_update,"[a-zA-Z\\s]+", R.string.err_msg_for_namein_bank);
        //awesomeValidation.addValidation(Update_profile.this, R.id.input_aadhar_update,"^[0-9]{12}$", R.string.err_msg_for_namein_bank);
        mySwipeRefreshLayout=new SwipeRefreshLayout(getApplicationContext());





        input_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (gender.equals("Select Gender")){
                    Toast.makeText(getApplicationContext(),"Gender must be selected",Toast.LENGTH_LONG).show();
                }
                else if (categoryy.equals("Select Category")){
                    Toast.makeText(getApplicationContext(),"category must be selected",Toast.LENGTH_LONG).show();
                }
                else if ((stateiddd1.equals("2") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("3") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("16") && (input_pann.getText().toString().matches("")))  ||
                        (stateiddd1.equals("17") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("18") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("23") && (input_pann.getText().toString().matches("")))||
                        (stateiddd1.equals("19") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("26") && (input_pann.getText().toString().matches("")))||
                        (stateiddd1.equals("10") && (input_pann.getText().toString().matches(""))) )

                {

                    Toast.makeText(Update_profile.this, "PAN Card cannot be empty ", Toast.LENGTH_SHORT).show();
                }

                else if ((stateiddd1.equals("1") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("4") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("5") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("6") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("7") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("8") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("9") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("11") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("12") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("13") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("14") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("15") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("19") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("20") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("21") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("22") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("24") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("25") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("27") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("28") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("30") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("31") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("32") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("33") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("34") && (input_aadhar.getText().toString().matches(""))))





                {

                    Toast.makeText(Update_profile.this, "PAN Card  be empty  but not aadhar", Toast.LENGTH_SHORT).show();
                }
                else if (awesomeValidation.validate() && !(gender.equals("Select Gender"))&& !category.equals("Select Category")&& checkBox.isChecked()){
                    Update_Student_detail();
                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please check the declaration form.Please verify the form again and submit.", Toast.LENGTH_LONG).show();
                }



            }
        });

      try{
          input_aadharpic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                }

            }
            }
        });
      }catch (Exception e){
          e.printStackTrace();
      }




        //Gender

   ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        myspinner.setAdapter(myAdapter);

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                gender=myspinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Choose category
        ArrayAdapter<String> categoryadapt = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Category));
        categoryadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(categoryadapt);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                categoryy = category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        getStudentProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        networkStateReceiver= new NetworkStateReceiver(new NetworkStateReceiver.NetworkListener() {
            @Override
            public void onNetworkAvailable() {
                input_submit.setEnabled(true);
            }

            @Override
            public void onNetworkUnavailable() {
                input_submit.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Internet Not available",Toast.LENGTH_LONG).show();
            }
        });
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //Update_profile
    private void getStudentProfile() {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_student_details.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                      System.out.println("sdf"+response);
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("student_detail");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            first_name = c.getString("firstname");
                            last_name = c.getString("lastname");
                            gender1 = c.getString("gender");
                            categoryselected = c.getString("category");
                            bankaccount = c.getString("bankac");
                            aadharselected = c.getString("aadhar");
                            name_in_bank = c.getString("NameAsOnBank");
                            ifsc_selected = c.getString("ifsc");
                            mobile_no_selected=c.getString("mobile");
                            stateiddd1=c.getString("state_id");
                        }
                        input_name.setText(first_name);
                        input_last_name.setText(last_name);
                        //category.setPrompt(categoryselected);
                        input_ifsc_code.setText(ifsc_selected);
                        input_bank_ac.setText(bankaccount);
                        input_aadhar.setText(aadharselected);
                        input_bank_username.setText(name_in_bank);
                        input_mobile_no.setText(mobile_no_selected);
                       // myspinner.setPrompt(gender1);
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Student Details",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Failed to fetch Job Roles", Toast.LENGTH_LONG).show();
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("user_name",User_namee);
                System.out.println("aaaaaa"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                // cameraIntent.setPackage(defaultCameraPackage);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } else {
                    Toast.makeText(getApplicationContext(),"Click photos using default Camera Of the Device",Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                if(data.getExtras()==null || (data.getExtras().get("data")==null ||  !(data.getExtras().get("data") instanceof Bitmap))){
                    //todo - show error
                    Toast.makeText(getApplicationContext(),"The file picked is invalid.Please use default camera to click Photos",Toast.LENGTH_LONG).show();
                    return;
                }
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                int currentBitmapWidth = photo.getWidth();
                int currentBitmapHeight = photo.getHeight();
                //input_photograph.setImageBitmap(photo);
                int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                Bitmap newbitMap = Bitmap.createScaledBitmap(photo, currentBitmapWidth, newHeight, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                newbitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                //encodedphoto = Base64.encodeToString(byteArray, Base64.DEFAULT);

            }

            if (requestCode == CAMERA_AADHAR_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                int currentBitmapWidth = photo.getWidth();
                int currentBitmapHeight = photo.getHeight();
                mySwipeRefreshLayout.setRefreshing(false);
                input_aadharpic.setImageBitmap(photo);
                int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                Bitmap newbitMap = Bitmap.createScaledBitmap(photo, currentBitmapWidth, newHeight, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                newbitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encodedphotoaadhar = Base64.encodeToString(byteArray, Base64.DEFAULT);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void Update_Student_detail() {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/update_student_data.php";
        System.out.println("sdff"+serverURL);

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("sdff"+response);
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        Intent ii=new Intent(Update_profile.this, Welcome_page.class);
                        startActivity(ii);
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Failed to Update the profile.Please Try Again.",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Failed to fetch Job Roles", Toast.LENGTH_LONG).show();
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("user_name",User_namee);
                map.put("firstname", input_name.getText().toString());
                map.put("lastname", input_last_name.getText().toString());
                map.put("gender", gender);
                map.put("category", categoryy);
                map.put("bankac", input_bank_ac.getText().toString());
                map.put("aadhar", input_aadhar.getText().toString());
                map.put("pan", input_pann.getText().toString());
                map.put("NameAsOnBank", input_bank_username.getText().toString());
                map.put("ifsc", input_ifsc_code.getText().toString());
                map.put("mobile", input_mobile_no.getText().toString());
                if (encodedphotoaadhar!=null){
                    map.put("aadhar_image",encodedphotoaadhar);}
                System.out.println("aaaaaa"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }
}
