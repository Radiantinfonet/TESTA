package com.radiant.rpl.testa.ExamSection;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import java.util.ArrayList;

import radiant.rpl.radiantrpl.R;

public class CustomAdapter extends BaseAdapter{
    String result[];
    Context con;
    String aa;
    ArrayList<String> queidd=new ArrayList<>();
    ArrayList<String> statussss=new ArrayList<>();
    ArrayList<String> questatuss=new ArrayList<>();
    String img[];
    DbAutoSave dbAutoSave;

    private static LayoutInflater inflater = null;

    public CustomAdapter(ArrayList<String> queidd, Context con, ArrayList<String> statussss, ArrayList<String> questatuss) {
        this.queidd = queidd;
        this.con = con;
        this.statussss=statussss;
        this.questatuss=questatuss;
        inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return queidd.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final holder  hld = new holder();
        View rowview;


            rowview = inflater.inflate(R.layout.gdmainfortestquestion, null);
            hld.tv = rowview.findViewById(R.id.txt2);
            hld.tv.setText(queidd.get(position));
            int ii=statussss.size();
            if (position<ii){
            if (statussss.get(position).equals("1")){
                hld.tv.setTextColor(Color.GREEN);
            }else if (statussss.get(position).equals("2")){
                hld.tv.setTextColor(con.getResources().getColor(R.color.purple));
            }else if (statussss.get(position).equals("0")){
                hld.tv.setTextColor(Color.BLUE);
            }else {
                hld.tv.setTextColor(Color.BLACK);
            }
            }
            else {
                hld.tv.setTextColor(Color.BLACK);
            }
            /*  if (queidd.get(position).equals(questatuss.get(position))){
                  hld.tv.setTextColor(Color.BLUE);
              }
            else
              {
                  hld.tv.setTextColor(Color.GREEN);
              }*/
            return rowview;
    }



    public class holder
    {
        TextView tv;

    }



}

