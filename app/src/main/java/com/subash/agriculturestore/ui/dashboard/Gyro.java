package com.subash.agriculturestore.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.subash.agriculturestore.R;

public class Gyro extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView tvGyro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gyro);
        setTitle("Gyroscope Sensor");
        tvGyro = findViewById(R.id.tvGyro);
        sensorGyro();




    }

    private void sensorGyro() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener gyrolistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1]<0){
                    tvGyro.setText("Phone is Moved Towards Left Side");
                }
                else if (event.values[1]>0){
                    tvGyro.setText("Phone is Moved Towards Right Side");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager.registerListener(gyrolistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "No Sensor Found", Toast.LENGTH_SHORT);
        }
    }
}
