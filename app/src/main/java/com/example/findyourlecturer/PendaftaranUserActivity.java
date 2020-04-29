package com.example.findyourlecturer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class PendaftaranUserActivity extends AppCompatActivity {
    EditText mEmail, mNamaLengkap, mPassword, mNoidentitas;
    RadioGroup mPilihUser;
    RadioButton mDosen, mMahasiswa;
    Button mBtSimpan;
    FirebaseAuth fAuth;
    String mahasiswa,dosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_user);

        //REGISTRASI
        mEmail = findViewById(R.id.editemail);
        mNamaLengkap = findViewById(R.id.editnamalengkap);
        mPassword = findViewById(R.id.editpassword);
        mNoidentitas = findViewById(R.id.editnoidentitas);
        mDosen = findViewById(R.id.rbdosen);
        mMahasiswa = findViewById(R.id.rbmahasiswa);
        mBtSimpan = findViewById(R.id.btsimpan2);
        fAuth = FirebaseAuth.getInstance();
        mPilihUser = findViewById(R.id.rbpilihuser);


        mBtSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pilihbt = mPilihUser.getCheckedRadioButtonId();
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String namalengkap = mNamaLengkap.getText().toString().trim();
                final String noidentitas = mNoidentitas.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email harus diisi");
                    return;
                }
                if (TextUtils.isEmpty((password))) {
                    mPassword.setError("Password harus diisi");
                    return;
                }
                if (password.length() < 6) {
                    mPassword.setError("Password harus lebih dari 6 karakter");
                    return;
                }

                //REGISTRASI DI FIREBASE
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            if (firebaseUser != null) {
                                String user = fAuth.getCurrentUser().getUid();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                if(pilihbt == mDosen.getId())
                                {
                                    dosen = mDosen.getText().toString();
                                    DatabaseReference myRef = database.getReference("user").child(dosen).child(user + "/");
                                    myRef.child("namalengkap").setValue(namalengkap);
                                    myRef.child("email").setValue(email);
                                    myRef.child("password").setValue(password);
                                    myRef.child("noidentitas").setValue(noidentitas);
                                }
                                else if(pilihbt == mMahasiswa.getId())
                                {
                                    mahasiswa = mMahasiswa.getText().toString();
                                    DatabaseReference myRef = database.getReference("user").child(mahasiswa).child(user + "/");
                                    myRef.child("namalengkap").setValue(namalengkap);
                                    myRef.child("email").setValue(email);
                                    myRef.child("password").setValue(password);
                                    myRef.child("noidentitas").setValue(noidentitas);
                                }

                                DatabaseReference myRef = database.getReference("user").child("dosen_dan_mahasiswa").child(user + "/");
                                myRef.child("namalengkap").setValue(namalengkap);
                                myRef.child("email").setValue(email);
                                myRef.child("password").setValue(password);
                                myRef.child("noidentitas").setValue(noidentitas);
                                if(pilihbt == mDosen.getId())
                                {
                                    myRef.child("user").setValue(dosen);
                                }
                                if(pilihbt == mMahasiswa.getId())
                                {
                                    myRef.child("user").setValue(mahasiswa);
                                }

                            }


                            Toast.makeText(PendaftaranUserActivity.this, "User Dibuat", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), PendaftaranUserActivity.class));

                        } else {
                            Toast.makeText(PendaftaranUserActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void btback3(View view) {
        Intent intent = new Intent(PendaftaranUserActivity.this, MenuUtamaAdminActivity.class);
        startActivity(intent);
    }
}
