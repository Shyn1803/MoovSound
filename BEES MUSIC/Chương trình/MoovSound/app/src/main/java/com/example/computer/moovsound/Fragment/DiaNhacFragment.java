package com.example.computer.moovsound.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.computer.moovsound.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiaNhacFragment extends Fragment {
    Context context;
    View view;
    ImageView imageView_dianhac;
//    CircleImageView circleImageView_dianhac;
//    ObjectAnimator objectAnimator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);
        imageView_dianhac = view.findViewById ( R.id.imagev_dianhac );
//        circleImageView_dianhac = view.findViewById ( R.id.circleimagev_dianhac );
//        objectAnimator = ObjectAnimator.ofFloat ( circleImageView_dianhac, "rotation", 0f, 360f );
//        objectAnimator.setDuration ( 10000 );
//        objectAnimator.setRepeatCount (ValueAnimator.INFINITE);
//        objectAnimator.setRepeatMode ( ValueAnimator.RESTART );
//        //tránh tình trạng sau khi chạy xong 1 vòng thì bị khựng lại 1 chút
//        objectAnimator.setInterpolator ( new LinearInterpolator (  ) );

        return view;
    }

    public void PlayNhac(String hinhBaiHat) {
        Picasso.with(context).load(hinhBaiHat).into(imageView_dianhac);
    }
}
