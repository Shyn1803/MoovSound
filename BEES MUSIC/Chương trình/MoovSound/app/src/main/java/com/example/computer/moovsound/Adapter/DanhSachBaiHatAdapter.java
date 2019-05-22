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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> arrayListBaiHat;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danhsachbaihat_line, viewGroup, false);

        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BaiHat baiHat = arrayListBaiHat.get ( position );
        viewHolder.txtTenBaiHat.setText ( baiHat.getTenBaiHat () );
        viewHolder.txtCaSi.setText ( baiHat.getCaSi () );
        viewHolder.txtIndex.setText ( position + 1 + "" );

    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBaiHat, txtCaSi;
        ImageView imgHeart;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            AnhXa();
            imgHeart.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    imgHeart.setImageResource ( R.drawable.iconloved );
                    IDataService dataService = APIService.getService ();
                    Call<String> callBack = dataService.UpdateLuotthich ( "1", arrayListBaiHat.get ( getPosition () ).getIdBaiHat () );
                    callBack.enqueue ( new Callback<String> ( ) {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body ();
                            if (ketqua.equals ( "Successed!" )) {
                                Toast.makeText ( context, "Đã thích", Toast.LENGTH_SHORT ).show ();
                            }else {
                                Toast.makeText ( context, "Lỗi", Toast.LENGTH_SHORT ).show ();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    } );
                    imgHeart.setEnabled ( false );
                }
            } );
            itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, PlayNhacActivity.class );
                    intent.putExtra ( "cakhuc", arrayListBaiHat.get ( getPosition () ) );
                    context.startActivity ( intent );
                }
            } );
        }

        private void AnhXa() {
            txtIndex = itemView.findViewById ( R.id.txt_danhsach_index );
            txtTenBaiHat = itemView.findViewById ( R.id.txt_tenbaihat_danhsachbaihatline );
            txtCaSi = itemView.findViewById ( R.id.txt_tencasi_danhsachbaihatline );
            imgHeart = itemView.findViewById ( R.id.imagev_heart_danhsachbaihatline );
        }
    }
}
