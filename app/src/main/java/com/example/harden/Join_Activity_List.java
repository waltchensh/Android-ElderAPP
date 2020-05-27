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

public class Join_Activity_List extends ArrayAdapter<Artist>{
    private Activity context;
    private List<Artist>artistList;

    public Join_Activity_List(Activity context, List<Artist>artistList){
        super(context,R.layout.joinlist_layout,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.joinlist_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewRe = (TextView)listViewItem.findViewById(R.id.textViewRe); //new

        TextView textViewGenre = (TextView)listViewItem.findViewById(R.id.textViewGenre);

        Artist artist = artistList.get(position);

        textViewName.setText(artist.getArtistName());
        textViewGenre.setText(artist.getArtistGenre());
        textViewRe.setText(artist.getArtistRe());  // new

        return listViewItem;
    }
}
