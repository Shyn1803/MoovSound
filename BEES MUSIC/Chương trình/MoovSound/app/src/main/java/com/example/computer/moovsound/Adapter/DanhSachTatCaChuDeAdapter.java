package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.computer.moovsound.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.computer.moovsound.Model.ChuDe;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTatCaChuDeAdapter extends RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.ViewHolder>{
    Context context;
    ArrayList<ChuDe> arrayListChuDe;

    public DanhSachTatCaChuDeAdapter(Context context, ArrayList<ChuDe> arrayListChuDe) {
        this.context = context;
        this.arrayListChuDe = arrayListChuDe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tatcachude_line, viewGroup, false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ChuDe chuDe = arrayListChuDe.get ( position );
        Picasso.with ( context ).load ( chuDe.getHinhChuDe () ).into ( viewHolder.imgChuDe );
    }

    @Override
    public int getItemCount() {
        return arrayListChuDe.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgChuDe;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            imgChuDe = itemView.findViewById(R.id.imagev_tatcachude_line);
            imgChuDe.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, DanhSachTheLoaiTheoChuDeActivity.class );
                    intent.putExtra ( "chudetatca", arrayListChuDe.get ( getPosition () ) );
                    context.startActivity ( intent );
                }
            } );
        }
    }
}
