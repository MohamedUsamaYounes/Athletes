package com.apptcom.athletes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mido on 17/08/2017.
 */


public class AllAthlete implements Serializable {
    @SerializedName("athletes")
    @Expose
    private List<Athlete> athletes = null;

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

}
