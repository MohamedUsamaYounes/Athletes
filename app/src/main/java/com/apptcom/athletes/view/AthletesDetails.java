package com.apptcom.athletes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.apptcom.athletes.R;
import com.squareup.picasso.Picasso;

public class AthletesDetails extends AppCompatActivity  {

    TextView athleteName , athleteBreif;
    ImageView athleteImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athletes_details);

        athleteName = (TextView) findViewById(R.id.recycler_item_name_TxtView);
        athleteBreif = (TextView) findViewById(R.id.athletes_details_breif_TxtView);
        athleteImage = (ImageView) findViewById(R.id.recycler_item_athlete_ImageView);

        Intent camedIntent = getIntent();
        String name = camedIntent.getExtras().getString("name");
        String breif = camedIntent.getExtras().getString("breif");
        String image = camedIntent.getExtras().getString("Image");

        athleteName.setText(name);
        athleteBreif.setText(breif);
       if (!TextUtils.isEmpty(image)) {
           Picasso.with(this).load(image).into(athleteImage);
       }
    }

}