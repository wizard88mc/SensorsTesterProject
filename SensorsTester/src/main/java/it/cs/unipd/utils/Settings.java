package it.cs.unipd.utils;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import it.cs.unipd.sensorstester.R;

/**
 * Created by Matteo Ciman on 29/07/13.
 */
public class Settings extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    protected static class SettingsFragment extends PreferenceFragment {

        public SettingsFragment() {
            super();
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
        }
    }

    public static int FrequencyID(String frequency) {

        int speed = -1;
        if (frequency.equals("NORMAL")) {
            speed = SensorManager.SENSOR_DELAY_NORMAL;
        }
        else if (frequency.equals("UI")) {
            speed = SensorManager.SENSOR_DELAY_UI;
        }
        else if (frequency.equals("GAME")) {
            speed = SensorManager.SENSOR_DELAY_GAME;
        }
        else if (frequency.equals("FASTEST")) {
            speed = SensorManager.SENSOR_DELAY_FASTEST;
        }

        return speed;
    }
}
