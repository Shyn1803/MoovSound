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
import com.example.computer.moovsound.Model.Playlist;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacPlaylistAdapter extends RecyclerView.Adapter<DanhSachCacPlaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> arrayListPlaylist;

    public DanhSachCacPlaylistAdapter(Context context, ArrayList<Playlist> arrayListPlaylist) {
        this.context = context;
        this.arrayListPlaylist = arrayListPlaylist;
    }

    //Khởi tạo và gắn layout cho phần adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danh_sach_cac_playlist_line, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Playlist playlist = arrayListPlaylist.get ( position );
        Picasso.with ( context ).load ( playlist.getHinhIcon () ).into ( viewHolder.imgIcon );
        viewHolder.txtTenPlaylist.setText ( playlist.getTen () );
    }

    @Override
    public int getItemCount() {
        return arrayListPlaylist.size ();
    }

    //Khai báo các giá trị ánh xạ để có thể tương tác
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTenPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            AnhXa();
            itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, DanhSachBaiHatActivity.class );
                    intent.putExtra ( "itemplaylist", arrayListPlaylist.get ( getPosition () ) );
                    context.startActivity ( intent );
                }
            } );
            
        }

        private void AnhXa() {
            imgIcon = itemView.findViewById ( R.id.imagev_danhsachcacplaylist_line );
            txtTenPlaylist = itemView.findViewById ( R.id.txtx_tenplaylist );
        }
    }
}
