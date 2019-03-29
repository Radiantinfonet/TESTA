package com.radiant.rpl.testa;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.ExamSection.TestQuestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class Testinstruction extends AppCompatActivity {
    Button testinstructproceed;
    AlertDialog alertDialog;
    String language,languageid;
    HashMap<String,String> hm=new HashMap<>();
    CharSequence[] values;
    List<String> listItems = new ArrayList<String>();
    SharedPreferences ssp,sspp;
         String batchidddd;
    Bundle b;
    SharedPreferences.Editor editor;
    private android.app.AlertDialog progressDialog;
    String geturl,gettestingurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinstruction);
        testinstructproceed=findViewById(R.id.testinstructproceed);
        b=new Bundle();
        ssp=getSharedPreferences("mypreff",MODE_PRIVATE);
        sspp=getSharedPreferences("mypref",MODE_PRIVATE);
        geturl= Start_Registration.getURL();
        gettestingurl=Start_Registration.getTestingURL();
        progressDialog = new SpotsDialog(Testinstruction.this, R.style.Custom);
        batchidddd=sspp.getString("batchid","");
        testinstructproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getExamLanguage(batchidddd);


            }
        });
    }

    public void showDialog(){
        values = listItems.toArray(new CharSequence[listItems.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(Testinstruction.this);
        builder.setTitle("Choose Your Language");
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                switch (i) {
                    case 0:
                        String aa = String.valueOf(values[i]);
                        String bb = hm.get(aa);
                        SharedPreferences.Editor editor = ssp.edit();
                        editor.putString("languagev", bb);
                        editor.apply();
                        editor.commit();
                            Intent ii = new Intent(Testinstruction.this, TestQuestion.class);
                            startActivity(ii);

                        break;
                    case 1:
                        String cc= String.valueOf(values[i]);
                        String dd=hm.get(cc);
                        SharedPreferences.Editor editor1 = ssp.edit();
                        editor1.putString("languagev", dd);
                        editor1.apply();
                        editor1.commit();
                        Intent iii = new Intent(Testinstruction.this, TestQuestion.class);
                        startActivity(iii);

                        break;
                }

                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();





    }


    //Exam Languages
    private void getExamLanguage(final String Sectorvalue) {
        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_batch_language.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("language");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            languageid = c.getString("language_code");
                            language = c.getString("name");
                            hm.put(language, languageid);
                            if (listItems.size()<=1){
                            listItems.add(language);
                            }

                        }
                        showDialog();

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
                Toast.makeText(getApplicationContext(), "Failed to fetch Languages List", Toast.LENGTH_LONG).show();
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
                map.put("batch_id",Sectorvalue);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }








}
