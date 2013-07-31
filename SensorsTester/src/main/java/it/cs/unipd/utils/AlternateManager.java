package it.cs.unipd.utils;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by po on 31/07/13.
 */
public class AlternateManager extends Thread {

    private SensorEventListener manager;
    private int timeSampling = -1;
    private int timeIdle = -1;
    private SensorManager sensorManager;
    private Sensor sensor;
    private int speed = -1;
    private boolean continueSampling = true;

    public AlternateManager(SensorEventListener manager, int timeSampling, int timeIdle,
                            SensorManager sensorManager, Sensor sensor, int speed) {
        super();
        this.manager = manager; this.timeSampling = timeSampling; this.timeIdle = timeIdle;
        this.sensorManager = sensorManager; this.sensor = sensor; this.speed = speed;
    }

    @Override
    public void run() {

        while (continueSampling) {

            try {
                sensorManager.registerListener(manager, sensor, speed);

                Thread.sleep(this.timeSampling * 1000, 0);

                sensorManager.unregisterListener(manager);

                Thread.sleep(this.timeIdle * 1000, 0);
            }
            catch (InterruptedException exc) {

            }
        }

        sensorManager.unregisterListener(manager);
    }

    public void endPattern() {
        continueSampling = false;
    }
}
