package com.apptcom.athletes.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apptcom.athletes.R;
import com.apptcom.athletes.model.Athlete;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mido on 17/08/2017.
 */

public class AllAthletesAdapter extends RecyclerView.Adapter<AllAthletesAdapter.MyViewHolder> {
    private List<Athlete> athleteList;
    private Context mContext;
    private int position;

    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_item_name_TxtView)
        TextView athleteNameView;
        @BindView(R.id.recycler_item_athlete_ImageView)
        ImageView athleteImage;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            athleteNameView = (TextView) view.findViewById(R.id.recycler_item_name_TxtView);
        }


    }


    public AllAthletesAdapter(ArrayList<Athlete> athleteList, Context context) {
        this.athleteList = athleteList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Athlete athlete = athleteList.get(position);
        holder.athleteNameView.setText(athlete.getName());
        if (!TextUtils.isEmpty(athlete.getImage()))
            Picasso.with(holder.itemView.getContext()).load(athlete.getImage()).into(holder.athleteImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAthleteDetails = new Intent(mContext,AthletesDetails.class);
                goToAthleteDetails.putExtra("name",athlete.getName());
                goToAthleteDetails.putExtra("breif",athlete.getBrief());
                goToAthleteDetails.putExtra("Image",athlete.getImage());
                mContext.startActivity(goToAthleteDetails);

            }
        });
    }


    @Override
    public int getItemCount() {
        return athleteList.size();
    }
}