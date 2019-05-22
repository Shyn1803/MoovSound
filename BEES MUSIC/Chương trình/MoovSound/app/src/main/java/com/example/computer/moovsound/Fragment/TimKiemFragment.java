package com.example.computer.moovsound.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.computer.moovsound.Adapter.TimKiemAdapter;
import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemFragment extends Fragment {
    Toolbar toolbarsearchbaihat;
    RecyclerView recyclerViewsearchbaihat;
    TextView txtkhongcobaihat;
    View view;
    TimKiemAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem, container, false);
        AnhXa();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarsearchbaihat);
        toolbarsearchbaihat.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    private void AnhXa() {
        toolbarsearchbaihat = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearchbaihat = view.findViewById(R.id.recycleviewsearchbaihat);
        txtkhongcobaihat = view.findViewById(R.id.textviewkhongcodulieubaihat);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                //Log.d("AAA",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBaiHat(String query){
        IDataService dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetSearchbaihat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size() > 0){
                    searchBaiHatAdapter = new TimKiemAdapter (getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchbaihat.setAdapter(searchBaiHatAdapter);
                    txtkhongcobaihat.setVisibility(View.GONE);
                    recyclerViewsearchbaihat.setVisibility(View.VISIBLE);
                }
                else{
                    recyclerViewsearchbaihat.setVisibility(View.GONE);
                    txtkhongcobaihat.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
