package com.example.assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.assignment.Adapter.AdapterCategory;
import com.example.assignment.Database;
import com.example.assignment.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Category#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Category extends Fragment {
    ListView lvListCategory;
    Database database;
    AdapterCategory adapterCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        lvListCategory = view.findViewById(R.id.lvListCategory);
        try {
            database = new Database(getContext());
            final ArrayList<com.example.assignment.Model.Category> categories = database.getAllCategory();
            adapterCategory = new AdapterCategory(categories);
            adapterCategory.notifyDataSetChanged();
            lvListCategory.setAdapter(adapterCategory);
            lvListCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    categories.remove(position);
                    adapterCategory.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {

        }
        return view;
    }
}
