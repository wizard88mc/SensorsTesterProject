package it.cs.unipd.sensorstester;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.List;

import it.cs.unipd.environment_sensors.AmbientTemperatureTester;
import it.cs.unipd.environment_sensors.LightTester;
import it.cs.unipd.environment_sensors.PressureTester;
import it.cs.unipd.environment_sensors.RelativeHumidityTester;
import it.cs.unipd.motion_sensors.AccelerometerTester;
import it.cs.unipd.motion_sensors.BaseMotionSensorManager;
import it.cs.unipd.motion_sensors.GravityTester;
import it.cs.unipd.motion_sensors.GyroscopeTester;
import it.cs.unipd.motion_sensors.LinearAccelerationTester;
import it.cs.unipd.position_sensors.GPSLocationTester;
import it.cs.unipd.position_sensors.MagneticFieldTester;
import it.cs.unipd.position_sensors.ProximityTester;

public class SensorTesterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_tester_activity);

        List<Sensor> deviceSensor = ((SensorManager)getSystemService(Context.SENSOR_SERVICE))
                .getSensorList(Sensor.TYPE_ALL);

        Log.i("List", deviceSensor.toString());
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
                Intent intent = new Intent(this, GyroscopeTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_linear_acceleration: {
                System.out.println("Linear Acceleration");
                Intent intent = new Intent(this, LinearAccelerationTester.class);
                startActivity(intent);
                break;
            }
        }
    }
    public void environmentSensorChosen(View view) {

        switch (view.getId()) {

            case R.id.button_ambient_temperature: {
                System.out.println("Ambient temperature");
                Intent intent = new Intent(this, AmbientTemperatureTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_light: {
                System.out.println("Light");
                Intent intent = new Intent(this, LightTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_pressure: {
                System.out.println("Pressure");
                Intent intent = new Intent(this, PressureTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_relative_humidity: {
                System.out.println("Relative humidity");
                Intent intent = new Intent(this, RelativeHumidityTester.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void positionSensorChosen(View view) {

        switch (view.getId()) {

            case R.id.button_magnetic_field: {
                System.out.println("Magnetic field");
                Intent intent = new Intent(this, MagneticFieldTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_proximity: {
                System.out.println("Proximity");
                Intent intent = new  Intent(this, ProximityTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_gps: {
                System.out.println("GPS");
                Intent intent = new Intent(this, GPSLocationTester.class);
                startActivity(intent);
                break;
            }
        }
    }
    
}
