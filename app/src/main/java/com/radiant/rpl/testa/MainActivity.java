package com.radiant.rpl.testa;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity{

    TextView course_detail;

    Spinner yearofbirth,monthofbirth,dateofbirth,education,employment,employer,sector,bankname,state,district,input_jobrole,disablity_type,type_of_disablity,
            Employment_status,OtherIdproof,
            input_layout_prefferedlanguage,category;
    EditText input_name,input_last_name,input_mobile_no,input_address1,input_Id_no
            ,input_address2,input_pincode,input_aadhar,input_pancard,input_bank_ac,input_ifsc_code,input_bank_username,input_empid,input_loc,Email;
    ProgressDialog pd;
    String[] banks,states,districts,employers,jobrole;
    List<String> bankslist,Statelist,districtlist,sectorlist,employerlist,jobrolelist,preflang;
    HashMap<String, String> bankdetail = new HashMap<>();
    HashMap<String, String> Jobrolelist = new HashMap<>();
    HashMap<String,String> Statedetail=new HashMap<>();
    HashMap<String,String> districtdetail=new HashMap<>();
    HashMap<String,String> sectordetail=new HashMap<>();
    HashMap<String,String> employerdetail =new HashMap<>();
    HashMap<String,String> employdetail =new HashMap<>();
    HashMap<String,String> langdetail =new HashMap<>();

    CheckBox checkBox;
    String[] sectors=new String[]{"Select the Sector"};
    String[] preflangg=new String[]{"Select the Preffered Language"};
    CircleImageView input_photograph,input_aadharpic;
    Button input_submit,input_photograph1,input_aadharpic1;
    String Stateid,Statevalue,bankid,bankvalue,districtid,districtvalue,selectedstatetext,sectorid,sectorvalue,
            employerid,employervalue,jobroleid,jobrolevalue,preflangid,preflangvalue;
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_AADHAR_REQUEST = 1889;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String yearobirth,monthobirth,dateobirth;
    AwesomeValidation awesomeValidation;
    String gender,eduction1,employer1,sector1,bankname1,state1,district1,encodedphoto,encodedphotoaadhar,jobrole1,preflang1,categoryy,disablity_type1,
            type_of_disablity1,Employment_status1,OtherIdproof1;
    String bankiddd,stateiddd,districtiddd,employeridd,employeridname,sectoridd,jobroleeiddd,preflangiddd;
    NetworkStateReceiver networkStateReceiver;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ArrayAdapter<String> jobroleadapter;
    private android.app.AlertDialog progressDialog;
    static final int REQUEST_IMAGE_CAPTURE = 1;
  String defaultCameraPackage;
  PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Spinner myspinner = findViewById(R.id.input_layout_gender);
        yearofbirth=findViewById(R.id.input_layout_year);
        category=findViewById(R.id.input_layout_category);
        monthofbirth=findViewById(R.id.input_layout_month);
        dateofbirth=findViewById(R.id.input_layout_date);
        education=findViewById(R.id.input_layout_Education);
        disablity_type = findViewById(R.id.input_layout_Disablity_type);
        type_of_disablity = findViewById(R.id.input_layout_type_of_Disablity);
        Employment_status= findViewById(R.id.employment_status);
        OtherIdproof = findViewById(R.id.otherIdproof);
        input_Id_no = findViewById(R.id.input_Id_no);
        course_detail= findViewById(R.id.course_detail);


        //employment=findViewById(R.id.input_layout_Employment);
        employer=findViewById(R.id.input_layout_Employer);
        sector=findViewById(R.id.input_layout_Sector);
        bankname=findViewById(R.id.input_layout_bankname);
        state=findViewById(R.id.input_layout_State);
        district=findViewById(R.id.input_layout_District);
        input_photograph1 = findViewById(R.id.input_photograph1);
        input_aadharpic1=findViewById(R.id.input_photograph_aadhar1);
        input_photograph=findViewById(R.id.input_photograph);
        input_aadharpic=findViewById(R.id.input_photograph_aadhar);
        input_submit=findViewById(R.id.btn_signup);
        input_name=findViewById(R.id.input_name);
        input_last_name=findViewById(R.id.input_last_name);
        input_mobile_no=findViewById(R.id.input_mobile_no);
        input_address1=findViewById(R.id.input_address1);
        input_address2=findViewById(R.id.input_address2);
        input_pincode=findViewById(R.id.input_pincode);
        input_jobrole=findViewById(R.id.input_layout_jobrole);
        input_empid=findViewById(R.id.input_empid);
        input_loc=findViewById(R.id.input_loc);
        input_aadhar=findViewById(R.id.input_aadhar);
        input_pancard = findViewById(R.id.input_pancard);
        input_bank_ac=findViewById(R.id.input_bank_ac);
        input_ifsc_code=findViewById(R.id.input_ifsc_code);
        input_bank_username = findViewById(R.id.input_bank_username);
        input_layout_prefferedlanguage=findViewById(R.id.input_layout_prefferedlanguage);
        progressDialog = new SpotsDialog(MainActivity.this, R.style.Custom);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        checkBox = findViewById(R.id.checkBox);
        Email = findViewById(R.id.input_email);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_name,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_last_name,"[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_address1,"(.|\\s)*\\S(.|\\s)*", R.string.err_msg_for_address1);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_pincode,"^[0-9]{6}$", R.string.err_msg_pincode);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_bank_ac,"^[0-9]{6,18}$", R.string.err_msg_for_acno);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_ifsc_code,"^[a-zA-Z0-9]{5,14}$", R.string.err_msg_for_ifsc);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_mobile_no,"^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_bank_username,"[a-zA-Z\\s]+", R.string.err_msg_for_namein_bank);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_email, Patterns.EMAIL_ADDRESS, R.string.err_msg_email);
       // awesomeValidation.addValidation(MainActivity.this, R.id.input_aadhar,"^[0-9]{12}$", R.string.err_msg_foraadhar);
        sector.setEnabled(false);
       /*
        employer.setEnabled(false);
        input_jobrole.setEnabled(false);
        input_empid.setEnabled(false);
        input_loc.setEnabled(false);
        input_layout_prefferedlanguage.setEnabled(false);*/
        Bankdetails();
        Statedetails();
        Employerlist();
        banks = new String[]{"Select the Bank"};
        states=new String[]{"Select the State"};
        districts=new String[]{"Select the District"};
        employers=new String[]{"Select the Employer"};
        jobrole=new String[]{"Select the Jobrole"};
        Statelist = new ArrayList<>(Arrays.asList(states));
        bankslist = new ArrayList<>(Arrays.asList(banks));
        districtlist=new ArrayList<>(Arrays.asList(districts));
        sectorlist=new ArrayList<>(Arrays.asList(sectors));
        employerlist=new ArrayList<>(Arrays.asList(employers));
        jobrolelist=new ArrayList<>(Arrays.asList(jobrole));
        preflang=new ArrayList<>(Arrays.asList(preflangg));
        mySwipeRefreshLayout=new SwipeRefreshLayout(getApplicationContext());




        input_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


               if(yearobirth.equals("Year")){
                    Toast.makeText(getApplicationContext(),"Year must be selected",Toast.LENGTH_LONG).show();
                }

               else if (gender.equals("Select Gender")){
                    Toast.makeText(getApplicationContext(),"Gender must be selected",Toast.LENGTH_LONG).show();
                }
                 else if (categoryy.equals("Select categroy")){
                     Toast.makeText(getApplicationContext(),"categroy must be selected",Toast.LENGTH_LONG).show();
                 }
                 else if (state1.equals("Select the State")){
                    Toast.makeText(getApplicationContext(),"State must be selected",Toast.LENGTH_LONG).show();
                }

             else if (district1.equals("Select the District")){
                    Toast.makeText(getApplicationContext(),"District must be selected",Toast.LENGTH_LONG).show();
                }

               else if (eduction1.equals("Select Education")){
                    Toast.makeText(getApplicationContext(),"Education must be selected",Toast.LENGTH_LONG).show();
                }

                else if (employer1.equals("Select the Employer")){
                    Toast.makeText(getApplicationContext(),"Employer must be selected",Toast.LENGTH_LONG).show();
                }
               else if (bankname1.equals("Select the Bank")){
                    Toast.makeText(getApplicationContext(),"Bank  name must be selected",Toast.LENGTH_LONG).show();

                }
                else  if (employeridname.equals("4") && (input_empid.getText().toString().matches(""))){

                       Toast.makeText(MainActivity.this, "Employee ID/Seller ID Cannot be empty", Toast.LENGTH_SHORT).show();
                }



                else if ((stateiddd.equals("2") && (input_pancard.getText().toString().matches(""))) ||
                       (stateiddd.equals("3") && (input_pancard.getText().toString().matches(""))) ||
                       (stateiddd.equals("16") && (input_pancard.getText().toString().matches("")))  ||
                       (stateiddd.equals("17") && (input_pancard.getText().toString().matches(""))) ||
                       (stateiddd.equals("18") && (input_pancard.getText().toString().matches(""))) ||
                       (stateiddd.equals("23") && (input_pancard.getText().toString().matches("")))||
                       (stateiddd.equals("19") && (input_pancard.getText().toString().matches(""))) ||
                       (stateiddd.equals("26") && (input_pancard.getText().toString().matches("")))||
                       (stateiddd.equals("10") && (input_pancard.getText().toString().matches(""))) )

                   {

                    Toast.makeText(MainActivity.this, "PAN Card cannot be empty according to your State", Toast.LENGTH_SHORT).show();
                  }

               else if ((stateiddd.equals("1") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("4") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("5") && (input_aadhar.getText().toString().matches("")))  ||
                       (stateiddd.equals("6") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("7") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("8") && (input_aadhar.getText().toString().matches("")))||
                       (stateiddd.equals("9") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("11") && (input_aadhar.getText().toString().matches("")))||
                       (stateiddd.equals("12") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("13") && (input_aadhar.getText().toString().matches("")))  ||
                       (stateiddd.equals("14") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("15") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("19") && (input_aadhar.getText().toString().matches("")))||
                       (stateiddd.equals("20") && (input_aadhar.getText().toString().matches("")))||
                       (stateiddd.equals("21") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("22") && (input_aadhar.getText().toString().matches("")))  ||
                       (stateiddd.equals("24") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("25") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("27") && (input_aadhar.getText().toString().matches("")))||
                       (stateiddd.equals("28") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("30") && (input_aadhar.getText().toString().matches("")))  ||
                       (stateiddd.equals("31") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("32") && (input_aadhar.getText().toString().matches(""))) ||
                       (stateiddd.equals("33") && (input_aadhar.getText().toString().matches("")))  ||
                       (stateiddd.equals("34") && (input_aadhar.getText().toString().matches(""))))





               {

                   Toast.makeText(MainActivity.this, "Aadhar Card Can't be empty according to your state", Toast.LENGTH_SHORT).show();
               }





                else if(awesomeValidation.validate() && !(gender.equals("Select Gender"))&& !state1.equals("Select the State")
                       && !yearobirth.equals("Year") && !district1.equals("Select the District") && !eduction1.equals("Select Education")
                         && !employer1.equals("Select the Employer") && !(bankname1.equals("Select the Bank")) && !(employeridname.equals("4") &&
                       (input_empid.getText().toString().matches("")))
                       && !(stateiddd.equals("2") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("3") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("16") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("17") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("18") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("23") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("19") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("26") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("10") && (input_pancard.getText().toString().matches("")))
                       && !(stateiddd.equals("4") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("5") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("6") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("7") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("8") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("9") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("11") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("12") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("13") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("14") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("15") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("19") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("20") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("21") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("22") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("24") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("25") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("27") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("28") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("29") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("30") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("31") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("32") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("33") && (input_aadhar.getText().toString().matches("")))
                       && !(stateiddd.equals("34") && (input_aadhar.getText().toString().matches("")))

                       && checkBox.isChecked() && encodedphoto!=null) {


                    Intent ii = new Intent(MainActivity.this, Reverify.class);
                    ii.putExtra("first_namee", input_name.getText().toString());
                    ii.putExtra("last_namee", input_last_name.getText().toString());
                    ii.putExtra("mobile", input_mobile_no.getText().toString());
                    ii.putExtra("aadhar", input_aadhar.getText().toString());
                    ii.putExtra("pancard", input_pancard.getText().toString());
                    ii.putExtra("bankaccount", input_bank_ac.getText().toString());
                    ii.putExtra("doy", yearobirth);
                    ii.putExtra("dom",monthobirth);
                    ii.putExtra("dod",dateobirth);
                    ii.putExtra("gender", gender);
                    ii.putExtra("bank", bankiddd);
                    ii.putExtra("state", stateiddd);
                    ii.putExtra("district", districtiddd);
                    ii.putExtra("education", eduction1);
                    //ii.putExtra("employed", employment1);
                    ii.putExtra("employer", employeridname);
                    ii.putExtra("sector", sectoridd);
                    ii.putExtra("addline1", input_address1.getText().toString());
                    ii.putExtra("addline2", input_address2.getText().toString());
                    ii.putExtra("pincode", input_pincode.getText().toString());
                    ii.putExtra("nameasinbank", input_bank_username.getText().toString());
                    ii.putExtra("ifsccode", input_ifsc_code.getText().toString());
                    ii.putExtra("jobrole",jobroleeiddd);
                    ii.putExtra("empid",input_empid.getText().toString());
                    ii.putExtra("location",input_loc.getText().toString());
                    ii.putExtra("preflang",preflangiddd);
                     ii.putExtra("pic",encodedphoto);
                     ii.putExtra("picaadhar",encodedphotoaadhar);
                     ii.putExtra("Email",Email.getText().toString());
                     ii.putExtra("categroy", categoryy);

                     startActivity(ii);

                }else
                {

                    Toast.makeText(getApplicationContext(), "The form is not filled correctly.Please verify it and submit.", Toast.LENGTH_LONG).show();
                }

            }
        });

        try{


        input_photograph.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {


                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                } else{
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });}

        catch (Exception e){
            e.printStackTrace();
        }


        try{


            input_photograph1.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {


                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }

                }
                    else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
            }

            });}

        catch (Exception e){
            e.printStackTrace();
        }




        input_aadharpic1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                }
                }else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                }

            }
        });






        input_aadharpic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                }

            }else{
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                }
            }
        });




        //Gender
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
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



        //disablity_type
        ArrayAdapter<String> myAdapterdisablity_type = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Disablity));
        myAdapterdisablity_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        disablity_type.setAdapter(myAdapterdisablity_type);

        disablity_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                disablity_type1=disablity_type.getSelectedItem().toString();
                if (disablity_type1.equals("Yes")){
                    type_of_disablity.setVisibility(View.VISIBLE);
                }
                else {
                    type_of_disablity.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //type_of_disablity





        ArrayAdapter<String> myAdapter_type_of_disablity = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type_of_Disablity));
        myAdapter_type_of_disablity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type_of_disablity.setAdapter(myAdapter_type_of_disablity);

        type_of_disablity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                type_of_disablity1=type_of_disablity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });



        //Employment _status


        ArrayAdapter<String> myAdapterEmployment_status = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Employment_status_string));
        myAdapterEmployment_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Employment_status.setAdapter(myAdapterEmployment_status);

        Employment_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                Employment_status1=Employment_status.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });




        // Other_id_Details


        ArrayAdapter<String> myAdapterOtherIdproof = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.other_id));
        myAdapterOtherIdproof.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        OtherIdproof.setAdapter(myAdapterOtherIdproof);

        OtherIdproof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                OtherIdproof1=OtherIdproof.getSelectedItem().toString();

                if (OtherIdproof1.equals("Other Id Proof")){
                    input_Id_no.setVisibility(View.GONE);
                }
                else {
                    input_Id_no.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


















        //Year of birth

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Year));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearofbirth.setAdapter(myAdapter1);

        yearofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                yearobirth=yearofbirth.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Month of birth

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Month));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        monthofbirth.setAdapter(myAdapter2);

        monthofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                      monthobirth=monthofbirth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Date of birth

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Date));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dateofbirth.setAdapter(myAdapter3);

        dateofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                dateobirth=dateofbirth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });

        //Education
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Education));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education.setAdapter(myAdapter4);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                eduction1=education.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //Employer
        ArrayAdapter<String> myAdapterEmployer = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,employerlist);
        myAdapterEmployer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employer.setAdapter(myAdapterEmployer);

        employer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {

                if(position > 0) {
                    employer1 = employer.getSelectedItem().toString();
                    employeridname = employdetail.get(employer1);

                    Sectorlist(employeridname);
                    languageSelect(employeridname);
                    getJobroleList(employeridname);

                    sector.setVisibility(View.VISIBLE);
                    input_layout_prefferedlanguage.setVisibility(View.VISIBLE);
                    input_jobrole.setVisibility(View.VISIBLE);
                    course_detail.setVisibility(View.VISIBLE);



                }
                else
                    {
                        employer1=employer.getSelectedItem().toString();
                        sector.setVisibility(View.GONE);
                        input_layout_prefferedlanguage.setVisibility(View.GONE);
                        input_jobrole.setVisibility(View.GONE);
                        course_detail.setVisibility(View.GONE);




                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Sector

        ArrayAdapter<String> myAdaptersector = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,sectorlist);
        myAdaptersector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sector.setAdapter(myAdaptersector);

        sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                sector1=sector.getSelectedItem().toString();
               String selectedsectortext  = (String) parent.getItemAtPosition(position);

                if(position > 0){
                    sectoridd=sectordetail.get(selectedsectortext);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //jobrole


        input_jobrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
               // if(position > 0) {
                    jobrole1 = input_jobrole.getSelectedItem().toString();
                    jobroleeiddd=Jobrolelist.get(jobrole1);
               // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Choose category
        ArrayAdapter<String> categoryadapt = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Category));
        categoryadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(categoryadapt);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
               // if(position > 0) {
                    categoryy = category.getSelectedItem().toString();
                    //jobroleeiddd=Jobrolelist.get(jobrole1);
               // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //Preffered Exam Language


        input_layout_prefferedlanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                    preflang1 = input_layout_prefferedlanguage.getSelectedItem().toString();
                    preflangiddd=langdetail.get(preflang1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });



        //Bankname

        ArrayAdapter<String> myAdapterBankname = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,bankslist);
        myAdapterBankname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bankname.setAdapter(myAdapterBankname);

        bankname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                bankname1=bankname.getSelectedItem().toString();
                if(position > 0){
                    bankiddd= bankdetail.get(bankname1);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //state

        ArrayAdapter<String> myAdapterState = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,Statelist);
        myAdapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(myAdapterState);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                state1=state.getSelectedItem().toString();
               selectedstatetext =(String) parent.getItemAtPosition(position);

                if(position > 0){
                    String value= Statedetail.get(selectedstatetext);
                    stateiddd=value;
                    DistrictDetails(value);
                    district.setVisibility(View.VISIBLE);


                }
                else {
                    district.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //District

      /*  if (districtlist.size()>1){
            districtlist.clear();
        }
        districtlist.add("Select the District");*/
        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                district1=district.getSelectedItem().toString();
                districtiddd=districtdetail.get(district1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

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

    //API For Bank
    private void Bankdetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_bank.php";
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("bank");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            bankid = c.getString("id");
                            bankvalue = c.getString("name");
                            bankdetail.put(bankvalue, bankid);
                            bankslist.add(bankvalue);

                        }
                       // Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Bank Details",Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (pd.isShowing()) {
                    pd.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to fetch Bank Details", Toast.LENGTH_LONG).show();
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

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //Language Api Call
    private void languageSelect(final String cmp_id) {

        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_language.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("language");
                        preflang.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            preflangid = c.getString("language_code");
                            preflangvalue = c.getString("name");
                            langdetail.put(preflangvalue,preflangid );
                            preflang.add(preflangvalue);
                        }
                        ArrayAdapter<String> preflanguage = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1,preflang);
                        preflanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_layout_prefferedlanguage.setAdapter(preflanguage);

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Language Details",Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), "Failed to fetch Language Details", Toast.LENGTH_LONG).show();
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
                map.put("company_id",cmp_id);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    //State List
    private void Statedetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_state.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("state");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            Stateid = c.getString("id");
                            Statevalue = c.getString("name");
                            Statedetail.put(Statevalue, Stateid);
                            Statelist.add(Statevalue);
                        }
                        //Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch States",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
               /* if (pd.isShowing()) {
                    pd.dismiss();
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to fetch State list", Toast.LENGTH_LONG).show();
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

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //District_List
    private void DistrictDetails(final String districtidd) {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_district.php";
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
         pd.show();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        districtlist.clear();
                        JSONArray jsonArray=jobj.getJSONArray("district");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            districtid = c.getString("id");
                            districtvalue = c.getString("name");
                            districtdetail.put(districtvalue, districtid);
                                districtlist.add(districtvalue);


                        }
                        ArrayAdapter<String> myAdapterDistrict = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1,districtlist);
                        myAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        district.setAdapter(myAdapterDistrict);
                        //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Districts",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (pd.isShowing()) {
                    pd.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                   pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to fetch Districts", Toast.LENGTH_LONG).show();
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
                map.put("state_id",districtidd);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //Employer_List
    private void Employerlist() {
       /* pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
*/
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_employer.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("employer");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            employerid = c.getString("id");
                            employervalue = c.getString("name");
                            employdetail.put(employervalue, employerid);
                            employerlist.add(employervalue);
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Employers",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
               /* if (pd.isShowing()) {
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pd.dismiss();
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

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //Sector_list
    private void Sectorlist(final String Sectorvalue) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_sector.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    if (sectorlist.size()>2){
                    sectorlist.clear();
                    }
                    sectorlist.add("Choose the Sector");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("sector");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            sectorid = c.getString("id");
                            sectorvalue = c.getString("name");
                            sectordetail.put(sectorvalue, sectorid);
                            sectorlist.add(sectorvalue);
                            sector.setSelection(sectorlist.size()-1);

                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Sector Details",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Failed to fetch Sectors List", Toast.LENGTH_LONG).show();
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
                map.put("company_id",Sectorvalue);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //Jobrole Api Call
    private void getJobroleList(final String sscid) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_jobrole.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");

                    /*if (jobrolelist.size()<=1){
                        jobrolelist.clear();
                    }*/
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("jobrole");
                        jobrolelist.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            jobroleid = c.getString("jobrole_id");
                            jobrolevalue = c.getString("name");
                            Jobrolelist.put(jobrolevalue, jobroleid);
                            jobrolelist.add(jobrolevalue);
                        }

                            jobroleadapter= new ArrayAdapter<String>(MainActivity.this,
                                    android.R.layout.simple_list_item_1,jobrolelist);
                            jobroleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            input_jobrole.setAdapter(jobroleadapter);



                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Job Roles",Toast.LENGTH_LONG).show();
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
                map.put("company_id",sscid);
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
                input_photograph.setImageBitmap(photo);
                int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                Bitmap newbitMap = Bitmap.createScaledBitmap(photo, currentBitmapWidth, newHeight, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                newbitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encodedphoto = Base64.encodeToString(byteArray, Base64.DEFAULT);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }
}
