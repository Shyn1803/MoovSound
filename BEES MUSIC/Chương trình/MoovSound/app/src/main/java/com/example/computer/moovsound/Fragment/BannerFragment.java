package com.example.computer.moovsound.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.computer.moovsound.Adapter.BannerAdapter;
import com.example.computer.moovsound.Model.Banner;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerFragment extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;

    Runnable runnable;
    Handler handler;

    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_banner, container, false);
        AnhXa();
        GetData();
        return view;
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        retrofit2.Call<List<Banner>> callBack = dataService.GetDataBanner ();
        callBack.enqueue ( new Callback<List<Banner>> ( ) {
            @Override
            public void onResponse(retrofit2.Call<List<Banner>> call, Response<List<Banner>> response) {
                ArrayList<Banner> banners = (ArrayList<Banner>) response.body ();
                bannerAdapter = new BannerAdapter ( getActivity (), banners );
                viewPager.setAdapter ( bannerAdapter );
                circleIndicator.setViewPager ( viewPager );

                handler = new Handler ( );
                runnable = new Runnable ( ) {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem ();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter ().getCount ()) {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem ( currentItem, true );
                        handler.postDelayed ( runnable, 4000 );
                    }
                };
                handler.postDelayed ( runnable, 4000 );
            }

            @Override
            public void onFailure(retrofit2.Call<List<Banner>> call, Throwable t) {

            }
        } );
    }

    private void AnhXa() {
        viewPager = view.findViewById ( R.id.viewpager );
        circleIndicator = view.findViewById ( R.id.indicator_banner );
    }
}
