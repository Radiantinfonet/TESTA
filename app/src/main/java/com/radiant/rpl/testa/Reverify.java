package com.radiant.rpl.testa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    EditText fname_txt,lname_txt,mob_txt,aadharno_txt,bankacc_txt,pancard_txt,name_in_bank,ifsc_txt;
    String fname,lname,mob,aadharno,bankacc,yearobirth,monthobirth,dateobirthh,gender,bank1,statee,
            districtt,educationn,employedd,employerr,sectorr,addline11,addline22,pincode1,nameasinbank1,
    iffccode1,photouri,jobrolee,empidd,locationn,aadharpic,language,category1,Email1,disablity_type,type_of_disablity,job_status,
            other_id_proof,other_id_proof_no,pancard
            ,alt_no,your_city,other_qualification;
    String getFname,getLname,getMob,getAadharno,getBankacc,getpancard,getname_in_bank,get_ifsc;
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
        pancard_txt = findViewById(R.id.input_pancard);
        name_in_bank = findViewById(R.id.input_bank_username1);
        ifsc_txt = findViewById(R.id.input_ifsc_code1);


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
        disablity_type = ii.getStringExtra("Any_disability");
        type_of_disablity= ii.getStringExtra("type_of_disblity");
        job_status=ii.getStringExtra("Employment_status");
        other_id_proof=ii.getStringExtra("other_Id_proof_type");
        alt_no= ii.getStringExtra("alt_no");
        your_city= ii.getStringExtra("your_city");
        other_qualification= ii.getStringExtra("other_qualification");
        other_id_proof_no = ii.getStringExtra("input_id_no");
        pancard = ii.getStringExtra("pancard");








        fname_txt.setText(fname);
        lname_txt.setText(lname);
        mob_txt.setText(mob);
        aadharno_txt.setText(aadharno);
        bankacc_txt.setText(bankacc);
        pancard_txt.setText(pancard);
        name_in_bank.setText(nameasinbank1);
        ifsc_txt.setText(iffccode1);



        btn_Register=findViewById(R.id.btn_Register);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFname=fname_txt.getText().toString();
                getLname=lname_txt.getText().toString();
                getMob=mob_txt.getText().toString();
                getAadharno=aadharno_txt.getText().toString();
                getBankacc=bankacc_txt.getText().toString();
                getpancard = pancard_txt.getText().toString();
                getname_in_bank = name_in_bank.getText().toString();
                get_ifsc = ifsc_txt.getText().toString();
                if ((statee.equals("2") && (pancard_txt.getText().toString().matches(""))) ||
                        (statee.equals("3") && (pancard_txt.getText().toString().matches(""))) ||
                        (statee.equals("16") && (pancard_txt.getText().toString().matches("")))  ||
                        (statee.equals("17") && (pancard_txt.getText().toString().matches(""))) ||
                        (statee.equals("18") && (pancard_txt.getText().toString().matches(""))) ||
                        (statee.equals("23") && (pancard_txt.getText().toString().matches("")))||
                        (statee.equals("19") && (pancard_txt.getText().toString().matches(""))) ||
                        (statee.equals("26") && (pancard_txt.getText().toString().matches("")))||
                        (statee.equals("10") && (pancard_txt.getText().toString().matches(""))) )

                {

                    Toast.makeText(Reverify.this, "PAN Card cannot be empty according to your State", Toast.LENGTH_SHORT).show();
                }

                else if ((statee.equals("1") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("4") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("5") && (aadharno_txt.getText().toString().matches("")))  ||
                        (statee.equals("6") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("7") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("8") && (aadharno_txt.getText().toString().matches("")))||
                        (statee.equals("9") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("11") && (aadharno_txt.getText().toString().matches("")))||
                        (statee.equals("12") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("13") && (aadharno_txt.getText().toString().matches("")))  ||
                        (statee.equals("14") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("15") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("19") && (aadharno_txt.getText().toString().matches("")))||
                        (statee.equals("20") && (aadharno_txt.getText().toString().matches("")))||
                        (statee.equals("21") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("22") && (aadharno_txt.getText().toString().matches("")))  ||
                        (statee.equals("24") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("25") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("27") && (aadharno_txt.getText().toString().matches("")))||
                        (statee.equals("28") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("30") && (aadharno_txt.getText().toString().matches("")))  ||
                        (statee.equals("31") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("32") && (aadharno_txt.getText().toString().matches(""))) ||
                        (statee.equals("33") && (aadharno_txt.getText().toString().matches("")))  ||
                        (statee.equals("34") && (aadharno_txt.getText().toString().matches(""))))





                {

                    Toast.makeText(Reverify.this, "Aadhar Card Can't be empty according to your state", Toast.LENGTH_SHORT).show();
                }
               else if (awesomeValidation.validate()) {
                    SaveDetail(getFname, getLname, getMob, getAadharno, getBankacc, getpancard, getname_in_bank, get_ifsc);
                }

            }
        });

        awesomeValidation.addValidation(Reverify.this, R.id.input_fname,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Reverify.this, R.id.input_last_lname,"[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Reverify.this, R.id.input_mobile_noo,"^[0-9]{10}$", R.string.err_msg_formobile);
        //awesomeValidation.addValidation(Reverify.this, R.id.input_aadhar_no,"^[0-9]{12}$", R.string.err_msg_foraadhar);
        awesomeValidation.addValidation(Reverify.this, R.id.input_bank_username1,"[a-zA-Z\\s]+", R.string.err_msg_for_namein_bank);
        awesomeValidation.addValidation(Reverify.this, R.id.input_ifsc_code1,"^[a-zA-Z0-9]{5,14}$", R.string.err_msg_for_ifsc);
        awesomeValidation.addValidation(Reverify.this, R.id.input_bank_acdetails,"^[0-9]{6,18}$", R.string.err_msg_for_acno);
    }


    private void SaveDetail(final String fnamee, final String lnamee, final String mobbb, final String aadhaar, final String bankacccc,
                            final String pancard,final String name_in_bank,final String ifsc) {
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
                        Log.d("Response",response);
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

                map.put("bankac",bankacccc);
                map.put("pan",pancard);




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
                map.put("aadhar",aadharno_txt.getText().toString());
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

                if (disablity_type!=null){
                    map.put("disabilitytype",disablity_type);}

                if (type_of_disablity!=null){
                    map.put("disability",disablity_type);}

                if (job_status!=null){
                    map.put("employment",job_status);}

                if (other_id_proof!=null){
                    map.put("other_id_type",other_id_proof);}

                if (other_id_proof_no!=null){
                    map.put("other_id_number",other_id_proof_no);}


                if (alt_no!=null){
                    map.put("landline",alt_no);}


                if (your_city!=null){
                    map.put("City",your_city);}


                if (other_qualification!=null){
                    map.put("other_qualification",other_qualification);}



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
