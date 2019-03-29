package com.radiant.rpl.testa.ExamSection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import radiant.rpl.radiantrpl.R;

public class Thankspage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankspage);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //exitByBackKey();

            moveTaskToBack(true);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
