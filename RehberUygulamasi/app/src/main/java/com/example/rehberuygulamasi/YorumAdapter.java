package com.example.rehberuygulamasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

public class YorumAdapter extends RecyclerView.Adapter<YorumAdapter.ViewHolder>{

    private Context mContext;
    private List<YorumModel> mComment;

    private FirebaseUser firebaseUser;

    public YorumAdapter(Context mContext, List<YorumModel> mComment) {
        this.mContext = mContext;
        this.mComment = mComment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_yorum, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.isimYorum.setText(mComment.get(i).getIsim());
        viewHolder.yorumAlani.setText(mComment.get(i).getYorum());
    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView isimYorum, yorumAlani;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            isimYorum = itemView.findViewById(R.id.isimYorum);
            yorumAlani = itemView.findViewById(R.id.yorumAlani);
        }
    }
}