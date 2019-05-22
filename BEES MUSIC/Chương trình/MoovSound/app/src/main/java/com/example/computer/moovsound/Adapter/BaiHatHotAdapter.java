package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer.moovsound.Activity.MainActivity;
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

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> arrayListBaiHat;
    Typeface typeface;
    MainActivity mainActivity;
    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    //dùng để gắn layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.baihathot_line, viewGroup, false);
        return new ViewHolder ( view );
    }

    @Override
    //thực hiện việc gắn giá trị
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BaiHat baiHat = arrayListBaiHat.get ( position );   //trả về đúng vị trí item mình cần
        //Lấy dữ liệu của item đó ra và gắn cho view
        viewHolder.txtTenBaiHat.setText ( baiHat.getTenBaiHat () );
        viewHolder.txtTenCaSi.setText ( baiHat.getCaSi () );
        Picasso.with ( context ).load ( baiHat.getHinhBaiHat () ).into ( viewHolder.imgHinhBaiHat );
    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenBaiHat, txtTenCaSi;
        ImageView imgHinhBaiHat, imgHeart;
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
            txtTenBaiHat = itemView.findViewById ( R.id.txt_tenbaihathot );
            txtTenCaSi = itemView.findViewById ( R.id.txt_casi_baihathot );
            imgHinhBaiHat = itemView.findViewById ( R.id.imagev_baihathot );
            imgHeart = itemView.findViewById ( R.id.imagev_heart );
        }
    }
}
