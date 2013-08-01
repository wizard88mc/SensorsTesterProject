package it.cs.unipd.motion_sensors;

import android.hardware.Sensor;

/**
 * Created by po on 01/08/13.
 */
public class GravityTester extends BaseMotionSensorManager {

    public GravityTester() {
        super(Sensor.TYPE_GRAVITY);
    }
}

