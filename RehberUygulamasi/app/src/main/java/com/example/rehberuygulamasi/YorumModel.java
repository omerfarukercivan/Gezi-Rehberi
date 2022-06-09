package com.example.rehberuygulamasi;

public class YorumModel {
    public String isim, yorum;

    public YorumModel() {
    }

    public YorumModel(String isim, String yorum) {
        this.isim = isim;
        this.yorum = yorum;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
}
