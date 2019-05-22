package com.example.computer.moovsound.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.computer.moovsound.Adapter.AllAlbumAdapter;
import com.example.computer.moovsound.Model.Album;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllAlbum;
    Toolbar toolbarAllAlbum;
    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_danh_sach_tat_ca_album );
        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<List<Album>> callBack = dataService.GetAllalbum ();
        callBack.enqueue ( new Callback<List<Album>> ( ) {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayListAlbum = (ArrayList<Album>) response.body ();
                allAlbumAdapter = new AllAlbumAdapter ( DanhSachTatCaAlbumActivity.this, arrayListAlbum );
                recyclerViewAllAlbum.setLayoutManager ( new GridLayoutManager ( DanhSachTatCaAlbumActivity.this, 2 ) );
                recyclerViewAllAlbum.setAdapter ( allAlbumAdapter );
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        } );
    }

    private void Init() {
        setSupportActionBar ( toolbarAllAlbum );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        getSupportActionBar ().setTitle ( "Tất Cả Các Album" );
        toolbarAllAlbum.setTitleTextColor ( Color.WHITE );
        toolbarAllAlbum.setBackground ( getDrawable ( R.drawable.custom_background_banner ) );
        toolbarAllAlbum.setNavigationOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void AnhXa() {
        recyclerViewAllAlbum = findViewById ( R.id.recyclerview_tatcaalbum );
        toolbarAllAlbum = findViewById ( R.id.toolbartatcaalbum );
    }
}
