package com.example.expensetracker;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
        import android.widget.ArrayAdapter;
        import android.widget.Toast;

        import androidx.annotation.Nullable;

        import com.google.android.material.tabs.TabLayout;

        import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    //Expense
    private static final String DATABASE_NAME = "tracker";
    private static final String TABLE_NAME1 = "expense";
    private static final int DATABSE_VERSION = 1;
    private static final String ID1 = "id";
    private static final String CATEGORY1 = "category";
    private static final String DATE1 = "date";
    private static final String AMOUNT1 = "amount";


    //Income

    private static final String TABLE_NAME2 = "income";
    private static final String ID2 = "id";
    private static final String CATEGORY2 = "category";
    private static final String DATE2 = "date";
    private static final String AMOUNT2 = "amount";

    public MyDBHelper(Context context,  String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABSE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE expense ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, category TEXT, amount INTEGER, date TEXT)
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + " " +
                "( "+ ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                CATEGORY1 + " TEXT , " +
                AMOUNT1 + " INTEGER ," +
                DATE1 + " TEXT )");

        Log.d("MyDBHelper", "Created Table " + TABLE_NAME1);

        db.execSQL("CREATE TABLE " + TABLE_NAME2 + " " +
                "( "+ ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                CATEGORY2 + " TEXT , " +
                AMOUNT2 + " INTEGER ," +
                DATE2 + " TEXT )");

        Log.d("MyDBHelper", "Created Table" + TABLE_NAME2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        Log.d("MyDBHelper", "Updated Tables");
        onCreate(db);
    }

    public void addExpense(String category, String date, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Log.d("MyDBHelper", "Category = " + category + "\taddExpense()");
        Log.d("MyDBHelper", "Amount = " + amount + "\taddExpense()");
        Log.d("MyDBHelper", "Date = " + date + "\taddExpense()");



        cv.put(CATEGORY1, category);
        cv.put(AMOUNT1, Integer.parseInt(amount));
        cv.put(DATE1, date);

        if(-1 == db.insert(TABLE_NAME1, null, cv)){
            Log.d("MyDBHelper", "Insertion Failed in addExpense()");
        }
        else
            Log.d("MyDBHelper", "Insertion Successful in addExpense()");
    }

    public void addIncome(String category, String date, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        Log.d("MyDBHelper", "Category = " + category + "\taddIncome()") ;
        Log.d("MyDBHelper", "Amount = " + amount  + "\taddIncome()");
        Log.d("MyDBHelper", "Date = " + date + "\taddIncome()");

        cv.put(CATEGORY1, category);
        cv.put(AMOUNT1, Integer.parseInt(amount));
        cv.put(DATE1, date);

        if(-1 == db.insert(TABLE_NAME2, null, cv)){
            Log.d("MyDBHelper", "Insertion Failed in addIncome()");
        }
        else
            Log.d("MyDBHelper", "Insertion Successful in addIncome()");

    }
    public ArrayList<String> getExpenses(){
        Log.d("MyDBHelper", "Called getExpenses Function");
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME1 + ";", null);
        ArrayList<String> result = new ArrayList<>();


        Log.d("MyDBHelper", "Called getExpenses Function and query is fired");
        while(cursor.moveToNext()){
            String cat = cursor.getString(1);
            String dt = cursor.getString(3);
            String am = cursor.getString(2);

            String current = "Category : " + cat + "\nDate : " + dt + "\nAmount : " + am;

            result.add(current);
        }

        Log.d("MyDBHelper", "Called getExpenses Function and returning result");
        return result;
    }


    public ArrayList<String> getIncome(){
        Log.d("MyDBHelper", "Called getExpenses Function");
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME2 + ";", null);
        ArrayList<String> result = new ArrayList<>();


        Log.d("MyDBHelper", "Called getExpenses Function and query is fired");
        while(cursor.moveToNext()){
            String cat = cursor.getString(1);
            String dt = cursor.getString(3);
            String am = cursor.getString(2);

            String current = "Category : " + cat + "\nDate : " + dt + "\nAmount : " + am;

            result.add(current);
        }

        Log.d("MyDBHelper", "Called getExpenses Function and returning result");
        return result;
    }

    public ArrayList<String> getDiff(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor1 = db.rawQuery("SELECT "+ AMOUNT1 + " FROM " + TABLE_NAME1 + ";",null);

        Cursor cursor2 = db.rawQuery("SELECT "+ AMOUNT2 + " FROM " + TABLE_NAME2 + ";",null);

        int totalExp, totalInc;
        totalInc = totalExp = 0;

        while(cursor1.moveToNext()){
            totalExp += cursor1.getInt(0);
        }

        while(cursor2.moveToNext()){
            totalInc += cursor2.getInt(0);
        }

        ArrayList<String> result = new ArrayList<>();

        result.add("Expenses = " + String.valueOf(totalExp));
        result.add("Income = " + String.valueOf(totalInc));
        result.add("Remaining = " + String.valueOf(totalInc - totalExp));

        return result;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME1);
        db.execSQL("DELETE FROM " + TABLE_NAME2);


    }

}

