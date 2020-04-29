package com.example.findyourlecturer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditUserActivity extends AppCompatActivity {
    EditText editemail, editnama, editpsswrd, editno;
    RadioGroup rguser;
    RadioButton rbdosen, rbmhsswa;
    Button btedit;
    FirebaseAuth fAuth;
    String mahasiswa,dosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editnama = (EditText) findViewById(R.id.editnamalengkap);
        editemail = (EditText) findViewById(R.id.editemail);
        editno = (EditText) findViewById(R.id.editnoidentitas);
        editpsswrd = (EditText) findViewById(R.id.editpassword);
        rguser = (RadioGroup) findViewById(R.id.rbpilihuser);
        rbdosen = (RadioButton) findViewById(R.id.rbdosen);
        rbmhsswa = (RadioButton) findViewById(R.id.rbmahasiswa);
        btedit = (Button) findViewById(R.id.btedit);
        fAuth = FirebaseAuth.getInstance();

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null) {
            String nama =(String) b.get("nama");
            String email =(String) b.get("email");
            String noidentitas =(String) b.get("noidentitas");
            String password =(String) b.get("password");
            String pilihuser =(String) b.get("user");

            editnama.setText(nama);
            editemail.setText(email);
            editno.setText(noidentitas);
            editpsswrd.setText(password);
            if(pilihuser.equals("Dosen"))
            {
                rbdosen.setChecked(true);
                rbmhsswa.setChecked(false);
            }
            else if(pilihuser.equals("Mahasiswa"))
            {
                rbdosen.setChecked(false);
                rbmhsswa.setChecked(true);
            }
        }



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail("user@example.com")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String newPassword = "SOME-SECURE-PASSWORD";
                        user.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditUserActivity.this, "Password Diedit", Toast.LENGTH_SHORT).show();
                                            if (task.isSuccessful()) {
                                                btedit.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        final int pilihbt = rguser.getCheckedRadioButtonId();
                                                        final String email = editemail.getText().toString().trim();
                                                        final String password = editpsswrd.getText().toString().trim();
                                                        final String namalengkap = editnama.getText().toString().trim();
                                                        final String noidentitas = editno.getText().toString().trim();
                                                        if (TextUtils.isEmpty(email)) {
                                                            editemail.setError("Email harus diisi");
                                                            return;
                                                        }
                                                        if (TextUtils.isEmpty((password))) {
                                                            editpsswrd.setError("Password harus diisi");
                                                            return;
                                                        }
                                                        if (password.length() < 6) {
                                                            editpsswrd.setError("Password harus lebih dari 6 karakter");
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
                                                                        if(pilihbt == rbdosen.getId())
                                                                        {
                                                                            dosen = rbdosen.getText().toString();
                                                                            DatabaseReference myRef = database.getReference("user").child(dosen).child(user + "/");
                                                                            myRef.child("namalengkap").setValue(namalengkap);
                                                                            myRef.child("email").setValue(email);
                                                                            myRef.child("password").setValue(password);
                                                                            myRef.child("noidentitas").setValue(noidentitas);
                                                                        }
                                                                        else if(pilihbt == rbmhsswa.getId())
                                                                        {
                                                                            mahasiswa = rbmhsswa.getText().toString();
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
                                                                        if(pilihbt == rbdosen.getId())
                                                                        {
                                                                            myRef.child("user").setValue(dosen);
                                                                        }
                                                                        if(pilihbt == rbmhsswa.getId())
                                                                        {
                                                                            myRef.child("user").setValue(mahasiswa);
                                                                        }

                                                                    }


                                                                    Toast.makeText(EditUserActivity.this, "User Dibuat", Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(getApplicationContext(), PendaftaranUserActivity.class));

                                                                } else {
                                                                    Toast.makeText(EditUserActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                                Toast.makeText(EditUserActivity.this, "User Diedit", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    }
                                });

                    }
                });

    }
    public void btback3(View view) {
        Intent intent = new Intent(EditUserActivity.this, ListUserActivity.class);
        startActivity(intent);
    }

}
