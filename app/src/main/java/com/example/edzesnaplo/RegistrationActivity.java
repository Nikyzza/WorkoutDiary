package com.example.edzesnaplo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = RegistrationActivity.class.getName();
    private static final String PREF_KEY = RegistrationActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    EditText userNameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;
    EditText phoneEditText;
    RadioGroup phoneTypeGroup;
    EditText heightEditText;
    EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        secretKeyCheck();
        findData();

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String email = preferences.getString("userName", "");
        String pwd = preferences.getString("password", "");

        setEmailPassword(email, pwd);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setEmailPassword(String email, String pwd) {
        emailEditText.setText(email);
        passwordEditText.setText(pwd);
        passwordAgainEditText.setText(pwd);
    }

    private void secretKeyCheck() {
        int secretKey = getIntent().getIntExtra("SECRET_KEY", 99);
        if (secretKey != 99) {
            finish();
        }
    }

    private void findData() {
        userNameEditText = findViewById(R.id.userNameEditText);
        emailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordAgainEditText = findViewById(R.id.passwordAgainEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        phoneTypeGroup = findViewById(R.id.phoneTypeGroup);
        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
    }

    public void registration(View view) {
        String userName = userNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordAgainEditText.getText().toString();

        if (!password.equals(passwordConfirm)) {
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és megerősítése.");
            return;
        }

        String phoneNumber = phoneEditText.getText().toString();
        int checkedId = phoneTypeGroup.getCheckedRadioButtonId();
        RadioButton radioButton = phoneTypeGroup.findViewById(checkedId);
        String phoneType = radioButton.getText().toString();
        String height = heightEditText.getText().toString();
        String weight = weightEditText.getText().toString();
        authenticationRegister(email, password);
    }

    private void authenticationRegister(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(LOG_TAG, "User created successfully");
                    startTrainingDiary();
                } else {
                    Log.d(LOG_TAG, "User was not created successfully");
                    Toast.makeText(RegistrationActivity.this, "User was not created successfully: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cancel(View view) {
        finish();
    }

    private void startTrainingDiary() {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
