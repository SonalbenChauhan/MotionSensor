package com.example.lab5motionsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //X increases towards the right of the object.
    //Y for increasing towards the right of the object.
    //Z for increasing towards the right of the object.

    TextView textX, textY, textZ;

    SensorManager sensorManager;
    Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor
       // You can access the sensor via the sensorManager.getDefaultSensor() method,
        //that takes the sensor type and the delay defined as constants on SensorManager as parameters.


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textZ = (TextView) findViewById(R.id.textZ);
    }

    //This listener will get informed, if the sensor data changes we use that for pause and resume.
    public void onResume() {
        // Register a listener for the sensor
        super.onResume();
        sensorManager.registerListener(accelListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        // Be sure to unregister the sensor when the activity stop.

        super.onStop();
        sensorManager.unregisterListener(accelListener);
    }

    SensorEventListener accelListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {
            //Movements
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("X : " + (int)x);
            textY.setText("Y : " + (int)y);
            textZ.setText("Z : " + (int)z);
        }
    };
}


