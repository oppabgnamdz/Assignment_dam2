package com.example.assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignment.Adapter.AdapterUser;
import com.example.assignment.Database;
import com.example.assignment.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User extends Fragment {
    Database database;
    EditText edtName, edtPass, edtPassConfirm, edtPhone, edtAddress;
    Button btnConfirm, btnCancel, btnShow;
    ListView lvListUser;
    AdapterUser adapterUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        edtName = view.findViewById(R.id.edtName);
        edtPass = view.findViewById(R.id.edtPass);
        edtPassConfirm = view.findViewById(R.id.edtPassConfirm);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtAddress = view.findViewById(R.id.edtAddress);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnShow = view.findViewById(R.id.btnShow);
        lvListUser = view.findViewById(R.id.lvListUser);
        handLing();
        return view;

    }

    public void handLing() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
                edtPass.setText("");
                edtPassConfirm.setText("");
                edtPhone.setText("");
                edtAddress.setText("");
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new Database(getContext());
                String name = edtName.getText().toString();
                String pass = edtPass.getText().toString();
                String passConfirm = edtPassConfirm.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                if (!name.equals("") && !pass.equals("") && !passConfirm.equals("") && !phone.equals("") && !address.equals("")) {
                    if (pass.equals(passConfirm)) {
                        com.example.assignment.Model.User user = new com.example.assignment.Model.User(name, pass, phone, address);
                        long result = database.insertUser(user);
                        if (result > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại người này đã có tài khoản ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "2 mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Các thông tin không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database= new Database(getContext());
                try {
                    if (database.getAllUser().size() > 0) {
                        ArrayList<com.example.assignment.Model.User> users = database.getAllUser();
                         adapterUser = new AdapterUser(users);
                        adapterUser.notifyDataSetChanged();
                        lvListUser.setAdapter(adapterUser);
                    }
                } catch (Exception e) {

                }

            }
        });
    }
}
