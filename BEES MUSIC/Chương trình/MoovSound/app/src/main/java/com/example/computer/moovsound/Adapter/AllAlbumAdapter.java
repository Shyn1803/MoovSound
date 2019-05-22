package com.example.computer.moovsound.Adapter;

import android.content.ClipData;
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
import com.example.computer.moovsound.Model.Album;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> arrayListAllAlbum;

    public AllAlbumAdapter(Context context, ArrayList<Album> arrayListAllAlbum) {
        this.context = context;
        this.arrayListAllAlbum = arrayListAllAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.allalbum_line, viewGroup, false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Album album = arrayListAllAlbum.get ( position );
        Picasso.with ( context ).load ( album.getHinhAlbum () ).into ( viewHolder.imgAllAlbum );
        viewHolder.txtAllAlbum.setText ( album.getTenAlbum () );
    }

    @Override
    public int getItemCount() {
        return arrayListAllAlbum.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAllAlbum;
        TextView txtAllAlbum;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            AnhXa();
            itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, DanhSachBaiHatActivity.class );
                    intent.putExtra ( "album", arrayListAllAlbum.get ( getPosition () ) );
                    context.startActivity ( intent );

                }
            } );
        }

        private void AnhXa() {
            imgAllAlbum = itemView.findViewById ( R.id.imagev_allalbum_line );
            txtAllAlbum = itemView.findViewById ( R.id.txtx_tenalbum_line );
        }
    }
}
