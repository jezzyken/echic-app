package com.jezrelljolampong.echicapp.Model;

public class Medicine  {
    public String medicinename;
    public int medicinephoto;

    public Medicine(String medicinename, int medicinephoto) {
        this.medicinename = medicinename;
        this.medicinephoto = medicinephoto;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public int getMedicinephoto() {
        return medicinephoto;
    }

    public void setMedicinephoto(int medicinephoto) {
        this.medicinephoto = medicinephoto;
    }
}
