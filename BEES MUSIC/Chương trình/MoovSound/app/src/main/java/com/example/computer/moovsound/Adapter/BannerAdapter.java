package com.example.computer.moovsound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer.moovsound.Activity.DanhSachBaiHatActivity;
import com.example.computer.moovsound.Model.Banner;
import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Banner> arrayListBanner;
    public BannerAdapter(Context context, ArrayList<Banner> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size ();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate( R.layout.banner_line, container, false);

        ImageView imgBackgroundBanner = view.findViewById ( R.id.background_banner );
        ImageView imgSongBanner = view.findViewById ( R.id.imagev_banner );
        TextView txtTitleSongBanner = view.findViewById ( R.id.txt_title_banner );
        TextView txtNoiDung = view.findViewById ( R.id.description );

        Picasso.with ( context ).load (arrayListBanner.get ( position ).getHinhAnh ()).into ( imgBackgroundBanner );
        Picasso.with ( context ).load (arrayListBanner.get ( position ).getHinhBaiHat ()).into ( imgSongBanner );
        txtTitleSongBanner.setText ( arrayListBanner.get ( position ).getTenBaiHat () );
        txtNoiDung.setText ( arrayListBanner.get ( position ).getNoiDung () );

        //Bắt sự kiện khi click vào banner!!!
        view.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                //Toast.makeText ( context, "Đã click vào banner!!!", Toast.LENGTH_SHORT ).show ();
                //Chuyển dữ liệu qua màn hình danh sách các bài hát
                Intent intent = new Intent ( context, DanhSachBaiHatActivity.class );
                //cast lại ở Banner implements Serializable
                intent.putExtra ( "banner", arrayListBanner.get ( position ) );
                context.startActivity ( intent );
            }
        } );
        container.addView ( view );
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView ( (View) object );
    }
}
