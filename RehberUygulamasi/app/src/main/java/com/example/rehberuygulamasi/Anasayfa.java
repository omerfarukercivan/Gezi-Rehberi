package com.example.rehberuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Anasayfa extends AppCompatActivity {

    TextView isimtv, emailtv;
    Button btnCikis, btnKategoriler, btnFavoriler;
    //selam ben ömer asdasd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null){
            Intent intent = new Intent(this, GirisYap.class);
            startActivity(intent);
            finish();
            return;
        }

        isimtv = findViewById(R.id.isimtv);
        emailtv = findViewById(R.id.emailtv);
        btnCikis = findViewById(R.id.btnCikis);
        btnKategoriler = findViewById(R.id.btnKategoriler);
        btnFavoriler = findViewById(R.id.btnFavoriler);

        btnKategoriler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKategoriler();
            }
        });

        btnFavoriler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFavoriler();
            }
        });

        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCikis();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Kullanıcılar").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KullaniciModel kullanici = snapshot.getValue(KullaniciModel.class);

                if (kullanici != null){
                    isimtv.setText("Hoşgeldiniz " + kullanici.isim);
                    emailtv.setText("Email: "+kullanici.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void btnKategoriler() {
        Intent intent = new Intent(this, Kategoriler.class);
        startActivity(intent);
    }

    private void btnFavoriler() {
        Intent intent = new Intent(this, Favoriler.class);
        startActivity(intent);
    }

    private void btnCikis() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, GirisYap.class);
        startActivity(intent);
        finish();
    }
}