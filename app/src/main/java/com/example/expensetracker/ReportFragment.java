package com.example.expensetracker;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportFragment extends Fragment {

    ArrayList<String> list ;
    View view;
    ListView listView;
    ArrayAdapter<String> adapter;
    MyDBHelper myDBHelper;

    AppCompatButton exp,inc, rem, clr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report, container, false);




        myDBHelper = new MyDBHelper(getContext(), "tracker", null, 1);

        listView = view.findViewById(R.id.listView);
        exp = view.findViewById(R.id.exp);
        inc = view.findViewById(R.id.inc);
        rem = view.findViewById(R.id.rem);
        clr = view.findViewById(R.id.clr);

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadExpenses(view);
            }
        });


        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIncome(view);
            }
        });

        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDifference(view);
            }
        });


        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHelper.delete();
                Toast.makeText(getContext(), "All Cleared " , Toast.LENGTH_SHORT).show();
                exp.callOnClick();
            }
        });
        return view;
    }


    public void loadIncome(View view){

        list = myDBHelper.getIncome();

        Log.d("MyDBHelper", "Received Result of getExpense()");

        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }


    public void loadExpenses(View view) {
        list = myDBHelper.getExpenses();

        Log.d("MyDBHelper", "Received Result of getExpense()");

        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }

    public void loadDifference(View view){
        list = myDBHelper.getDiff();
        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }


}