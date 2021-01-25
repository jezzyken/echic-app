package com.jezrelljolampong.echicapp.Model;

public class Aid {
    public String aidname;
    public int aidphoto;

    public Aid(String aidname, int aidphoto) {
        this.aidname = aidname;
        this.aidphoto = aidphoto;
    }

    public String getAidname() {
        return aidname;
    }

    public void setAidname(String aidname) {
        this.aidname = aidname;
    }

    public int getAidphoto() {
        return aidphoto;
    }

    public void setAidphoto(int aidphoto) {
        this.aidphoto = aidphoto;
    }
}
