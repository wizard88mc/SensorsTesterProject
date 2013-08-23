package it.cs.unipd.environment_sensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.cs.unipd.sensorstester.R;
import it.cs.unipd.utils.AlternateManager;
import it.cs.unipd.utils.Settings;

/**
 * Created by Matteo Ciman on 02/08/13.
 */
public class BaseEnvironmentSensorManager extends Activity
        implements View.OnClickListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private int sensorID;
    private long lastTimestamp = 0;
    private boolean sampling = false;
    private boolean constantSampling;
    private AlternateManager threadPattern = null;
    private String unitMeasure;

    public BaseEnvironmentSensorManager(int sensorID, String unitMeasure) {
        super();
        this.sensorID = sensorID; this.unitMeasure = unitMeasure;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.environment_sensors);

        ((Button)this.findViewById(R.id.button_start)).setOnClickListener(this);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensorID);

        if (sensor == null) {

            Button buttonStart = (Button)findViewById(R.id.button_start);
            buttonStart.setEnabled(false);

        }

        /*getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Settings())
                .commit();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onClick(View view) {

        if (!sampling) {
            /**
             * Retrieving data from Preferences
             */
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String samplingFrequency = sharedPreferences.getString("frequency", "");
            constantSampling = sharedPreferences.getBoolean("unlimited_sampling", true);

            int speed = Settings.FrequencyID(samplingFrequency);

            Button button = (Button)findViewById(R.id.button_start);
            button.setText(R.string.stop_sampling);

            sampling = true;

            if (constantSampling) {
                sensorManager.registerListener(this, sensor, speed);
            }
            else {

                int samplingDuration = -1, idleDuration = -1;
                samplingDuration = sharedPreferences.getInt("sampling_durantion", 10);
                idleDuration = sharedPreferences.getInt("idle_duration", 10);

                threadPattern = new AlternateManager(this, samplingDuration, idleDuration,
                        sensorManager, sensor, speed );

                threadPattern.start();
            }
        }
        else {
            if (constantSampling) {
                sensorManager.unregisterListener(this);
            }
            else {
                threadPattern.endPattern();
            }
            sampling = false;
            Button button = (Button)findViewById(R.id.button_start);
            button.setText(R.string.start_sampling);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        try {
            float value = sensorEvent.values[0];
            TextView textValue = (TextView)findViewById(R.id.value);
            textValue.setText(Float.toString(value) + " " + this.unitMeasure);

            if (lastTimestamp != 0) {
                TextView delta = (TextView)findViewById(R.id.time);
                delta.setText(Long.toString((sensorEvent.timestamp - lastTimestamp) / 1000000));
            }
            lastTimestamp = sensorEvent.timestamp;
        }
        catch(Exception exc) {
            System.out.println(exc.toString());
        }

    }
}
