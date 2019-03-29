package com.radiant.rpl.testa.LocalDB;


import com.orm.SugarRecord;

public class Studentslistdb extends SugarRecord<Studentslistdb> {
    String userid;
    String Studentid;
    String attenstatus;

    public Studentslistdb(){

    }

    public Studentslistdb(String userid, String Studentid, String attenstatus){
         this.userid=userid;
         this.Studentid=Studentid;
         this.attenstatus=attenstatus;
    }
}
