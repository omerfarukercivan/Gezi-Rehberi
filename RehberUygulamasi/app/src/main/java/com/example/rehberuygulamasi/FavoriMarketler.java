package com.example.rehberuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FavoriMarketler extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseRecyclerOptions<KategoriModel> options;
    FirebaseRecyclerAdapter<KategoriModel, MyViewHolder> adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori_marketler);

        Query query = FirebaseDatabase.getInstance().getReference("Kategoriler").child("Marketler")
                .orderByChild("puan")
                .startAt(4);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<KategoriModel>().setQuery(query, KategoriModel.class).build();

        adapter = new FirebaseRecyclerAdapter<KategoriModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull KategoriModel kategoriModel) {

                String isim = kategoriModel.getIsim();
                String ucret = kategoriModel.getUcret();
                String sure = kategoriModel.getSure();
                String bilgi = kategoriModel.getBilgi();
                String fotograf = kategoriModel.getFotograf();
                String id = kategoriModel.getId();
                String ortalama = kategoriModel.getOrtalama();
                Float toplampuan = kategoriModel.getToplampuan();
                Integer toplamkisi = kategoriModel.getToplamkisi();
                Float sayi = kategoriModel.getSayi();

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),SayfaDetay.class);

                        intent.putExtra("isim", isim);
                        intent.putExtra("sure", sure);
                        intent.putExtra("ucret", ucret);
                        intent.putExtra("bilgi", bilgi);
                        intent.putExtra("fotograf", fotograf);
                        intent.putExtra("id", id);
                        intent.putExtra("ortalama", ortalama);
                        intent.putExtra("toplamkisi", toplamkisi);
                        intent.putExtra("toplampuan", toplampuan);
                        intent.putExtra("sayi", sayi);

                        startActivity(intent);
                    }
                });

                holder.tvisim.setText(kategoriModel.isim);
                holder.tvyildizSayisi.setText(kategoriModel.ortalama);

                Glide.with(holder.fotograf.getContext())
                        .load(kategoriModel.getFotograf())
                        .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                        .into(holder.fotograf);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}