package it.cs.unipd.sensorstester;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import it.cs.unipd.accelerometer.AccelerometerTester;

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

    public void sensorChoosed(View view) {

        switch (view.getId()) {

            case R.id.button_test_accelerometer: {
                System.out.println("Accelerometer");
                Intent intent = new Intent(this, AccelerometerTester.class);
                startActivity(intent);
                break;
            }
            case R.id.button_test_gps: {
                System.out.println("GPS");
                break;
            }
            case R.id.button_test_gyroscope: {
                System.out.println("Gyroscope");
                break;
            }
        }
    }
    
}
