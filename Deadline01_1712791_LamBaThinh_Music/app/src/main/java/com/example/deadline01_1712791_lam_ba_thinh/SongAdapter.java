package com.example.deadline01_1712791_lam_ba_thinh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    private final Context context;

    public SongAdapter(Context context, ArrayList<Song> songArrayList){
        super(context, 0, songArrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            // Creating layout for ListItem
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item_layout, null);

            Song info = getItem(position);

            // Declaring views
            ImageView imageView = convertView.findViewById(R.id.imageView_song);
            TextView textViewName = convertView.findViewById(R.id.textView_songName);
            TextView textViewDescription = convertView.findViewById(R.id.textView_description);

            // Finding id image from mipmap by name
            int id_image = context.getResources().getIdentifier(info.getM_avatarName(), "mipmap",
                    context.getPackageName());

            // Setting data to view
            imageView.setImageResource(id_image);
            textViewName.setText(info.getM_Name());
            textViewDescription.setText(info.getM_description());
        }

        return convertView;
    }
}
