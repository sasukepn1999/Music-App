package com.example.deadline01_1712791_lam_ba_thinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Intent playIntent;
    private boolean musicBound=false;
    private ArrayList<Song> songArrayList;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDataToListView();
        onItem();
    }

    private void loadDataToListView(){
        songArrayList = initDataInArray();

        SongAdapter songAdapter = new SongAdapter(this, songArrayList);
        ListView listView = this.findViewById(R.id.listview);

        listView.setAdapter(songAdapter);
    }


    private ArrayList<Song> initDataInArray(){
        ArrayList<Song> songArrayList = new ArrayList<Song>();

        songArrayList.add(new Song("Trời Giấu Trời Mang Đi",
                "troigiautroimangdi",
                "Ca sỹ: Amee ft Viruss"));

        songArrayList.add(new Song("I Won't Give Up",
                "iwontgiveup",
                "Ca sỹ: Jason Mraz"));

        songArrayList.add(new Song("I’m Yours",
                "imyours",
                "Ca sỹ: Jason Mraz"));

        songArrayList.add(new Song("I love you boy",
                "iloveyouboy",
                "Ca sỹ: Suzy"));

        songArrayList.add(new Song("First Love",
                "firstlove",
                "Ca sỹ: Epitone Project"));

        songArrayList.add(new Song("Say Goodbye",
                "saygoodbye",
                "Ca sỹ: Kim Na Young"));

        songArrayList.add(new Song("Knees",
                "knees",
                "Ca sỹ: IU"));

        songArrayList.add(new Song("Through the Night",
                "throughthenight",
                "Ca sỹ: IU"));

        songArrayList.add(new Song("Can you see my heart",
                "canyouseemyheart",
                "Ca sỹ: IU"));

        return songArrayList;
    }

    private void onItem(){
        ListView listView = findViewById(R.id.listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song = songArrayList.get(i);
                Intent intentMusic = new Intent(MainActivity.this, Music.class);
                int id_song = getApplicationContext().getResources().getIdentifier(song.getM_avatarName(), "raw",
                        getApplicationContext().getPackageName());

                intentMusic.putExtra("ID song", id_song);
                intentMusic.putExtra("Name song", song.getM_Name());

                startActivity(intentMusic);
            }
        });
    }
}
