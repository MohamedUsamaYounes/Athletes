package com.apptcom.athletes.service;


import com.apptcom.athletes.model.AllAthlete;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mido on 17/08/2017.
 */

public interface Api {
    @GET("athletes.josn")
    Call<AllAthlete> loadAllAthletes();
    //Call<AllAthlete> response;
}
