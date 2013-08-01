package it.cs.unipd.motion_sensors;

import android.hardware.Sensor;

/**
 * Created by Matteo Ciman on 01/08/13.
 */
public class AccelerometerTester extends BaseMotionSensorManager {

    public AccelerometerTester() {
        super(Sensor.TYPE_ACCELEROMETER);
    }
}
