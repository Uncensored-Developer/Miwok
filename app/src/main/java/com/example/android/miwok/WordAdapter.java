package com.example.android.miwok;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    private int mColourResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colourResourceId){
        //Here we initialize the ArrayAdapter's internal storage for the context and the list.
        //the second argument is used when the array adapter is populating just a single TextView
        //because this is a custom adapter for two TextViews
        super(context,0,words);
        mColourResourceId = colourResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check of the existing view is reused, otherwise inflate view
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Get the word Object located at the position in the list
        Word currentWord = getItem(position);

        //find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItem.findViewById(R.id.miwok_text_view);
        //get the current miwok translation from the current Word object and set it on the
        miwokTextView.setText(currentWord.getMiwokTranslation());

        //find the TextView in the list_item.xml layout with the ID defualt_text_view
        TextView defaultTextView = (TextView) listItem.findViewById(R.id.default_text_view);
        //get the current default translation from the current Word object and set it on the
        defaultTextView.setText(currentWord.getDefaultTranslation());

        //find the ImageView in the list_item.xml layout with the ID Image
        ImageView imageView = (ImageView) listItem.findViewById(R.id.image);
        if (currentWord.hasImage()){
            //get the current image from the current Word object and set it on the
            imageView.setImageResource(currentWord.getImageResourceId());

            imageView.setVisibility(View.VISIBLE);
        } else {
            //hide imageView
            imageView.setVisibility(View.GONE);
        }

        //set the theme color for the layout
        View textContainer = listItem.findViewById(R.id.text_container);
        //find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColourResourceId);
        //set the background color
        textContainer.setBackgroundColor(color);

        //return the whole list item layout containing two TextViews
        return listItem;

    }
}
