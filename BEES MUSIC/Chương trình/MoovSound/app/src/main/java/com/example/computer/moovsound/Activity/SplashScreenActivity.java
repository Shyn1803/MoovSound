package com.example.computer.moovsound.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.computer.moovsound.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_screen );
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent (SplashScreenActivity.this,MainActivity.class));
//                finish();
//            }
//        },2500);
        Thread thread = new Thread ( new Runnable ( ) {
            @Override
            public void run() {
                try{
                    Thread.sleep ( 2500 );
                }catch (Exception e) {

                }finally {
                    Intent intent = new Intent ( SplashScreenActivity.this,MainActivity.class );
                    startActivity ( intent );
                }
            }
        } );
        thread.start ();
    }
}
