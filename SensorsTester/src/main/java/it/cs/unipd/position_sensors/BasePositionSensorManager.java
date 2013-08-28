package it.cs.unipd.position_sensors;

import android.app.Activity;
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
 * Created by matteo on 8/27/13.
 */
public class BasePositionSensorManager extends Activity implements View.OnClickListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private int sensorID;
    private long lastTimestamp = 0;
    private boolean sampling = false;
    private boolean constantSampling;
    private AlternateManager threadPattern = null;

    public BasePositionSensorManager(int sensorID) {
        super();
        this.sensorID = sensorID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.sensorID == Sensor.TYPE_MAGNETIC_FIELD) {
            setContentView(R.layout.three_values);
        }
        else {
            setContentView(R.layout.single_value);
        }

        ((Button)this.findViewById(R.id.button_start_sampling)).setOnClickListener(this);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensorID);
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

            Button button = (Button)findViewById(R.id.button_start_sampling);
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
            Button button = (Button)findViewById(R.id.button_start_sampling);
            button.setText("Start Sampling");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float[] values = sensorEvent.values;

        if (this.sensorID == Sensor.TYPE_MAGNETIC_FIELD) {
            TextView xAxis = (TextView)findViewById(R.id.x_value);
            xAxis.setText(Float.toString(values[0]));
            TextView yAxis = (TextView)findViewById(R.id.y_value);
            yAxis.setText(Float.toString(values[1]));
            TextView zAxis = (TextView)findViewById(R.id.z_value);
            zAxis.setText(Float.toString(values[2]));
        }
        else {
            TextView singleValue = (TextView)findViewById(R.id.value);
            singleValue.setText(Float.toString(values[0]));
        }

        if (lastTimestamp != 0) {
            TextView delta = (TextView)findViewById(R.id.delta_time);
            delta.setText(Long.toString((sensorEvent.timestamp - lastTimestamp) / 1000000));
        }
        lastTimestamp = sensorEvent.timestamp;
    }
}
