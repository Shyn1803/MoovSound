package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer.moovsound.Activity.PlayNhacActivity;
import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public TimKiemAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_baihat_line,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtcasi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat,txtcasi;
        ImageView imgbaihat,imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtcasi = itemView.findViewById(R.id.textviewsearchcasi);
            imgbaihat = itemView.findViewById(R.id.imageviewsearchbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewsearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    IDataService dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotthich("1",mangbaihat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Successed!")){
                                Toast.makeText(context,"Đã Thích",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context,"Lỗi!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}

