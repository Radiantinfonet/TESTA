package com.radiant.rpl.testa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import radiant.rpl.radiantrpl.R;

public class Reverify extends AppCompatActivity {

    EditText fname_txt,lname_txt,mob_txt,aadharno_txt,bankacc_txt;
    String fname,lname,mob,aadharno,bankacc,yearobirth,monthobirth,dateobirthh,gender,bank1,statee,districtt,educationn,employedd,employerr,sectorr,addline11,addline22,pincode1,nameasinbank1,
    iffccode1,photouri,jobrolee,empidd,locationn,aadharpic,language,category1,Email1;
    String getFname,getLname,getMob,getAadharno,getBankacc;
    ProgressDialog pd;
    Button btn_Register;
    AwesomeValidation awesomeValidation;
    String geturl,gettestingurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverify);
        fname_txt=findViewById(R.id.input_fname);
        lname_txt=findViewById(R.id.input_last_lname);
        mob_txt=findViewById(R.id.input_mobile_noo);
        aadharno_txt=findViewById(R.id.input_aadhar_no);
        bankacc_txt=findViewById(R.id.input_bank_acdetails);
        geturl= Start_Registration.getURL();
        gettestingurl=Start_Registration.getTestingURL();
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        Intent ii=getIntent();
        fname=ii.getStringExtra("first_namee");
        lname=ii.getStringExtra("last_namee");
        mob=ii.getStringExtra("mobile");
        aadharno=ii.getStringExtra("aadhar");
        bankacc=ii.getStringExtra("bankaccount");
        yearobirth=ii.getStringExtra("doy");
        monthobirth=ii.getStringExtra("dom");
        dateobirthh=ii.getStringExtra("dod");
        gender=ii.getStringExtra("gender");
        bank1=ii.getStringExtra("bank");
        statee=ii.getStringExtra("state");
        districtt=ii.getStringExtra("district");
        educationn=ii.getStringExtra("education");
        //employedd=ii.getStringExtra("employed");
        employerr=ii.getStringExtra("employer");
        sectorr=ii.getStringExtra("sector");
        addline11=ii.getStringExtra("addline1");
        addline22=ii.getStringExtra("addline2");
        pincode1=ii.getStringExtra("pincode");
        nameasinbank1=ii.getStringExtra("nameasinbank");
        iffccode1=ii.getStringExtra("ifsccode");
        jobrolee=ii.getStringExtra("jobrole");
        empidd=ii.getStringExtra("empid");
        language=ii.getStringExtra("preflang");
        locationn=ii.getStringExtra("location");
        aadharpic=ii.getStringExtra("picaadhar");
        photouri=ii.getStringExtra("pic");
        category1 = ii.getStringExtra("categroy");
        Email1= ii.getStringExtra("Email");

        fname_txt.setText(fname);
        lname_txt.setText(lname);
        mob_txt.setText(mob);
        aadharno_txt.setText(aadharno);
        bankacc_txt.setText(bankacc);

        btn_Register=findViewById(R.id.btn_Register);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFname=fname_txt.getText().toString();
                getLname=lname_txt.getText().toString();
                getMob=mob_txt.getText().toString();
                getAadharno=aadharno_txt.getText().toString();
                getBankacc=bankacc_txt.getText().toString();
                SaveDetail(getFname,getLname,getMob,getAadharno,getBankacc);


            }
        });

        awesomeValidation.addValidation(Reverify.this, R.id.input_fname,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Reverify.this, R.id.input_last_lname,"[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Reverify.this, R.id.input_mobile_noo,"^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(Reverify.this, R.id.input_aadhar_no,"^[0-9]{12}$", R.string.err_msg_foraadhar);
        awesomeValidation.addValidation(Reverify.this, R.id.input_bank_acdetails,"^[0-9]{6,18}$", R.string.err_msg_for_acno);
    }


    private void SaveDetail(final String fnamee, final String lnamee, final String mobbb, final String aadhaar, final String bankacccc) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/save_student_data.php";
        pd = new ProgressDialog(Reverify.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                    String status= jobj.getString("status");
                    if (status.equals("1")){
                          Intent iii=new Intent(Reverify.this, Registration_Done.class);
                          iii.putExtra("sectorid",sectorr);
                          iii.putExtra("mobileeno",mobbb);
                          startActivity(iii);
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Error Saving the details"+response,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "error+Error Saving the details"+error, Toast.LENGTH_LONG).show();
                System.out.println("aa"+error);
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
                map.put("firstname",fnamee);
                map.put("lastname",lnamee);
                map.put("mobile",mobbb);
                map.put("aadhar",aadhaar);
                map.put("bankac",bankacccc);
                if (monthobirth!=null || dateobirthh!=null){
                map.put("dob",yearobirth+"-"+monthobirth+"-"+dateobirthh);}
                else{
                    map.put("dob",yearobirth);
                }
                map.put("gender",gender);
                map.put("qualification",educationn);
                map.put("address1",addline11);
                if (addline22!=null){
                map.put("address2",addline22);}
                map.put("state_id",statee);
                map.put("district_id",districtt);
                map.put("pincode",pincode1);
                map.put("aadhar",aadharno);
                map.put("ssc_id",sectorr);
                map.put("company_id",employerr);
                map.put("sector_id","0");
                map.put("bankname",bank1);
                map.put("name_in_bank",nameasinbank1);
                map.put("ifsc",iffccode1);
                map.put("jobrole_id",jobrolee);
                if (empidd!=null){
                map.put("employee_id",empidd);}
                map.put("language",language);
                if (locationn!=null){
                map.put("StoreLocation",locationn);}
                if (category1!=null){
                map.put("category",category1);}
                if (Email1!=null){
                map.put("email",Email1);}


                if (aadharpic!=null){
                map.put("aadhar_image",aadharpic);}
                map.put("student_image",photouri);
                map.put("data_source","Mobile");
                System.out.print("ggggggg"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


}
