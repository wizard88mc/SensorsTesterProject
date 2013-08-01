package it.cs.unipd.utils;

import android.app.Activity;
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
}
