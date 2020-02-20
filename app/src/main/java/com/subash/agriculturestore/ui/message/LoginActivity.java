package com.subash.agriculturestore.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.subash.agriculturestore.MainActivity;
import com.subash.agriculturestore.R;
import com.subash.agriculturestore.bll.LoginBLL;
import com.subash.agriculturestore.strictmode.StrictMode;
import com.subash.agriculturestore.ui.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView signUp_text;
    private EditText etPhone, etPassword;
    private Button btnLogin;

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
                Intent intent =  new Intent(com.subash.agriculturestore.ui.LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login(){

        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictMode.StrictMode();
        if (loginBLL.checkUser(phone, password)) {
            Toast.makeText(com.subash.agriculturestore.ui.LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(com.subash.agriculturestore.ui.LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(com.subash.agriculturestore.ui.LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
}
