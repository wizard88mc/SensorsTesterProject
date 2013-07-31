package it.cs.unipd.accelerometer;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.cs.unipd.sensorstester.R;
import it.cs.unipd.utils.AlternateManager;

public class AccelerometerTester extends Activity implements View.OnClickListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastTimestamp = 0;
    private boolean sampling = false;
    private boolean constantSampling;
    private Thread threadPattern = null;

    public AccelerometerTester() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_tester);

        ((Button)this.findViewById(R.id.button_start_sampling_acc)).setOnClickListener(this);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        /*getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AccelerometerSettings())
                .commit();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accelerometer_tester, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent intent = new Intent(this, AccelerometerSettings.class);
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
            String samplingFrequency = sharedPreferences.getString("acc_frequency", "");
            constantSampling = sharedPreferences.getBoolean("acc_unlimited_sampling", true);

            int speed = -1;
            if (samplingFrequency.equals("NORMAL")) {
                speed = SensorManager.SENSOR_DELAY_NORMAL;
            }
            else if (samplingFrequency.equals("UI")) {
                speed = SensorManager.SENSOR_DELAY_UI;
            }
            else if (samplingFrequency.equals("GAME")) {
                speed = SensorManager.SENSOR_DELAY_GAME;
            }
            else if (samplingFrequency.equals("FASTEST")) {
                speed = SensorManager.SENSOR_DELAY_FASTEST;
            }

            Button button = (Button)findViewById(R.id.button_start_sampling_acc);
            button.setText(R.string.stop_sampling);

            sampling = true;

            if (constantSampling) {
                sensorManager.registerListener(this, accelerometer, speed);
            }
            else {

                int samplingDuration = -1, idleDuration = -1;
                samplingDuration = sharedPreferences.getInt("acc_sampling_durantion", 10);
                idleDuration = sharedPreferences.getInt("acc_idle_duration", 10);

                threadPattern = new AlternateManager(this, samplingDuration, idleDuration,
                        sensorManager, accelerometer, speed );

                threadPattern.start();

            }
        }
        else {
            if (constantSampling) {
                sensorManager.unregisterListener(this);
            }
            else {

            }
            sampling = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float[] values = sensorEvent.values;
        TextView xAxis = (TextView)findViewById(R.id.acc_x_value);
        xAxis.setText(Float.toString(values[0]));
        TextView yAxis = (TextView)findViewById(R.id.acc_y_value);
        yAxis.setText(Float.toString(values[1]));
        TextView zAxis = (TextView)findViewById(R.id.acc_z_value);
        zAxis.setText(Float.toString(values[2]));

        if (lastTimestamp != 0) {
            TextView delta = (TextView)findViewById(R.id.acc_delta_time);
            delta.setText(Long.toString((sensorEvent.timestamp - lastTimestamp) / 1000000));
        }
        lastTimestamp = sensorEvent.timestamp;

    }
}
