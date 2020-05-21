package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Model.User;

public class Login extends AppCompatActivity {
    CardView cardView;
    EditText edtUser, edtPass;
    CheckBox checkInfor;
    SharedPreferences sharedPreferences;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("myLogin", MODE_PRIVATE);
        init();


        cardView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                if (edtUser.getText().toString().equals("a") && edtPass.getText().toString().equals("1")) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    boolean b = false;
                    for (int i = 0; i < database.getAllUser().size(); i++) {
                        User user = database.getAllUser().get(i);
                        if (edtUser.getText().toString().equals(user.getName()) && edtPass.getText().toString().equals(user.getPass())) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("name",edtUser.getText().toString());
                            intent.putExtra("pass",edtPass.getText().toString());
                            startActivity(intent);
                            b = true;
                        }
                    }
                    if (!b) {
                        Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
                if (checkInfor.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", edtUser.getText().toString());
                    editor.putString("pass", edtPass.getText().toString());
                    editor.putBoolean("check", true);
                    editor.commit();
                }

            }
        });
        checkInfor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkInfor.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", edtUser.getText().toString());
                    editor.putString("pass", edtPass.getText().toString());
                    editor.putBoolean("check", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", "");
                    editor.putString("pass", "");
                    editor.putBoolean("check", false);
                    editor.commit();
                }
            }
        });
    }

    private void init() {
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean check = sharedPreferences.getBoolean("check", false);
        cardView = findViewById(R.id.cardView);
        edtUser = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        checkInfor = findViewById(R.id.checkInfor);
        edtUser.setText(user);
        edtPass.setText(pass);
        checkInfor.setChecked(check);
        database = new Database(getApplicationContext());
    }
}
