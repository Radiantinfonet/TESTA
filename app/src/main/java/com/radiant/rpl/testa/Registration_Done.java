package com.radiant.rpl.testa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import radiant.rpl.radiantrpl.R;

public class Registration_Done extends AppCompatActivity {
TextView tvv,tvv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__done);
        tvv=findViewById(R.id.clickhere);
        tvv1=findViewById(R.id.clickheree);
        Intent iiii=getIntent();
       String sectorrr= iiii.getStringExtra("sectorid");
       String mobbbbb=iiii.getStringExtra("mobileeno");

        String styledText = "<u><font color='red'>Click Here</font></u> to proceed further for assessment.";
        if (sectorrr.equals("40")) {
            tvv.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        }
        else {
            tvv.setVisibility(View.GONE);
        }
        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.skillassessment.org/retail/";

             Intent ii=new Intent(Registration_Done.this, Start_Registration.class);
                   startActivity(ii);
                /*Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
            }
        });

        String styledText1 = "परीक्षा शुरू करने के लिए यहां "+"<u><font color='red'>क्लिक करें।</font></u>";
        if (sectorrr.equals("40")) {
            tvv1.setText(Html.fromHtml(styledText1), TextView.BufferType.SPANNABLE);
        }
        else {
            tvv1.setVisibility(View.GONE);
        }
        tvv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.skillassessment.org/retail/";
                Intent ii=new Intent(Registration_Done.this, Start_Registration.class);
                startActivity(ii);
               /* Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
            }
        });

    }
}
