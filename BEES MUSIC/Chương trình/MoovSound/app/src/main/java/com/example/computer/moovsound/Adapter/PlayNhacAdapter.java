package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    //gắn layout vào item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.play_danhsachcacbaihat_line, viewGroup, false);
        return new ViewHolder ( view ); //truyền view vào để ánh xạ sau.
    }

    //gắn dữ liệu
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BaiHat baiHat =baiHatArrayList.get ( position );
        viewHolder.txt_tenbaihat_playdanhsachbaihatline.setText ( baiHat.getTenBaiHat () );
        viewHolder.txt_tencasi_playdanhsachbaihatline.setText ( baiHat.getCaSi () );
        viewHolder.txt_playdanhsach_index.setText ( position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_playdanhsach_index, txt_tenbaihat_playdanhsachbaihatline, txt_tencasi_playdanhsachbaihatline;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            AnhXa();
        }

        private void AnhXa() {
            txt_playdanhsach_index = itemView.findViewById ( R.id.txt_playdanhsach_index );
            txt_tenbaihat_playdanhsachbaihatline = itemView.findViewById ( R.id.txt_tenbaihat_playdanhsachbaihatline );
            txt_tencasi_playdanhsachbaihatline = itemView.findViewById ( R.id.txt_tencasi_playdanhsachbaihatline );
        }
    }
}
