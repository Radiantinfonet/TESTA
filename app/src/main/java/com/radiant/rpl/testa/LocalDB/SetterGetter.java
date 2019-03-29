package com.radiant.rpl.testa.LocalDB;

public class SetterGetter {

    String Stuid,Assessorid,attenstatuss;

    public String getStuid() {
        return Stuid;
    }

    public void setStuid(String Stuid) {
        this.Stuid = Stuid;
    }

public SetterGetter(){}
    public SetterGetter(String Stuid, String Assessorid, String attenstatuss) {
        this.Stuid = Stuid;
        this.Assessorid = Assessorid;
        this.attenstatuss = attenstatuss;
    }

    public String getAssessorid() {
        return Assessorid;
    }

    public void setAssessorid(String Assessorid) {
        this.Assessorid = Assessorid;
    }

    public String getAttenstatuss() {
        return attenstatuss;
    }

    public void setAttenstatuss(String attenstatuss) {
        this.attenstatuss = attenstatuss;
    }
}
