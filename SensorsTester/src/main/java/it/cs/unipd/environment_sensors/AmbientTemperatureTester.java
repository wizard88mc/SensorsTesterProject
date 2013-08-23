package it.cs.unipd.environment_sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by Matteo Ciman on 02/08/13.
 */
public class AmbientTemperatureTester extends BaseEnvironmentSensorManager {

    public AmbientTemperatureTester() {
        super(Sensor.TYPE_AMBIENT_TEMPERATURE, "°C");
    }
}
