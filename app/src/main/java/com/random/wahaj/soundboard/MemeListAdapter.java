package com.random.wahaj.soundboard;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

class MemeListAdapter extends ArrayAdapter<Meme> {

    public MemeListAdapter(@NonNull Context context, Meme[] resource) {
        super(context, R.layout.custom_list_element, resource);
    }

            @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View customListView = myInflater.inflate(R.layout.custom_list_element, parent, false);
        final Meme singleMemeItem = getItem(position);
        Button listButton = (Button) customListView.findViewById(R.id.listButton);

        listButton.setText(singleMemeItem.name);
        listButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                try { AssetFileDescriptor afd = getContext().getAssets().openFd(singleMemeItem.assetName);
                MediaPlayer player = new MediaPlayer();
                player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                player.prepare();
                player.start();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return customListView;
    }

}
