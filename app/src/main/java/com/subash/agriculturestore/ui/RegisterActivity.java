package com.subash.agriculturestore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.subash.agriculturestore.api.UserAPI;
import com.subash.agriculturestore.model.User;
import com.subash.agriculturestore.server_response.SignUpResponse;
import com.subash.agriculturestore.url.URL;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView login_text;
    private EditText etName, etEmail, etPhoneNumber, etSMSCode, etPasswordRegister;
    private Button btnSendSMS, btnSignUp;

    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        etSMSCode = findViewById(R.id.etSMSCode);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btnSendSMS = findViewById(R.id.btnSendSMS);
        btnSignUp = findViewById(R.id.btnSignUp);

        login_text = findViewById(R.id.login_text);


        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPhoneNumber.getText().toString())){
                    etPhoneNumber.setError("Enter Phone Number");
                    return;
                }
                final int random = (int)(Math.random() * 9999 + 0000);
                etSMSCode.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        etSMSCode.setText(Integer.toString(random));
                    }
                }, 3000);
            }
        });

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhoneNumber.getText().toString();
                String password = etPasswordRegister.getText().toString();
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();

                User users = new User(name, email, password, phone);

                UserAPI usersAPI = URL.getInstance().create(UserAPI.class);
                Call<SignUpResponse> signUpCall = usersAPI.registerUser(users);

                signUpCall.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent intent =  new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Register Unsuccessful" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
