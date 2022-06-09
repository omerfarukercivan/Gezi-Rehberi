package com.example.rehberuygulamasi;

import java.io.Serializable;

public class KategoriModel implements Serializable {
    String isim, fotograf, bilgi, sure, ucret, id, ortalama;
    Integer toplamkisi;
    Float toplampuan, sayi;

    KategoriModel(){
    }

    public KategoriModel(String isim, String fotograf, String bilgi, String sure, String ucret, String id, Integer toplamkisi, Float toplampuan, Float sayi, String ortalama) {
        this.isim = isim;
        this.fotograf = fotograf;
        this.bilgi = bilgi;
        this.sure = sure;
        this.ucret = ucret;
        this.id = id;
        this.toplamkisi = toplamkisi;
        this.toplampuan = toplampuan;
        this.sayi = sayi;
        this.ortalama = ortalama;
    }

    public KategoriModel(Integer toplamkisi, Float toplampuan, Float sayi) {
        this.toplamkisi = toplamkisi;
        this.toplampuan = toplampuan;
        this.sayi = sayi;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getFotograf() {
        return fotograf;
    }

    public void setFotograf(String fotograf) {
        this.fotograf = fotograf;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public String getUcret() {
        return ucret;
    }

    public void setUcret(String ucret) {
        this.ucret = ucret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Float getSayi() {
        return sayi;
    }

    public void setSayi(Float sayi) {
        this.sayi = sayi;
    }

    public String getOrtalama() {
        return ortalama;
    }

    public void setOrtalama(String ortalama) {
        this.ortalama = ortalama;
    }
}