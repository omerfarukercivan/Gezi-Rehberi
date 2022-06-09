package com.example.rehberuygulamasi;

public class PuanModel {
    Integer toplamkisi;
    Float toplampuan, ortalama;

    public PuanModel() {
    }

    public PuanModel(Integer toplamkisi, Float toplampuan, Float ortalama) {
        this.toplamkisi = toplamkisi;
        this.toplampuan = toplampuan;
        this.ortalama = ortalama;
    }

    public Integer getToplamkisi() {
        return toplamkisi;
    }

    public void setToplamkisi(Integer toplamkisi) {
        this.toplamkisi = toplamkisi;
    }

    public Float getToplampuan() {
        return toplampuan;
    }

    public void setToplampuan(Float toplampuan) {
        this.toplampuan = toplampuan;
    }

    public Float getOrtalama() {
        return ortalama;
    }

    public void setOrtalama(Float ortalama) {
        this.ortalama = ortalama;
    }
}
