package com.example.computer.moovsound.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.computer.moovsound.Activity.DanhSachBaiHatActivity;
import com.example.computer.moovsound.Activity.DanhSachTatCaChuDeActivity;
import com.example.computer.moovsound.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.computer.moovsound.Model.ChuDe;
import com.example.computer.moovsound.Model.ChuDeVaTheLoaiTrongNgay;
import com.example.computer.moovsound.Model.TheLoai;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuDeVaTheLoaiToDayFragment extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThemChuDeTheLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_chude_theloai_today, container, false);
        AnhXa();
        txtXemThemChuDeTheLoai.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity (),DanhSachTatCaChuDeActivity.class );
                startActivity ( intent );
            }
        } );
        GetData();
        return view;
    }

    private void AnhXa() {
        horizontalScrollView = view.findViewById ( R.id.horizontalscrollview_chude_theloai );
        txtXemThemChuDeTheLoai = view.findViewById ( R.id.txt_xemthem_chude_theloai );
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<ChuDeVaTheLoaiTrongNgay> callBack = dataService.GetCategoryMusic ();
        callBack.enqueue ( new Callback<ChuDeVaTheLoaiTrongNgay> ( ) {
            @Override
            public void onResponse(Call<ChuDeVaTheLoaiTrongNgay> call, Response<ChuDeVaTheLoaiTrongNgay> response) {
                ChuDeVaTheLoaiTrongNgay chuDeVaTheLoaiTrongNgay = response.body ();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<> (  );
                chuDeArrayList.addAll ( chuDeVaTheLoaiTrongNgay.getChude () );

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<> (  );
                theLoaiArrayList.addAll ( chuDeVaTheLoaiTrongNgay.getTheloai () );

                LinearLayout linearLayout = new LinearLayout ( getActivity () );
                linearLayout.setOrientation ( LinearLayout.HORIZONTAL );

                //set lại kích thước trước khi đưa vào LinearLayout tránh hình ảnh quá to tràn kích thước của LinearLayout
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams ( 580, 250 );
                //dãn cách với những phần xung quanh
                layoutParams.setMargins ( 10, 20, 10, 30);

                //Load với Chủ Đề
                for (int i = 0; i < chuDeArrayList.size (); i++) {
                    CardView cardView = new CardView ( getActivity () );
                    cardView.setRadius ( 10 );
                    final ImageView imageView = new ImageView ( getActivity () );
                    imageView.setScaleType ( ImageView.ScaleType.FIT_XY );
                    if (chuDeArrayList.get ( i ).getHinhChuDe () != null) {
                        Picasso.with ( getActivity () ).load ( chuDeArrayList.get ( i ).getHinhChuDe () ).into ( imageView );
                    }
                    cardView.setLayoutParams ( layoutParams );
                    cardView.addView ( imageView );
                    //đưa vào Linear để cho nó hiển thị lên
                    linearLayout.addView ( cardView );
                    final int finalI = i;
                    imageView.setOnClickListener ( new View.OnClickListener ( ) {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent ( getActivity (), DanhSachTheLoaiTheoChuDeActivity.class );
                            intent.putExtra ( "chudetatca", chuDeArrayList.get ( finalI ) );
                            startActivity ( intent );
                        }
                    } );
                }

                //Load với Thể Loại
                for (int j = 0; j < theLoaiArrayList.size (); j++) {
                    CardView cardView = new CardView ( getActivity () );
                    cardView.setRadius ( 10 );
                    ImageView imageView = new ImageView ( getActivity () );
                    imageView.setScaleType ( ImageView.ScaleType.FIT_XY );
                    if (theLoaiArrayList.get ( j ).getHinhTheLoai () != null) {
                        Picasso.with ( getActivity () ).load ( theLoaiArrayList.get ( j ).getHinhTheLoai () ).into ( imageView );
                    }
                    cardView.setLayoutParams ( layoutParams );
                    cardView.addView ( imageView );
                    //đưa vào Linear để cho nó hiển thị lên
                    linearLayout.addView ( cardView );
                    final int finalJ = j;
                    imageView.setOnClickListener ( new View.OnClickListener ( ) {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent ( getActivity (), DanhSachBaiHatActivity.class );
                            intent.putExtra ( "idtheloai", theLoaiArrayList.get ( finalJ ) );
                            startActivity ( intent );
                        }
                    } );
                }

                //đưa vào Horizontal (layout chude_theloai_today)
                horizontalScrollView.addView ( linearLayout );
            }

            @Override
            public void onFailure(Call<ChuDeVaTheLoaiTrongNgay> call, Throwable t) {

            }
        } );
    }
}
