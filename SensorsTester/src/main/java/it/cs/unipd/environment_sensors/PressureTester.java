package it.cs.unipd.environment_sensors;

import android.hardware.Sensor;

/**
 * Created by Matteo Ciman on 02/08/13.
 */
public class PressureTester extends BaseEnvironmentSensorManager {

    public PressureTester() {
        super(Sensor.TYPE_PRESSURE, "mbar");
    }
}
