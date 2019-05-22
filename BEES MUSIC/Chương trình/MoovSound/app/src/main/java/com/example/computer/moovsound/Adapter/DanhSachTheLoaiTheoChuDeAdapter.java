package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.computer.moovsound.Activity.DanhSachBaiHatActivity;
import com.example.computer.moovsound.Model.TheLoai;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> arrayListTheLoai;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> arrayListTheLoai) {
        this.context = context;
        this.arrayListTheLoai = arrayListTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.theloaitheochude_line, viewGroup, false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TheLoai theLoai = arrayListTheLoai.get ( position );
        Picasso.with ( context ).load ( theLoai.getHinhTheLoai () ).into ( viewHolder.imgHinhNen );
        viewHolder.txtTenTheLoai.setText ( theLoai.getTenTheLoai () );
    }

    @Override
    public int getItemCount() {
        return arrayListTheLoai.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhNen;
        TextView txtTenTheLoai;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            AnhXa();
            itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, DanhSachBaiHatActivity.class );
                    intent.putExtra ( "idtheloai", arrayListTheLoai.get ( getPosition () ) );
                    context.startActivity ( intent );
                }
            } );
        }

        private void AnhXa() {
            imgHinhNen = itemView.findViewById ( R.id.imagev_theloaitheochude );
            txtTenTheLoai = itemView.findViewById ( R.id.txt_tentheloaitheochude );
        }
    }
}
