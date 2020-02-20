package com.subash.agriculturestore;

import android.content.Context;
import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView tvTemperature, tvHumidity;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private TextView tvShake;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        tvTemperature = findViewById(R.id.tvtemperature);
        tvHumidity = findViewById(R.id.tvhumidity);




        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        SensorGyro();
    }

    }
    private void SensorGyro() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener gyrolistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 0) {
                    finish();
                } else if (event.values[1] > 0) {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null){
            sensorManager.registerListener(gyrolistener, sensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "No Sensor Found", Toast.LENGTH_SHORT).show();
        }
    }


    private void temperatureSensor() {
        sensorManager = (SensorManager) MainActivity.this.getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        SensorEventListener temperatureListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float temp = event.values[0];
                tvTemperature.setText(temp + "°C");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null){
            sensorManager.registerListener(temperatureListener, sensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(MainActivity.this, "No Sensor Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void humiditySensor() {
        sensorManager = (SensorManager) MainActivity.this.getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        SensorEventListener temperatureListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float humidity = event.values[0];
                String humidityLevel;

                if(humidity <= 30){
                    humidityLevel = "Low Humidity";
                }
                else if(humidity > 30 && humidity < 60){
                    humidityLevel = "Moderate Humidity";
                }
                else {
                    humidityLevel = "High Humidity";
                }

                tvHumidity.setText(humidityLevel);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null){
            sensorManager.registerListener(temperatureListener, sensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(MainActivity.this, "No Sensor Found", Toast.LENGTH_SHORT).show();
        }
    }

}
