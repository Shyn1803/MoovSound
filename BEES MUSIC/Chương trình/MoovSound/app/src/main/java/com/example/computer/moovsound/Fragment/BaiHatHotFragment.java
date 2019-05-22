package com.example.computer.moovsound.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.computer.moovsound.Adapter.BaiHatHotAdapter;
import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotFragment extends Fragment {
    View view; //gắn layout cho fragment và ánh xạ các biến
    RecyclerView recyclerView;
    BaiHatHotAdapter baiHatHotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_baihat_yeuthichnhat, container, false);
        AnhXa();
        GetData();
        return view;
    }

    private void AnhXa() {
        recyclerView = view.findViewById ( R.id.recyclerview_baihathot );
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<List<BaiHat>> callBack = dataService.GetBaiHatHot ();
        callBack.enqueue ( new Callback<List<BaiHat>> ( ) {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrayListBaiHat = (ArrayList<BaiHat>) response.body ();
                //Khi nào arrayAlbum có dữ liệu rồi thì thực hiện việc đưa dữ liệu vào Context
                baiHatHotAdapter = new BaiHatHotAdapter (getActivity (), arrayListBaiHat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager ( getActivity () );
                linearLayoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
                recyclerView.setLayoutManager ( linearLayoutManager );
                recyclerView.setAdapter ( baiHatHotAdapter );

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        } );
    }
}
