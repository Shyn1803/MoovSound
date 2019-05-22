package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.computer.moovsound.Activity.DanhSachBaiHatActivity;
import com.example.computer.moovsound.Activity.DanhSachTatCaAlbumActivity;
import com.example.computer.moovsound.Model.Album;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends  RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> arrayListAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayListAlbum) {
        this.context = context;
        this.arrayListAlbum = arrayListAlbum;
    }

    @NonNull
    @Override
    //dùng để gắn layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate( R.layout.album_line, viewGroup, false);
        return new ViewHolder ( view );
    }

    @Override
    //thực hiện việc gắn giá trị
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Album album = arrayListAlbum.get ( position );  //trả về đúng vị trí item mình cần
        //Lấy dữ liệu của item đó ra và gắn cho view
        viewHolder.txtTenAlbum.setText ( album.getTenAlbum () );
        viewHolder.txtCasiAlbum.setText ( album.getTenCaSiAlbum () );
        Picasso.with ( context ).load ( album.getHinhAlbum () ).into ( viewHolder.imgHinhAnhAlbum );
    }

    @Override
    //trả lại kích thước của mảng
    public int getItemCount() {
        return arrayListAlbum.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAnhAlbum;
        TextView txtTenAlbum,txtCasiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView);
            AnhXa();
            itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, DanhSachBaiHatActivity.class );
                    intent.putExtra ( "album", arrayListAlbum.get ( getPosition () ) );
                    context.startActivity ( intent );
                }
            } );
        }

        private void AnhXa() {
            imgHinhAnhAlbum = itemView.findViewById ( R.id.imagev_albumline);
            txtTenAlbum = itemView.findViewById ( R.id.txt_tenalbum );
            txtCasiAlbum = itemView.findViewById ( R.id.txt_tencasialbum );
        }
    }
}
