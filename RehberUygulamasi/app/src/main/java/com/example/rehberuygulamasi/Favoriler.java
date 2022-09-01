package com.example.rehberuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Favoriler extends AppCompatActivity {

    Button btnParklar, btnKutuphaneler, btnTarihiYerler, btnOteller, btnMarketler, btnIbadetYerleri, btnOtoparlklar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriler);

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
        Intent intent = new Intent(this, FavoriParklar.class);
        startActivity(intent);
    }

    private void btnKutuphaneler() {
        Intent intent = new Intent(this, FavoriKutuphaneler.class);
        startActivity(intent);
    }

    private void btnTarihiYerler() {
        Intent intent = new Intent(this, FavoriTarihiYerler.class);
        startActivity(intent);
    }

    private void btnOteller() {
        Intent intent = new Intent(this, FavoriOteller.class);
        startActivity(intent);
    }

    private void btnMarketler() {
        Intent intent = new Intent(this, FavoriMarketler.class);
        startActivity(intent);
    }

    private void btnIbadetYerleri() {
        Intent intent = new Intent(this, FavoriIbadetYerleri.class);
        startActivity(intent);
    }

    private void btnOtoparlklar() {
        Intent intent = new Intent(this, FavoriOtoparklar.class);
        startActivity(intent);
    }
}
