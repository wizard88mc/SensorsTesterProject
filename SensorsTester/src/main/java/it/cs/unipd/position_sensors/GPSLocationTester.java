package it.cs.unipd.position_sensors;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.cs.unipd.sensorstester.R;

/**
 * Created by po on 28/08/13.
 */
public class GPSLocationTester extends Activity implements LocationListener, View.OnClickListener {

    LocationManager locationManager = null;
    boolean sampling = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_values);

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        ((Button)findViewById(R.id.button_start_sampling)).setOnClickListener(this);
    }

    public void onClick(View view) {

        if (!sampling) {
            System.out.println("Requesting sampling");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            ((Button)findViewById(R.id.button_start_sampling)).setText(R.string.stop_sampling);
            sampling = true;
        }
        else {
            System.out.println("Stop sampling");
            locationManager.removeUpdates(this);
            ((Button)findViewById(R.id.button_start_sampling)).setText(R.string.start_sampling);
            sampling = false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        TextView latitude = (TextView)findViewById(R.id.x_value);
        latitude.setText(Double.toString(location.getLatitude()));

        TextView longitude = (TextView)findViewById(R.id.y_value);
        longitude.setText(Double.toString(location.getLongitude()));

        TextView altitude = (TextView)findViewById(R.id.z_value);
        altitude.setText(Double.toString(location.getAltitude()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
