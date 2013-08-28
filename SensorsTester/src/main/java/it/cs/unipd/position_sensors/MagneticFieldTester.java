package it.cs.unipd.position_sensors;

import android.hardware.Sensor;

/**
 * Created by po on 28/08/13.
 */
public class MagneticFieldTester extends BasePositionSensorManager {

    public MagneticFieldTester() {
        super(Sensor.TYPE_MAGNETIC_FIELD);
    }
}
