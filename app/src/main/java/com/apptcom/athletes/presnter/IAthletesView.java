package com.apptcom.athletes.presnter;


import com.apptcom.athletes.model.Athlete;

import java.util.ArrayList;

/**
 * Created by Mido on 17/08/2017.
 */

public interface IAthletesView {

    void setAllAthletesView(ArrayList<Athlete> AthletesArrayList);

    void loadFailure();
}
