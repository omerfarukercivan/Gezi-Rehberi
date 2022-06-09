package com.example.rehberuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UyeOl extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);

        mAuth = FirebaseAuth.getInstance();

        Button btnUyeOl = findViewById(R.id.btnUyeOl);
        TextView girisYap = findViewById(R.id.girisYap);

        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        btnUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUyeOl();
            }
        });

        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girisYap();
            }
        });
    }

    private void btnUyeOl() {
        EditText isimET = findViewById(R.id.isimET);
        EditText emailET = findViewById(R.id.emailET);
        EditText sifreET = findViewById(R.id.sifreET);

        String isim = isimET.getText().toString();
        String email = emailET.getText().toString();
        String sifre = sifreET.getText().toString();

        if (isim.isEmpty()){
            isimET.setError("İsim bölümü boş bırakılamaz");
            isimET.requestFocus();
            return;
        }

        if (email.isEmpty()){
            emailET.setError("Email bölümü boş bırakılamaz");
            emailET.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailET.setError("Lütfen geçerli bir email giriniz");
            emailET.requestFocus();
            return;
        }

        if (sifre.isEmpty()){
            sifreET.setError("Şifre bölümü boş bırakılamaz");
            sifreET.requestFocus();
            return;
        }

        if (sifre.length() < 6){
            sifreET.setError("Şifre uzunluğu minimum 6 karakterden oluşmalıdır");
            sifreET.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    KullaniciModel kullanici = new KullaniciModel(isim, email);

                    FirebaseDatabase.getInstance().getReference("Kullanıcılar")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(kullanici).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            showMainActivity();
                        }
                    });
                }
                else{
                    Toast.makeText(UyeOl.this, "Kayıt başarısız", Toast.LENGTH_LONG).show();
                }
            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(sifreET.getWindowToken(), 0);
    }

    private void showMainActivity() {
        Intent intent = new Intent(this, Anasayfa.class);
        startActivity(intent);
        finish();
    }

    private void girisYap() {
        Intent intent = new Intent(this, GirisYap.class);
        startActivity(intent);
        finish();
    }
}