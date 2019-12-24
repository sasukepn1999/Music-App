package com.example.deadline01_1712791_lam_ba_thinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Music extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private int id_song;
    private String songName;
    private Intent intent;
    private ImageButton imgPlay;
    private ImageButton imgPause;
    private ImageButton imgStop;
    private ImageButton imgLoop;
    private TextView textSongName;
    private TextView textCurrent;
    private TextView textTotal;
    private ImageView imgMusic;
    private SeekBar seekBar;
    private Handler handler;
    private Runnable runnable;
    private boolean isLoop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        imgPlay = findViewById(R.id.btnPlay);
        imgPause = findViewById(R.id.btnPause);
        imgStop = findViewById(R.id.btnStop);
        imgLoop = findViewById(R.id.btnLoop);

        textSongName = findViewById(R.id.txtSongName);
        textCurrent = findViewById(R.id.txtCurrentTime);
        textTotal = findViewById(R.id.txtTotalTime);

        imgMusic = findViewById(R.id.imgMusic);

        seekBar = findViewById(R.id.seekbarMusic);

        handler = new Handler();

        imgPlay.setEnabled(true);
        imgPause.setEnabled(false);
        imgStop.setEnabled(false);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        imgMusic.startAnimation(animation);

        intent = this.getIntent();
        id_song = intent.getIntExtra("ID song", 0);
        songName = intent.getStringExtra("Name song");

        mediaPlayer = MediaPlayer.create(getApplicationContext(), id_song);

        textSongName.setText(songName);
        textTotal.setText(timeToString(mediaPlayer.getDuration()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        textCurrent.setText(timeToString(mediaPlayer.getCurrentPosition()));

        if (mediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    public String timeToString(int time){
        String timeString = "";

        int minute = time / 1000 / 60;
        int second = time / 1000 % 60;

        timeString += minute + ":";

        if (second < 10) {
            timeString+="0";
        }

        timeString += second;

        return timeString;
    }

    public void onPlay(View view){
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        changeSeekBar();
        imgPlay.setEnabled(false);
        imgPause.setEnabled(true);
        imgStop.setEnabled(true);
    }

    public void onPause(View view){
        mediaPlayer.pause();
        imgPause.setEnabled(false);
        imgPlay.setEnabled(true);
    }

    public void onStop(View view){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), id_song);
        if (isLoop) { mediaPlayer.setLooping(true); }
        imgStop.setEnabled(false);
        imgPause.setEnabled(false);
        imgPlay.setEnabled(true);
    }

    public void onLoop(View view){
        if (!isLoop){
            mediaPlayer.setLooping(true);
            imgLoop.setBackgroundResource(R.drawable.ic_loop_selected_24dp);
            isLoop = true;
        }
        else{
            mediaPlayer.setLooping(false);
            imgLoop.setBackgroundResource(R.drawable.ic_loop_black_24dp);
            isLoop = false;
        }
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        finish();
    }
}
