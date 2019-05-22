package com.example.computer.moovsound.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.StrictMode;
import android.support.design.button.MaterialButton;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.computer.moovsound.Adapter.DanhSachBaiHatAdapter;
import com.example.computer.moovsound.Model.Album;
import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.Model.Banner;
import com.example.computer.moovsound.Model.Playlist;
import com.example.computer.moovsound.Model.TheLoai;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    Banner banner;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    //MaterialButton materialButton;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhSachCaKhuc;
    ArrayList<BaiHat> arrayListBaiHat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;

    Playlist playlist;
    TheLoai theloai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_danh_sach_bai_hat );
        //2 dòng kiểm tra tín hiệu mạng
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder (  ).permitAll ().build ();
        StrictMode.setThreadPolicy ( policy );
        AnhXa();
        DataIntent();
        Init();
        //Khác null và đã có dữ liệu
        if (banner != null && !banner.getTenBaiHat ().equals ( "" )) {
            setValueInView(banner.getTenBaiHat (), banner.getHinhBaiHat ());
            GetDataBanner(banner.getId ());
        }

        if (playlist != null && !playlist.getTen ().equals ( "" )) {
            setValueInView ( playlist.getTen (), playlist.getHinhIcon () );
            GetDataPlaylist ( playlist.getIdPlayList () );
        }

        if(theloai != null && !theloai.getTenTheLoai().equals(""))
        {
            setValueInView(theloai.getTenTheLoai(),theloai.getHinhTheLoai());
            GetDataTheLoai(theloai.getIdTheLoai());
        }

        if(album != null && !album.getTenAlbum ().equals(""))
        {
            setValueInView(album.getTenAlbum(),album.getHinhAlbum ());
            GetDataAlbum(album.getIdAlbum ());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        IDataService dataService = APIService.getService ();
        Call<List<BaiHat>> callBack = dataService.Getdanhsachbaihattheoalbum(idAlbum);
        callBack.enqueue ( new Callback<List<BaiHat>> ( ) {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaiHat = (ArrayList<BaiHat>) response.body ();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter ( DanhSachBaiHatActivity.this, arrayListBaiHat );
                recyclerViewDanhSachBaiHat.setLayoutManager ( new LinearLayoutManager ( DanhSachBaiHatActivity.this ) );
                recyclerViewDanhSachBaiHat.setAdapter ( danhSachBaiHatAdapter );
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        } );
    }

    private void GetDataTheLoai(String idTheLoai) {
        IDataService dataService = APIService.getService ();
        Call<List<BaiHat>> callBack = dataService.GetDanhsachbaihattheotheloai ( idTheLoai );
        callBack.enqueue ( new Callback<List<BaiHat>> ( ) {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaiHat = (ArrayList<BaiHat>) response.body ();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter ( DanhSachBaiHatActivity.this, arrayListBaiHat );
                recyclerViewDanhSachBaiHat.setLayoutManager ( new LinearLayoutManager ( DanhSachBaiHatActivity.this ) );
                recyclerViewDanhSachBaiHat.setAdapter ( danhSachBaiHatAdapter );
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        } );
    }

    private void GetDataPlaylist(String idPlayList) {
        IDataService dataService = APIService.getService ();
        Call<List<BaiHat>> callBack = dataService.GetDanhsachbaihattheoplaylist ( idPlayList );
        callBack.enqueue ( new Callback<List<BaiHat>> ( ) {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaiHat = (ArrayList<BaiHat>) response.body ();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter ( DanhSachBaiHatActivity.this, arrayListBaiHat );
                recyclerViewDanhSachBaiHat.setLayoutManager ( new LinearLayoutManager ( DanhSachBaiHatActivity.this ) );
                recyclerViewDanhSachBaiHat.setAdapter ( danhSachBaiHatAdapter );
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        } );

    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle ( ten );
        try {
            URL url = new URL ( hinh );
            Bitmap bitmap = BitmapFactory.decodeStream ( url.openConnection ().getInputStream () );
            BitmapDrawable bitmapDrawable = new BitmapDrawable ( getResources (), bitmap );
            collapsingToolbarLayout.setBackground ( bitmapDrawable );
        } catch (MalformedURLException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        Picasso.with ( this ).load ( hinh ).into ( imgDanhSachCaKhuc );
    }

    private void GetDataBanner(String idbanner) {
        IDataService dataService = APIService.getService ();
        Call<List<BaiHat>> callBack = dataService.GetDanhsachbaihattheoquangcao ( idbanner );
        callBack.enqueue ( new Callback<List<BaiHat>> ( ) {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaiHat = (ArrayList<BaiHat>) response.body ();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter ( DanhSachBaiHatActivity.this, arrayListBaiHat );
                recyclerViewDanhSachBaiHat.setLayoutManager ( new LinearLayoutManager ( DanhSachBaiHatActivity.this ) );
                recyclerViewDanhSachBaiHat.setAdapter ( danhSachBaiHatAdapter );
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        } );
    }

    private void Init() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        collapsingToolbarLayout.setExpandedTitleColor ( Color.WHITE );
        collapsingToolbarLayout.setCollapsedTitleTextColor ( Color.WHITE );
        //ban đầu nút float... ở chế độ false tới khi load xong dữ liệu
        floatingActionButton.setEnabled ( false );

    }

    private void AnhXa() {
        coordinatorLayout = findViewById ( R.id.coordinatordanhsachbaihat );
        collapsingToolbarLayout = findViewById ( R.id.collapsingtoolbar );
        toolbar = findViewById ( R.id.toolbardanhsachbaihat );
        recyclerViewDanhSachBaiHat = findViewById ( R.id.recyclerview_danhsachbaihat );
        floatingActionButton = findViewById ( R.id.floatingbutton );
        imgDanhSachCaKhuc = findViewById ( R.id.imagev_hinhdanhsachbaihat );
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra ( "banner" )) {
                banner = (Banner) intent.getSerializableExtra ( "banner" );
                Toast.makeText ( this, banner.getTenBaiHat (), Toast.LENGTH_SHORT ).show ();
            }
            if (intent.hasExtra ( "itemplaylist" )) {
                playlist = (Playlist) intent.getSerializableExtra ( "itemplaylist" );
            }
            if(intent.hasExtra("idtheloai")){
                theloai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    //xét sự kiện khi load hết dữ liệu ra cho các album,bài hát hot thì bắt sự kiện cho nút phát nhạc
    private void eventClick() {
        floatingActionButton.setEnabled ( true );
        floatingActionButton.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( DanhSachBaiHatActivity.this, PlayNhacActivity.class );
                //gửi dữ liệu các bài hát ở trong dánh sách hiện tại đi
                intent.putExtra ( "cacbaihathientai", arrayListBaiHat );
                startActivity ( intent );
            }
        } );
    }
}
