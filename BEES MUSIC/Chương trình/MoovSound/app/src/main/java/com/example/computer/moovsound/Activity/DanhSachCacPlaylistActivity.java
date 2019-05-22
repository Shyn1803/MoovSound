package com.example.computer.moovsound.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.computer.moovsound.Adapter.DanhSachCacPlaylistAdapter;
import com.example.computer.moovsound.Model.Playlist;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCacPlaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachcacplaylist;
    DanhSachCacPlaylistAdapter danhSachCacPlaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_danh_sach_cac_playlist );
        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<List<Playlist>> callBack = dataService.GetDanhsachcacplaylist ();
        callBack.enqueue ( new Callback<List<Playlist>> ( ) {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayListPlaylist = (ArrayList<Playlist>) response.body ();
                danhSachCacPlaylistAdapter = new DanhSachCacPlaylistAdapter ( DanhSachCacPlaylistActivity.this, arrayListPlaylist );
                //sắp xếp theo nhiều cột nhiều dòng -- GridLayoutManager
                recyclerViewDanhsachcacplaylist.setLayoutManager ( new GridLayoutManager ( DanhSachCacPlaylistActivity.this, 2 ) );
                recyclerViewDanhsachcacplaylist.setAdapter ( danhSachCacPlaylistAdapter );

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        } );
    }

    private void Init() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        getSupportActionBar ().setTitle ( "Play List" );
        toolbar.setTitleTextColor ( Color.WHITE );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void AnhXa() {
        toolbar = findViewById ( R.id.toolbardanhsachcacplaylist );
        recyclerViewDanhsachcacplaylist = findViewById ( R.id.recyclerview_danhsachcacplaylist );
    }

}
