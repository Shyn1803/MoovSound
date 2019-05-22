package com.example.computer.moovsound.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.computer.moovsound.Activity.DanhSachTatCaAlbumActivity;
import com.example.computer.moovsound.Adapter.AlbumAdapter;
import com.example.computer.moovsound.Model.Album;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumHotFragment extends Fragment {
    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtAlbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_albumhot, container, false);
        AnhXa();
        txtAlbum.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( getActivity (), DanhSachTatCaAlbumActivity.class );
                startActivity ( intent );
            }
        } );
        GetData();
        return view;
    }

    private void AnhXa() {
        recyclerViewAlbum = view.findViewById ( R.id.recyclerview_album );
        txtAlbum = view.findViewById ( R.id.txt_xemthem_albumhot );
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<List<Album>> callBack = dataService.GetAlbumHot ();
        callBack.enqueue ( new Callback<List<Album>> ( ) {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAlbum = (ArrayList<Album>) response.body ();
                //Khi nào arrayAlbum có dữ liệu rồi thì thực hiện việc đưa dữ liệu vào Context
                albumAdapter = new AlbumAdapter ( getActivity (), arrayAlbum );
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager ( getActivity () );
                linearLayoutManager.setOrientation ( LinearLayoutManager.HORIZONTAL );
                recyclerViewAlbum.setLayoutManager ( linearLayoutManager );
                recyclerViewAlbum.setAdapter ( albumAdapter );
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        } );
    }
}
