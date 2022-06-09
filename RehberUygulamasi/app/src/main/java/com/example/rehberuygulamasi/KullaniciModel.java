package com.example.rehberuygulamasi;

public class KullaniciModel {
    public String isim, email;

    public KullaniciModel(){

    }

    public KullaniciModel(String isim, String email){
        this.isim = isim;
        this.email = email;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
