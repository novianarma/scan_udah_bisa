package com.example.findyourlecturer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mBtLogin;
    CheckBox mShowpass;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        mEmail      = findViewById(R.id.editEmail);
        mPassword   = findViewById(R.id.editPassword);
        mBtLogin    = findViewById(R.id.btlogin2);
        mShowpass   = findViewById(R.id.showpass1);

        fAuth       = FirebaseAuth.getInstance();

        //jika status login = trus, otomatis menuju ke halaman menu utama tidak perlu mengisi password
        if (session.getLoggedInStatusUser(getBaseContext())) {
            Intent intent = new Intent(LoginUserActivity.this, MenuUtamaUserActivity.class);
            LoginUserActivity.this.startActivity(intent);
            finish();
        }

        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email harus diisi");
                    return;
                }
                if (TextUtils.isEmpty((password))){
                    mPassword.setError("Password harus diisi");
                    return;
                }
                if(password.length() < 6){
                    mPassword.setError("Password harus lebih dari 6 karakter");
                    return;
                }

                //AUTENTIKASI USER
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginUserActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MenuUtamaUserActivity.class));
                            session.setLoggedInStatusUser(getBaseContext(), true); //memberikan status login ke true
                        }
                        else{
                            Toast.makeText(LoginUserActivity.this,"Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mShowpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mShowpass.isChecked()){
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

}
