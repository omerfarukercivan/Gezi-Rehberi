package com.example.rehberuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class GirisYap extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        mAuth = FirebaseAuth.getInstance();
        Button btnGiris = findViewById(R.id.btnGiris);
        TextView uyeOl = findViewById(R.id.uyeOl);

        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGiris();
            }
        });

        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uyeOl();
            }
        });
    }

    private void btnGiris() {
        EditText emailET = findViewById(R.id.emailET);
        EditText sifreET = findViewById(R.id.sifreET);

        String email = emailET.getText().toString();
        String sifre = sifreET.getText().toString();

        if (email.isEmpty()){
            emailET.setError("Email bölümü boş bırakılamaz");
            emailET.requestFocus();
            return;
        }

        if (sifre.isEmpty()){
            sifreET.setError("Şifre bölümü boş bırakılamaz");
            sifreET.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    showAnasayfa();
                }
                else{
                    Toast.makeText(GirisYap.this, "Giriş başarısız", Toast.LENGTH_LONG).show();
                }
            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(sifreET.getWindowToken(), 0);
    }

    private void showAnasayfa() {
        Intent intent = new Intent(this, Anasayfa.class);
        startActivity(intent);
        finish();
    }

    private void uyeOl() {
        Intent intent = new Intent(this, UyeOl.class);
        startActivity(intent);
        finish();
    }
}