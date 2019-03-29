package com.radiant.rpl.testa.ExamSection;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import java.util.HashMap;

import radiant.rpl.radiantrpl.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentChild1 extends Fragment implements View.OnClickListener {
    String childname,quename,option1,option2,option3,option4;
    int pgnn;
    String dummystuid;
    SharedPreferences sp;

    TextView textViewChildName,t1,optiona,optionb,optionc,optiond,titlea,titleb,titlec,titled;
    LinearLayout l1,l2,l3,l4;
    DbAutoSave dbAutoSave;
    String idd;
    String query;
    boolean statusvalue;
    HashMap<String,String> hm=new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child1, container, false);
        Bundle bundle = getArguments();
        pgnn=bundle.getInt("pgno");
        childname = bundle.getString("data");
        quename =bundle.getString("daa");
        option1=bundle.getString("op1");
        option2=bundle.getString("op2");
        option3=bundle.getString("op3");
        option4=bundle.getString("op4");
        hm.put(quename,childname);
        sp=getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        dbAutoSave = new DbAutoSave(getContext());
        getIDs(view);
        setEvents();
        idd=dbAutoSave.getDataOfSingleClient(query);
        dummystuid=sp.getString("userid","");
        return view;
    }

    private void getIDs(View view) {
        textViewChildName =view.findViewById(R.id.Quesnoo1);
        t1=view.findViewById(R.id.que1);
        optiona=view.findViewById(R.id.optiona1);
        optionb=view.findViewById(R.id.optionb1);
        optionc=view.findViewById(R.id.optionc1);
        optiond=view.findViewById(R.id.optiond1);
        titlea=view.findViewById(R.id.optionnoa1);
        titleb=view.findViewById(R.id.optionnob1);
        titlec=view.findViewById(R.id.optionnoc1);
        titled=view.findViewById(R.id.optionnod1);
        l1=view.findViewById(R.id.option11);
        l2=view.findViewById(R.id.option21);
        l3=view.findViewById(R.id.option31);
        l4=view.findViewById(R.id.option41);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        textViewChildName.setText(pgnn+".)");
        //textViewChildName.setText(childname+".)");
        t1.setText(quename);
        optiona.setText(option1);
        optionb.setText(option2);
        optionc.setText(option3);
        optiond.setText(option4);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void changeColorBack(TextView textView)
    {
        textView.setBackground(getResources().getDrawable(R.drawable.rounded_grey));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onResume();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String aab;
        if (!getUserVisibleHint())
        {
            return;
        }
        query=hm.get(quename);
        if (dbAutoSave.getD(query)!=null){
            aab =dbAutoSave.getD(hm.get(quename));
        }
        else{
            aab="BB";
        }

        if (aab.equals("A")){
            titlea.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }else if (aab.equals("B")){
            titleb.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }
        else if (aab.equals("C")){
            titlec.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }
        else if (aab.equals("D")){
            titled.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }
        else {

        }
        //Show Answer updated in Db
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        l3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        l4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        if (statusvalue){
        }
        else{
        }

    }



    private void setEvents() {

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        changeColorBack(titlea);
        changeColorBack(titleb);
        changeColorBack(titlec);
        changeColorBack(titled);

        switch (v.getId()) {
            case R.id.option11:
                titlea.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titlea.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titlea.getText().toString());
                    dbAutoSave.insertDataunanswered(dummystuid,hm.get(quename),"1");
                    statusvalue=true;
                }
                break;
            case R.id.option21:
                titleb.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titleb.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titleb.getText().toString());
                    dbAutoSave.insertDataunanswered(dummystuid,hm.get(quename),"1");
                    statusvalue=true;
                }
                break;
            case R.id.option31:
                titlec.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titlec.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titlec.getText().toString());
                    dbAutoSave.insertDataunanswered(dummystuid,hm.get(quename),"1");
                    statusvalue=true;
                }
                break;
            case  R.id.option41:

                titled.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titled.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename), dummystuid,  titled.getText().toString());
                    dbAutoSave.insertDataunanswered(dummystuid,hm.get(quename),"1");
                    statusvalue=true;
                }
                break;
            default:
                break;
        }
    }
}
