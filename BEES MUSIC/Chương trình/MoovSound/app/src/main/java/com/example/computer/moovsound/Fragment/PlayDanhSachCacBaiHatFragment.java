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

import com.example.computer.moovsound.Activity.PlayNhacActivity;
import com.example.computer.moovsound.Adapter.PlayNhacAdapter;
import com.example.computer.moovsound.R;

public class PlayDanhSachCacBaiHatFragment extends Fragment {
    View view;
    RecyclerView recyclerView_playdanhsachbaihat;
    PlayNhacAdapter playNhacAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsachcacbaihat, container, false);
        recyclerView_playdanhsachbaihat = view.findViewById ( R.id.recyclerview_play_danhsach_cacbaihat );
        //if (PlayNhacActivity.mangbaihat.size () > 0) {
            playNhacAdapter = new PlayNhacAdapter ( getActivity (), PlayNhacActivity.mangbaihat );
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager ( getActivity () );
            //linearLayoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
            recyclerView_playdanhsachbaihat.setLayoutManager ( linearLayoutManager );
            recyclerView_playdanhsachbaihat.setAdapter ( playNhacAdapter );
        //}

        return view;
    }
}
