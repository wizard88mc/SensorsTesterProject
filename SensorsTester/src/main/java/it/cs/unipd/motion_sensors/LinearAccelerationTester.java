package it.cs.unipd.motion_sensors;

import android.hardware.Sensor;

/**
 * Created by po on 01/08/13.
 */
public class LinearAccelerationTester extends BaseMotionSensorManager {

    public LinearAccelerationTester(){
        super(Sensor.TYPE_LINEAR_ACCELERATION);
    }
}
