package com.example.findyourlecturer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MenuUtamaAdminActivity extends AppCompatActivity {

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_admin);

        btn=(Button) findViewById(R.id.btinfo);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                showDialog();
            }
        });
    }
    public void showDialog(){
        Dialog dialog=new Dialog(MenuUtamaAdminActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogadmin);
        dialog.show();
    }

    public void btpendaftaran(View view) {
        Intent intent = new Intent(MenuUtamaAdminActivity.this, PendaftaranUserActivity.class);
        startActivity(intent);
    }
    public void btlistuser(View view) {
        Intent intent = new Intent(MenuUtamaAdminActivity.this, ListUserActivity.class);
        startActivity(intent);
    }

    public void btlogout(View view) {
        FirebaseAuth.getInstance().signOut();
/*
        Toast.makeText(MenuUtamaAdminActivity.this, "Hasilnyaaa" , Toast.LENGTH_SHORT).show(); //mengecek
*/
        Intent intent = new Intent(MenuUtamaAdminActivity.this, LoginAdminActivity.class);
        startActivity(intent);
        session.setLoggedInStatusAdmin(getBaseContext(),false);
        finish();
    }
}
