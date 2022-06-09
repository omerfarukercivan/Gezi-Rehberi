package com.example.rehberuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Kategoriler extends AppCompatActivity {

    Button btnParklar, btnKutuphaneler, btnTarihiYerler, btnOteller, btnMarketler, btnIbadetYerleri, btnOtoparlklar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriler);

        btnParklar = findViewById(R.id.btnParklar);
        btnKutuphaneler = findViewById(R.id.btnKutuphaneler);
        btnTarihiYerler = findViewById(R.id.btnTarihiYerler);
        btnOteller = findViewById(R.id.btnOteller);
        btnMarketler = findViewById(R.id.btnMarketler);
        btnIbadetYerleri = findViewById(R.id.btnIbadetYerleri);
        btnOtoparlklar = findViewById(R.id.btnOtoparklar);

        btnParklar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnParklar();
            }
        });

        btnKutuphaneler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKutuphaneler();
            }
        });

        btnTarihiYerler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTarihiYerler();
            }
        });

        btnOteller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOteller();
            }
        });

        btnMarketler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMarketler();
            }
        });

        btnIbadetYerleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIbadetYerleri();
            }
        });

        btnOtoparlklar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOtoparlklar();
            }
        });
    }

    private void btnParklar() {
        Intent intent = new Intent(this, Parklar.class);
        startActivity(intent);
        //finish();
    }

    private void btnKutuphaneler() {
        Intent intent = new Intent(this, Kutuphaneler.class);
        startActivity(intent);
        //finish();
    }

    private void btnTarihiYerler() {
        Intent intent = new Intent(this, TarihiYerler.class);
        startActivity(intent);
        //finish();
    }

    private void btnOteller() {
        Intent intent = new Intent(this, Oteller.class);
        startActivity(intent);
        //finish();
    }

    private void btnMarketler() {
        Intent intent = new Intent(this, Marketler.class);
        startActivity(intent);
        //finish();
    }

    private void btnIbadetYerleri() {
        Intent intent = new Intent(this, IbadetYerleri.class);
        startActivity(intent);
        //finish();
    }

    private void btnOtoparlklar() {
        Intent intent = new Intent(this, Otoparklar.class);
        startActivity(intent);
        //finish();
    }
}