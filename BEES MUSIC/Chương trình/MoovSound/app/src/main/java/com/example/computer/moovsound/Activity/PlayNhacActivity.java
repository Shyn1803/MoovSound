package com.example.computer.moovsound.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.computer.moovsound.Adapter.ViewPagerPlaylistNhacAdapter;
import com.example.computer.moovsound.Fragment.DiaNhacFragment;
import com.example.computer.moovsound.Fragment.PlayDanhSachCacBaiHatFragment;
import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTimeStar, txtTotalTime;
    SeekBar skTime;

    ImageButton imageButton_shuffle, imageButton_previous,imageButton_play, imageButton_next, imageButton_repeat;
    ViewPager viewPager_playnhac;
    CircleIndicator circleIndicator_playnhac;

    DiaNhacFragment diaNhacFragment;
    PlayDanhSachCacBaiHatFragment playDanhSachCacBaiHatFragment;

    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    //mangbaihat chứa các bài hát hiện tại để đưa vào danh sách phát nhạc truyền
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistNhacAdapter viewPagerPlaylistNhacAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_play_nhac );
        //2 dòng kiểm tra tín hiệu mạng
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder (  ).permitAll ().build ();
//        StrictMode.setThreadPolicy ( policy );
        GetDataIntent();
        AnhXa();
        ConfigurationToolbar();
        Init();

        EventClick();

    }

    private void EventClick() {
        final Handler handler = new Handler (  );
        handler.postDelayed ( new Runnable ( ) {
            @Override
            public void run() {
                if (viewPagerPlaylistNhacAdapter.getItem ( 1 ) != null) {
                    if (mangbaihat.size () > 0) {
                        diaNhacFragment.PlayNhac ( mangbaihat.get ( position ).getHinhBaiHat () );
                        diaNhacFragment.getActivity ();
                        handler.removeCallbacks ( this );
                    }else {
                        handler.postDelayed ( this, 300 );  //nếu load lâu hoặc kg được thì gọi lại hàm
                    }
                }
            }
        }, 350 );
        imageButton_play.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying ()) {
                    mediaPlayer.pause ();
                    imageButton_play.setImageResource ( R.drawable.ic_play );
                }else {
                    mediaPlayer.start ();
                    imageButton_play.setImageResource ( R.drawable.ic_pause_50px );
                }
            }
        } );

        imageButton_repeat.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (!repeat) {
                    if (checkrandom) {
                        checkrandom = false;
                        imageButton_repeat.setImageResource ( R.drawable.ic_repeat_one_50px );
                        imageButton_shuffle.setImageResource ( R.drawable.ic_shuffle_48px );
                    }
                    imageButton_repeat.setImageResource ( R.drawable.ic_repeat_one_50px );
                    repeat = true;
                } else {
                    imageButton_repeat.setImageResource ( R.drawable.ic_repeat_50px );
                    repeat = false;
                }
            }
        } );

        imageButton_shuffle.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (!checkrandom) {
                    if (repeat) {
                        repeat = false;
                        imageButton_shuffle.setImageResource ( R.drawable.ic_shuffle_one );
                        imageButton_repeat.setImageResource ( R.drawable.ic_repeat_50px );
                    }
                    imageButton_shuffle.setImageResource ( R.drawable.ic_shuffle_one );
                    checkrandom = true;
                } else {
                    imageButton_shuffle.setImageResource ( R.drawable.ic_shuffle_48px );
                    checkrandom = false;
                }
            }
        } );

        skTime.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener ( ) {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo ( seekBar.getProgress () );
            }
        } );

        imageButton_next.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size () > 0) {
                    if (mediaPlayer.isPlaying () || mediaPlayer != null) {
                        mediaPlayer.stop ();
                        mediaPlayer.release ();
                        mediaPlayer = null;
                    }
                    if (position < mangbaihat.size ()) {
                        imageButton_play.setImageResource ( R.drawable.ic_pause_50px );

                        if (repeat) {
                            if (position == 0) {
                                position = mangbaihat.size ();
                            }
                            position -= 1;
                        }
                        if (checkrandom) {
                            Random random = new Random (  );
                            int index = random.nextInt(mangbaihat.size ());
                            if (index == position) {
                                position = index - 1;
                            }
                        }
                        position++;
                        if (position > mangbaihat.size () - 1) {
                            position = 0;
                        }
                        new PlayMp3 ().execute ( mangbaihat.get ( position ).getLinkBaiHat () );
                        diaNhacFragment.PlayNhac ( mangbaihat.get ( position ).getHinhBaiHat () );
                        getSupportActionBar ().setTitle ( mangbaihat.get ( position ).getTenBaiHat () );
                        Updatetime();
                    }
                }
                imageButton_previous.setClickable ( false );
                imageButton_next.setClickable ( false );
                Handler handler1 = new Handler (  );
                handler1.postDelayed ( new Runnable ( ) {
                    @Override
                    public void run() {
                        imageButton_previous.setClickable ( true );
                        imageButton_next.setClickable ( true );
                    }
                }, 1000 );
            }
        } );

        imageButton_previous.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size () > 0) {
                    if (mediaPlayer.isPlaying () || mediaPlayer != null) {
                        mediaPlayer.stop ();
                        mediaPlayer.release ();
                        mediaPlayer = null;
                    }
                    if (position < mangbaihat.size ()) {
                        imageButton_play.setImageResource ( R.drawable.ic_pause_50px );
                        position--;
                        if (position < 0) {
                            position = mangbaihat.size () - 1;
                        }

                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            Random random = new Random (  );
                            int index = random.nextInt(mangbaihat.size ());
                            position = index;
                            if (index == position) {
                                position = index - 1;
                            }

                        }
                        new PlayMp3 ().execute ( mangbaihat.get ( position ).getLinkBaiHat () );
                        diaNhacFragment.PlayNhac ( mangbaihat.get ( position ).getHinhBaiHat () );
                        getSupportActionBar ().setTitle ( mangbaihat.get ( position ).getTenBaiHat () );
                        Updatetime();
                    }
                }
                imageButton_previous.setClickable ( false );
                imageButton_next.setClickable ( false );
                Handler handler1 = new Handler (  );
                handler1.postDelayed ( new Runnable ( ) {
                    @Override
                    public void run() {
                        imageButton_previous.setClickable ( true );
                        imageButton_next.setClickable ( true );
                    }
                }, 1000 );
            }
        } );
    }

    private void Init() {
        diaNhacFragment = new DiaNhacFragment ();
        playDanhSachCacBaiHatFragment = new PlayDanhSachCacBaiHatFragment ();
        viewPagerPlaylistNhacAdapter = new ViewPagerPlaylistNhacAdapter ( getSupportFragmentManager () );
        viewPagerPlaylistNhacAdapter.AddFragment ( playDanhSachCacBaiHatFragment );
        viewPagerPlaylistNhacAdapter.AddFragment ( diaNhacFragment );
        viewPager_playnhac.setAdapter ( viewPagerPlaylistNhacAdapter );
        diaNhacFragment = (DiaNhacFragment) viewPagerPlaylistNhacAdapter.getItem ( 1 );
        if (mangbaihat.size () > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3 ().execute ( mangbaihat.get ( 0 ).getLinkBaiHat () );
            imageButton_play.setImageResource ( R.drawable.ic_pause_50px );

        }
    }

    private void GetDataIntent() {
        Intent intent = getIntent();
        mangbaihat.clear ();        //chắc chắn kg có dữ liệu trước khi add tránh lặp
        if (intent != null) {       //tránh lỗi khi biến intent không có dữ liệu
            if (intent.hasExtra ( "cakhuc" )) {
                BaiHat baiHat = intent.getParcelableExtra ( "cakhuc" );
                mangbaihat.add ( baiHat );
            }
            if (intent.hasExtra ( "cacbaihathientai" )) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra ( "cacbaihathientai" );
                mangbaihat = baiHatArrayList ;
            }
        }
    }

    private void ConfigurationToolbar() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                finish ();
                mediaPlayer.stop ();
                mangbaihat.clear ();
            }
        } );
        toolbar.setTitleTextColor ( Color.WHITE );
    }

    private void AnhXa() {
        toolbar = findViewById ( R.id.toolbarplaynhac );
        txtTimeStar = findViewById ( R.id.textviewtimestar );
        txtTotalTime = findViewById ( R.id.textviewtotaltime );
        skTime = findViewById ( R.id.seekbarplaynhac );
        imageButton_shuffle = findViewById ( R.id.imagebutton_shuffle );
        imageButton_previous = findViewById ( R.id.imagebutton_previous );
        imageButton_play = findViewById ( R.id.imagebutton_play );
        imageButton_next = findViewById ( R.id.imagebutton_next );
        imageButton_repeat = findViewById ( R.id.imagebutton_repeat );
        viewPager_playnhac = findViewById ( R.id.viewpager_playnhac );
        circleIndicator_playnhac = findViewById ( R.id.indicator_playnhac );
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {   //prameter link bài hát, void, result link bài hát

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }
        //doInBackground thực hiện xong thì sẽ trả dữ liệu về cho onPostExecute
        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute ( baihat );
            try {
                mediaPlayer = new MediaPlayer ();
                mediaPlayer.setAudioStreamType ( AudioManager.STREAM_MUSIC );   //Play ca khúc dưới dạng online
                mediaPlayer.setOnCompletionListener ( new MediaPlayer.OnCompletionListener ( ) {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop ();
                        mediaPlayer.reset ();
                    }
                } );
                mediaPlayer.setDataSource ( baihat );
                mediaPlayer.prepare ();
            } catch (IOException e) {
                e.printStackTrace ( );
            }
            mediaPlayer.start ();
            TotalTimeSong();
            Updatetime();
        }


    }

    private void TotalTimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTime.setText ( simpleDateFormat.format ( mediaPlayer.getDuration () ) );
        skTime.setMax ( mediaPlayer.getDuration () );
    }

    private void Updatetime(){
        final Handler handler =  new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    skTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeStar.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 =  new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (mangbaihat.size())) {
                        imageButton_play.setImageResource(R.drawable.ic_pause_50px);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        diaNhacFragment.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());

                    }
                    imageButton_previous.setClickable(false);
                    imageButton_next.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButton_previous.setClickable(true);
                            imageButton_next.setClickable(true);
                        }
                    },3500);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,500);
                }
            }
        },500);
    }
}
