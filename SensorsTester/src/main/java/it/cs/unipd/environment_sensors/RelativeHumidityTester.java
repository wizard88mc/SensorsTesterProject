package it.cs.unipd.environment_sensors;

import android.hardware.Sensor;

/**
 * Created by Matteo Ciman on 02/08/13.
 */
public class RelativeHumidityTester extends BaseEnvironmentSensorManager {

    public RelativeHumidityTester() {
        super(Sensor.TYPE_RELATIVE_HUMIDITY, "%");
    }
}
