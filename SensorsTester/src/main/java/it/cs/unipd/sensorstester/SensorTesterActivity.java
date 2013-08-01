package it.cs.unipd.sensorstester;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import it.cs.unipd.motion_sensors.AccelerometerTester;
import it.cs.unipd.motion_sensors.BaseMotionSensorManager;
import it.cs.unipd.motion_sensors.GravityTester;

public class SensorTesterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_tester_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.sensor_tester, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void motionSensorChosen(View view) {

        switch (view.getId()) {

            case R.id.button_accelerometer: {
                System.out.println("Accelerometer");
                Intent intent = new Intent(this, AccelerometerTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_gravity: {
                System.out.println("Gravity");
                Intent intent = new Intent(this, GravityTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_gyroscope: {
                System.out.println("Gyroscope");
                break;
            }
            case R.id.button_linear_acceleration: {
                System.out.println("Linear Acceleration");
                break;
            }
        }
    }
    public void environmentalSensorChosen(View view) {

        switch (view.getId()) {

            case R.id.button_ambient_temperature: {
                System.out.println("Ambient temperature");
                break;
            }
            case R.id.button_light: {
                System.out.println("Light");
                break;
            }
            case R.id.button_pressure: {
                System.out.println("Pressure");
                break;
            }
            case R.id.button_proximity: {
                System.out.println("Proximity");
                break;
            }
            case R.id.button_relative_humidity: {
                System.out.println("Relative humidity");
                break;
            }
            case R.id.button_temperature: {
                System.out.println("Temperature");
                break;
            }
        }
    }

    public void positionSensorChosen(View view) {

        switch (view.getId()) {

            case R.id.button_magnetic_field: {
                System.out.println("Magnetic field");
                break;
            }
            case R.id.button_gps: {
                System.out.println("GPS");
                break;
            }
        }
    }
    
}
