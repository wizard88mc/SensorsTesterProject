package it.cs.unipd.position_sensors;

import android.hardware.Sensor;

/**
 * Created by po on 28/08/13.
 */
public class ProximityTester extends BasePositionSensorManager {

    public ProximityTester() {
        super(Sensor.TYPE_PROXIMITY);
    }
}
