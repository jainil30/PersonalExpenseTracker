package com.example.expensetracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.expensetracker.ExpenseFragment;
import com.example.expensetracker.IncomeFragment;
import com.example.expensetracker.ReportFragment;

public class ViewPagerManagerAdapter extends FragmentPagerAdapter {
    public ViewPagerManagerAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == 0)
            return "Expense";
        else if(position == 1)
            return "Income";
        else
            return "Report";


    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new ExpenseFragment();
        else if(position == 1)
            return new IncomeFragment();
        else
            return new ReportFragment();


    }

    @Override
    public int getCount() {
        return 3;
    }
}
