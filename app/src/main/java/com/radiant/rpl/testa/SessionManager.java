package com.radiant.rpl.testa;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Vipin", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public  String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("Vipin",	Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }

    /*public void cleard(Context context, String key, String value){
        SharedPreferences prefs1 = context.getSharedPreferences("Vipin",	Context.MODE_PRIVATE);
        prefs1.clear();
        prefs1.commit();
    }*/
}
