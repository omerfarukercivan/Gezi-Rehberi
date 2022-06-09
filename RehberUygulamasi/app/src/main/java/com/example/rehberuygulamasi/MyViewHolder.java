package com.example.rehberuygulamasi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tvisim, tvyildizSayisi;
    ImageView fotograf;
    View view;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        tvisim = itemView.findViewById(R.id.tvIsim);
        fotograf = itemView.findViewById(R.id.fotograf);
        tvyildizSayisi = itemView.findViewById(R.id.tvyildizSayisi);

        view = itemView;
    }
}
