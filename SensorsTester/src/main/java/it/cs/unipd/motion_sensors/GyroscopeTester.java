package it.cs.unipd.motion_sensors;

import android.hardware.Sensor;

/**
 * Created by Matteo Ciman on 01/08/13.
 */
public class GyroscopeTester extends BaseMotionSensorManager {

    public GyroscopeTester() {
        super(Sensor.TYPE_GYROSCOPE);
    }
}
