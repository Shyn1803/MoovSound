package com.example.computer.moovsound.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.computer.moovsound.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.computer.moovsound.Model.ChuDe;
import com.example.computer.moovsound.Model.TheLoai;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbartheloaitheochude;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_danh_sach_the_loai_theo_chu_de );
        GetIntent();
        AnhXa();
        Init();
        GetData();

    }

    private void GetData() {
        IDataService dataServiced = APIService.getService ();
        Call<List<TheLoai>> callBack = dataServiced.GetTheloaitheochude ( chuDe.getIdChuDe ( ) );
        callBack.enqueue ( new Callback<List<TheLoai>> ( ) {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arrayListTheLoai = (ArrayList<TheLoai>) response.body ();
                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter ( DanhSachTheLoaiTheoChuDeActivity.this, arrayListTheLoai );
                recyclerViewTheLoaiTheoChuDe.setLayoutManager ( new GridLayoutManager ( DanhSachTheLoaiTheoChuDeActivity.this, 2 ) );
                recyclerViewTheLoaiTheoChuDe.setAdapter ( danhSachTheLoaiTheoChuDeAdapter );
            }


            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        } );
    }

    private void AnhXa() {
        recyclerViewTheLoaiTheoChuDe = findViewById ( R.id.recyclerview_theloaitheochude );
        toolbartheloaitheochude = findViewById ( R.id.toolbartheloaitheochude );
    }

    private void Init() {
        setSupportActionBar ( toolbartheloaitheochude );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        getSupportActionBar ().setTitle ( chuDe.getTenChuDe ( ) );
        toolbartheloaitheochude.setTitleTextColor ( Color.WHITE );
        toolbartheloaitheochude.setBackground ( getDrawable ( R.drawable.custom_background_banner ) );
        toolbartheloaitheochude.setNavigationOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void GetIntent() {
        Intent intent = getIntent ();
        if (intent.hasExtra ( "chudetatca" )) {
            chuDe = (ChuDe) intent.getSerializableExtra ( "chudetatca" );
        }
    }
}
