package com.example.computer.moovsound.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.computer.moovsound.Fragment.TimKiemFragment;
import com.example.computer.moovsound.Fragment.TrangChuFragment;
import com.example.computer.moovsound.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //MaterialSearchView materialSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.navigation_drawer );
        AnhXa();

        ConfigureToolbar();
        navigationView.setCheckedItem(R.id.trangchu_id);
        if (navigationView.getMenu ().getItem ( 1 ).isChecked () == true) {
            getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TrangChuFragment () ).commit ();
            toolbar.setTitle ( "Trang Chủ" );
        }
        navigationView.setNavigationItemSelectedListener ( this );
//        materialSearchView.setOnSearchViewListener ( new MaterialSearchView.SearchViewListener ( ) {
//            @Override
//            public void onSearchViewShown() {
//                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TimKiemFragment () ).commit ();
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//
//            }
//        } );
//        timKiemFragment = new TimKiemFragment ();
//        if (materialSearchView != null) {
//
//            materialSearchView.setOnQueryTextListener ( new MaterialSearchView.OnQueryTextListener ( ) {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    timKiemFragment.SearchTuKhoaBaiHat ( query );
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            } );
//
//        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle ( this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer );
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState ();
    }

    private void ConfigureToolbar() {
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar ().setLogo(R.drawable.ic_menu_48px);    //Icon muốn hiện thị
//        getSupportActionBar ().setDisplayUseLogoEnabled(true);
        getSupportActionBar ( ).setHomeButtonEnabled ( true );
        toolbar.setTitleTextColor ( Color.WHITE );
    }

    private void AnhXa() {
        toolbar = findViewById ( R.id.toolbar );
        drawerLayout = findViewById ( R.id.drawer_layout );
        navigationView = findViewById ( R.id.navigation_view );
        //materialSearchView = findViewById ( R.id.search_view );
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//
//        MenuItem item = menu.findItem(R.id.iconsearch);
//        materialSearchView.setMenuItem(item);
//
//        return true;
////        getMenuInflater ().inflate ( R.menu.search_menu, menu );
////        MenuItem menuItem = menu.findItem ( R.id.iconsearch );
////        searchView = (SearchView) menuItem.getActionView ();
////        searchView.setMaxWidth ( Integer.MAX_VALUE );
////        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId ();
//        switch (id) {
//            case R.id.iconsearch:
//                materialSearchView.setOnSearchViewListener ( new MaterialSearchView.SearchViewListener ( ) {
//                    @Override
//                    public void onSearchViewShown() {
//                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TimKiemFragment () ).commit ();
//                    }
//
//                    @Override
//                    public void onSearchViewClosed() {
//
//                    }
//                } );
//                if (materialSearchView != null) {
//
//                    materialSearchView.setOnQueryTextListener ( new MaterialSearchView.OnQueryTextListener ( ) {
//                        @Override
//                        public boolean onQueryTextSubmit(String query) {
//                            timKiemFragment.SearchTuKhoaBaiHat ( query );
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onQueryTextChange(String newText) {
//                            return false;
//                        }
//                    } );
//
//                }
//                break;
//        }
//        return super.onOptionsItemSelected ( item );
//    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId ();
//        switch (id) {
//            case R.id.iconsearch:
//                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TimKiemFragment () ).commit ();
//                if (searchView != null) {
//                    searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener ( ) {
//                        @Override
//                        public boolean onQueryTextSubmit(String s) {
//                            //Log.d ( "AA", s );
//                            //getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TimKiemFragment () ).commit ();
//                            timKiemFragment.SearchTuKhoaBaiHat ( s );
//                            return true;
//                        }
//                        @Override
//                        public boolean onQueryTextChange(String s) {
//                            //getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TimKiemFragment () ).commit ();
//                            return true;
//                        }
//                    } );
//                }
//        }
//        return super.onOptionsItemSelected ( item );
//    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId ();
        switch(id)
        {
            case R.id.timkiem_id:
                Toast.makeText ( this, "Search", Toast.LENGTH_SHORT ).show ();
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TimKiemFragment () ).commit ();
                toolbar.setTitle ( "Tìm Kiếm" );
                break;
            case R.id.trangchu_id:
                Toast.makeText ( this, "Home", Toast.LENGTH_SHORT ).show ();
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new TrangChuFragment () ).commit ();
                toolbar.setTitle ( "Trang Chủ" );
                break;
            case R.id.playlist_id:
                Intent intent = new Intent ( this,DanhSachCacPlaylistActivity.class);
                startActivity ( intent );
                break;
            case R.id.album_id:
                Intent intent1 = new Intent ( this, DanhSachTatCaAlbumActivity.class );
                startActivity ( intent1 );
                break;
        }
        drawerLayout.closeDrawer ( GravityCompat.START );
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen (GravityCompat.START )){
            drawerLayout.closeDrawer ( GravityCompat.START );
        }else {
            super.onBackPressed ( );
        }
    }
}
