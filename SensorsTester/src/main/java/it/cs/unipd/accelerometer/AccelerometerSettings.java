package it.cs.unipd.accelerometer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import it.cs.unipd.sensorstester.R;

/**
 * Created by Matteo Ciman on 29/07/13.
 */
public class AccelerometerSettings extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Settings())
                .commit();
    }

    protected static class Settings extends PreferenceFragment {

        public Settings() {
            super();
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.accelerometer_preferences);
        }
    }
}
