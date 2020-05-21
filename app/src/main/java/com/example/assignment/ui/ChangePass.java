package com.example.assignment.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Database;
import com.example.assignment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePass#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePass extends Fragment {
    EditText edtPassCurrent, edtPassNew, edtPassConfirm;
    Button btnConfirm, btnCancel;
    Intent intent;
    Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edtPassCurrent = view.findViewById(R.id.edtPassCurrent);
        edtPassNew = view.findViewById(R.id.edtPassNew);
        edtPassConfirm = view.findViewById(R.id.edtPassConfirm);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnCancel = view.findViewById(R.id.btnCancel);
        intent = getActivity().getIntent();
        handling();
        return view;
    }

    public void handling() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new Database(getContext());

                String name = intent.getStringExtra("name");
                if (edtPassCurrent.getText().toString().equals(database.searchUser(name).getPass()) && edtPassNew.getText().toString().equals(edtPassConfirm.getText().toString())) {
                    int result = database.updateUser(name, edtPassNew.getText().toString());
                    if (result > 0) {
                        Toast.makeText(getContext(), "Đổi mặt khẩu thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Mật khẩu hiện tại của bạn điền không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPassCurrent.setText("");
                edtPassConfirm.setText("");
                edtPassNew.setText("");
            }
        });
    }
}
