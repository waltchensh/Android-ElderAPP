package com.example.harden;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Join_Activity_Record_List extends ArrayAdapter<record_Artist> {

    private Activity context;
    private List<record_Artist> artistList;

    public Join_Activity_Record_List(Activity context, List<record_Artist> artistList){
        super(context,R.layout.joinlist_layout, artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.joinlist_layout,null,true);

        TextView textViewName = (TextView)ListViewItem.findViewById(R.id.textRecord);
        TextView textViewGenre = (TextView)ListViewItem.findViewById(R.id.cenreRecord);

        record_Artist artist = artistList.get(position);

        textViewName.setText(artist.getArtistName());
        textViewGenre.setText(artist.getArtistGenre());

        return ListViewItem;
    }
}
