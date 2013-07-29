package it.cs.unipd.accelerometer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import it.cs.unipd.sensorstester.R;

public class AccelerometerTester extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AccelerometerSettings())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accelerometer_tester, menu);
        return true;
    }
    
}
