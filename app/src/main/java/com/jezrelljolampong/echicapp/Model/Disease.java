package com.jezrelljolampong.echicapp.Model;

public class Disease {
    private String diseasename;
    private int diseasephoto;

    public Disease(String diseasename, int diseasephoto) {
        this.diseasename = diseasename;
        this.diseasephoto = diseasephoto;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public int getDiseasephoto() {
        return diseasephoto;
    }

    public void setDiseasephoto(int diseasephoto) {
        this.diseasephoto = diseasephoto;
    }
}
