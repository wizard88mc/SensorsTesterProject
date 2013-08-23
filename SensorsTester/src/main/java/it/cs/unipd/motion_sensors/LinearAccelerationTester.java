package it.cs.unipd.motion_sensors;

import android.hardware.Sensor;

/**
 * Created by Matteo Ciman on 01/08/13.
 */
public class LinearAccelerationTester extends BaseMotionSensorManager {

    public LinearAccelerationTester(){
        super(Sensor.TYPE_LINEAR_ACCELERATION);
    }
}
