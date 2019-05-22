package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.computer.moovsound.Model.Playlist;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super ( context, resource, objects );
    }

    class ViewHolder {
        TextView txtTenPlaylist;
        ImageView imgBackground, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext ());
            convertView = inflater.inflate( R.layout.playlist_line, null);
            viewHolder = new ViewHolder ();
            viewHolder.txtTenPlaylist = convertView.findViewById ( R.id.txt_TenPlaylist );
            viewHolder.imgPlaylist = convertView.findViewById ( R.id.imagev_playlist );
            viewHolder.imgBackground = convertView.findViewById( R.id.imagev_backgroundplaylist );
            convertView.setTag ( viewHolder );
        }else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        Playlist playlist = getItem ( position );
        Picasso.with ( getContext () ).load ( playlist.getHinhNen () ).into ( viewHolder.imgBackground );
        Picasso.with ( getContext () ).load ( playlist.getHinhIcon () ).into ( viewHolder.imgPlaylist );
        viewHolder.txtTenPlaylist.setText ( playlist.getTen () );
        return convertView;
    }
}
