package com.example.expensetracker;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpenseFragment extends Fragment {
    Spinner spinner;
    List<String> str;
    View view;
    EditText amount;
    DatePicker datePicker;
    Calendar calendar;
    Button add;
    String date;
    MyDBHelper myDBHelper;

    public ExpenseFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expense, container, false);

        spinner = view.findViewById(R.id.spinner);
        add = view.findViewById(R.id.add);
        datePicker = view.findViewById(R.id.date);
        amount = view.findViewById(R.id.amount);

        str = new ArrayList<String>();
        str.add("Food & Groceries");
//        str.add("Tuttion & Fees");
//        str.add("Books & Supplies");
        str.add("Housing/Rent");
        str.add("Food");
        str.add("Transportation & Car Insurance");
        str.add("Health Insurance");
        str.add("Life Insurance");
        str.add("Entertainment");
        str.add("Credit Card Debt");
        str.add("Emergency Fund");
        str.add("Large Purchases/Luxury");
        str.add("Travel Expenses");
        str.add("Cell Phone");
        str.add("Other Expenses");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,str);

        spinner.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHelper = new MyDBHelper(getContext(),"tracker", null, 1);

                if(!amount.getText().toString().isEmpty()){
                    myDBHelper.addExpense(spinner.getSelectedItem().toString(),date,amount.getText().toString());
                    Toast.makeText(getContext(), "Expense Inserted " , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Please enter the amount to insert" , Toast.LENGTH_SHORT).show();
                }

            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = datePicker.getDayOfMonth() + "-" + datePicker.getMonth() + "-" + datePicker.getYear();
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    date = "";
                    date += dayOfMonth;
                    date += "-" + monthOfYear;
                    date += "-" + year;
                }
            });
        }



        return view;
    }

}