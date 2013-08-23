package it.cs.unipd.environment_sensors;

import android.hardware.Sensor;

/**
 * Created by Matteo Ciman on 02/08/13.
 */
public class LightTester extends BaseEnvironmentSensorManager {

    public LightTester() {
        super(Sensor.TYPE_LIGHT, "lx");
    }
}
