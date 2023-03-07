package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    SensorManager sensorManager2;
    SensorManager sensorManager3;
    Sensor mLight;
    Sensor mTemp;
    Sensor mGrav;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager2 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mTemp = sensorManager2.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorManager3 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mGrav = sensorManager3.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager2.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager3.registerListener(this, mGrav, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        sensorManager2.unregisterListener(this);
        sensorManager3.unregisterListener(this);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
// Do something here if sensor accuracy changes.
    }
    @Override
    public final void onSensorChanged(SensorEvent event) {
// The light sensor returns a single value.
// Many sensors return 3 values, one for each axis.
        float lux = event.values[0];
        if(event.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            TextView textView = findViewById(R.id.textview);
            textView.setText("Light: " + lux);
        }
        else if(event.sensor.getType() == Sensor.TYPE_PRESSURE)
        {
            TextView textView = findViewById(R.id.textview2);
            textView.setText("Pressure: " + lux);
        }
        else if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY)
        {
            TextView textView = findViewById(R.id.textview3);
            textView.setText("humidity: " + lux);
        }
// Do something with this sensor value.
    }
}