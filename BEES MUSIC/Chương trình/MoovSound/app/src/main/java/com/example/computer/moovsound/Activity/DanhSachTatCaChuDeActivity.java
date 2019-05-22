package com.example.computer.moovsound.Activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.computer.moovsound.Adapter.DanhSachTatCaChuDeAdapter;
import com.example.computer.moovsound.Model.ChuDe;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbartatcachude;
    DanhSachTatCaChuDeAdapter danhSachTatCaChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_danh_sach_tat_ca_chu_de );
        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<List<ChuDe>> callBack = dataService.GetAllChuDe ();
        callBack.enqueue ( new Callback<List<ChuDe>> ( ) {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> arrayListChuDe = (ArrayList<ChuDe>) response.body ();
                danhSachTatCaChuDeAdapter = new DanhSachTatCaChuDeAdapter ( DanhSachTatCaChuDeActivity.this, arrayListChuDe );
                recyclerView.setLayoutManager ( new GridLayoutManager ( DanhSachTatCaChuDeActivity.this, 1 ) );
                recyclerView.setAdapter ( danhSachTatCaChuDeAdapter );
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        } );
    }

    private void Init() {
        setSupportActionBar ( toolbartatcachude );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        getSupportActionBar ().setTitle ( "Tất Cả Chủ Đề" );
        toolbartatcachude.setTitleTextColor ( Color.WHITE );
        toolbartatcachude.setBackground ( getDrawable ( R.drawable.custom_background_banner ) );
        //toolbartatcachude.setBackground ( getResources ().getDrawable ( R.drawable.custom_background_banner ) );
        toolbartatcachude.setNavigationOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void AnhXa() {
        recyclerView = findViewById ( R.id.recyclerview_tatcachude );
        toolbartatcachude = findViewById ( R.id.toolbartatcachude );
    }
}
