package com.example.rehberuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class SayfaDetay extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    TextView tvisim, tvucret, tvsure, tvbilgi, tvyildizSayisi;
    ImageView fotograf;

    //YORUM YAPMA
    EditText yorumEt;
    ImageButton gonderButon;
    String id, Id;
    RecyclerView rvYorum;
    YorumAdapter yorumAdapter;
    List<YorumModel> yorumModelList;

    //PUAN VERME
    RatingBar puan;
    Button yildizBtn;
    Float toplampuan, sayi;
    Integer toplamkisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayfa_detay);

        tvisim = findViewById(R.id.tvIsim);
        tvucret = findViewById(R.id.tvucret);
        tvsure = findViewById(R.id.tvsure);
        tvbilgi = findViewById(R.id.tvbilgi);
        fotograf = findViewById(R.id.fotograf);
        yorumEt = findViewById(R.id.yorumEt);
        gonderButon = findViewById(R.id.gonderButon);
        rvYorum =findViewById(R.id.rvYorum);
        tvyildizSayisi = findViewById(R.id.tvyildizSayisi);
        puan = findViewById(R.id.puan);
        yildizBtn = findViewById(R.id.yildizBtn);
        
        String isim = getIntent().getStringExtra("isim");
        String ucret = getIntent().getStringExtra("ucret");
        String sure = getIntent().getStringExtra("sure");
        String bilgi = getIntent().getStringExtra("bilgi");
        String img = getIntent().getStringExtra("fotograf");
        String id = getIntent().getStringExtra("id");
        String ortalama = getIntent().getStringExtra("ortalama");
        sayi = getIntent().getFloatExtra("sayi", 0.0f);
        toplampuan = getIntent().getFloatExtra("toplampuan", 0.0f);
        toplamkisi = getIntent().getIntExtra("toplamkisi", 0);

        tvisim.setText(isim);
        tvucret.setText("Ücret: "+ucret);
        tvsure.setText(sure);
        tvbilgi.setText(bilgi);
        tvyildizSayisi.setText(ortalama);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Glide.with(fotograf.getContext())
                .load(img)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(fotograf);

        //YORUM YAPMA
        gonderButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yorumEt.getText().toString().equals("")){
                    Toast.makeText(SayfaDetay.this, "Boş yorum gönderemezsiniz!", Toast.LENGTH_LONG).show();
                }
                else{
                    yorumKaydet(id);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(yorumEt.getWindowToken(), 0);
            }
        });

        Id = getIntent().getExtras().getString("id");
        yorumOku();

        //PUAN VERME
        yildizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(puan.getRating());
                float f = Float.parseFloat(s);

                toplampuan += f;
                toplamkisi += 1;
                sayi = toplampuan / toplamkisi;

                Toast.makeText(getApplicationContext(), "Ortalama: "+sayi.toString(), Toast.LENGTH_SHORT).show();

                puanKaydet();
            }
        });
    }

    private void puanKaydet() {
        DatabaseReference ref = firebaseDatabase.getReference("Kategoriler").child(Id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                KategoriModel kategoriModel = new KategoriModel(toplamkisi, toplampuan, sayi);

                FirebaseDatabase.getInstance().getReference("Puanlar").child(Id).setValue(kategoriModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SayfaDetay.this, "HATA!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void yorumKaydet(String id) {
        String yorum = yorumEt.getText().toString();

        DatabaseReference ref = firebaseDatabase.getReference("Kullanıcılar").child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KullaniciModel kullaniciModel = snapshot.getValue(KullaniciModel.class);

                YorumModel yorumModel = new YorumModel(kullaniciModel.isim, yorum);
                FirebaseDatabase.getInstance().getReference("Yorumlar")
                        .child(id).push().setValue(yorumModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SayfaDetay.this, "Yorum gönderildi", Toast.LENGTH_SHORT).show();
                        yorumEt.setText("");
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SayfaDetay.this, "HATA!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void yorumOku(){
        rvYorum.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Yorumlar").child(Id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yorumModelList = new ArrayList<>();

                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    YorumModel yorumModel = snap.getValue(YorumModel.class);
                    yorumModelList.add(yorumModel);
                }
                yorumAdapter = new YorumAdapter(getApplicationContext(), yorumModelList);
                rvYorum.setAdapter(yorumAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SayfaDetay.this, "HATA!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
