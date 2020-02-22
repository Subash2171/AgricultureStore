package com.subash.agriculturestore.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.subash.agriculturestore.MainActivity;
import com.subash.agriculturestore.R;
import com.subash.agriculturestore.bll.LoginBLL;
import com.subash.agriculturestore.strictmode.StrictMode;

public class LoginActivity extends AppCompatActivity {

    private int id = 1;
    private TextView signUp_text;
    private EditText etPhone, etPassword;
    private Button btnLogin;
    private NotificationManagerCompat notificationManagerCompat;
    private CreateChannel createChannel;
    private SensorEventListener event;
    private SensorManager manager;
    private Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        signUp_text = findViewById(R.id.signUp_text);
        etPhone = findViewById(R.id.etPhoneLogin);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             login();
            }
        });

        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login() {


        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();

        if (phone.equals("")) {
            etPhone.setError("Email is required!!!");
            etPhone.requestFocus();
            return;
        } else if (password.equals("")) {
            etPassword.setError("Password is required!!!");
            etPassword.requestFocus();
            return;
        }

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();


        LoginBLL loginBLL = new LoginBLL();

        StrictMode.StrictMode();
        if (loginBLL.checkUser(phone, password)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            showLoginNotification();
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }


    }
    public void openRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.registerListener(event, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(event);
    }

    private void showLoginNotification() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("User Log In")
                .setContentText("You have successfully logged in!!!")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(id, notification);
        id++;

    }
}
