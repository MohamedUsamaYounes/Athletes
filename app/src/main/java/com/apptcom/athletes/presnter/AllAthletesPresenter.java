package com.apptcom.athletes.presnter;

import android.util.Log;

import com.apptcom.athletes.model.AllAthlete;
import com.apptcom.athletes.model.Athlete;
import com.apptcom.athletes.network.RestClient;
import com.apptcom.athletes.service.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mido on 17/08/2017.
 */

public class AllAthletesPresenter implements IAthletesPresenter {
    private IAthletesView iAthletesView;

    public AllAthletesPresenter(IAthletesView intView) {
        this.iAthletesView = intView;
    }

    public void loadAthletesFromServer() {
        Api serviceApi = RestClient.getClient();
        Call<AllAthlete> athletesCall = serviceApi.loadAllAthletes();
        athletesCall.enqueue(new Callback<AllAthlete>() {
            @Override
            public void onResponse(Call<AllAthlete> call, Response<AllAthlete> response) {
                response.body();
                if (response.code() == 200)
                    iAthletesView.setAllAthletesView((ArrayList<Athlete>) response.body().getAthletes());
                else
                    iAthletesView.loadFailure();


            }

            @Override
            public void onFailure(Call<AllAthlete> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                iAthletesView.loadFailure();
            }
        });
    }


}
