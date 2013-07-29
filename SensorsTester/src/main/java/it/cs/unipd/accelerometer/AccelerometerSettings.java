package it.cs.unipd.accelerometer;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import it.cs.unipd.sensorstester.R;

/**
 * Created by Matteo Ciman on 29/07/13.
 */
public class AccelerometerSettings extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.accelerometer_preferences);
    }
}
