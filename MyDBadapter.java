package com.example.creditsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBadapter {
    myDbHelper myhelper;
    public MyDBadapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public void insertData()
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        dbb.execSQL("insert into user values('Archana','jainarchana998@gmail.com','20')");
        dbb.execSQL("insert into user values('Prathana','jainprathana@gmail.com','30')");



    }

    public String getallData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.NAME,myDbHelper.current_credit};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {

            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String crd =cursor.getString(cursor.getColumnIndex(myDbHelper.current_credit));

            buffer.append(name+"    "+crd+"\n");
        }
        return buffer.toString();
    }
    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.NAME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {

            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            //String crd =cursor.getString(cursor.getColumnIndex(myDbHelper.current_credit));

            buffer.append(name+"\n");
        }
        return buffer.toString();
    }
    public String getcheckData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.user_name,myDbHelper.credit_transfer};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME1,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {

            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.user_name));
            String crd =cursor.getString(cursor.getColumnIndex(myDbHelper.credit_transfer));

            buffer.append(name+crd+"\n");
        }
        return buffer.toString();
    }
    public String getselectData(String a)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.user_name,myDbHelper.credit_transfer};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME1,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {

            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.user_name));
            String crd =cursor.getString(cursor.getColumnIndex(myDbHelper.credit_transfer));
            if(a==name)
            buffer.append(name+crd+"\n");
        }
        return buffer.toString();
    }


    public void insert_data(String user_name,String name,String credit)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.user_name, user_name);
        contentValues.put(myDbHelper.tranfer_name, name);
        contentValues.put(myDbHelper.credit_transfer, credit);

        dbb.insert("transfer", null , contentValues);

    }
public void update(String users,String transferuser,int n)
{


    SQLiteDatabase dbb = myhelper.getWritableDatabase();

    String[] columns = {myDbHelper.NAME,myDbHelper.current_credit};
    Cursor cursor =dbb.query(myDbHelper.TABLE_NAME1,columns,null,null,null,null,null);
    while (cursor.moveToNext())
    {

        String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
        String crd =cursor.getString(cursor.getColumnIndex(myDbHelper.current_credit));
        if(name==users)
        {
            int n1=Integer.parseInt(crd)-n;
            String s1=""+n1;
            ContentValues contentValues=new ContentValues();
            contentValues.put(myDbHelper.current_credit,s1);
            dbb.update(myDbHelper.TABLE_NAME,contentValues,myDbHelper.NAME+"="+name,null);
        }


    }

}

void delete()
{
    SQLiteDatabase db = myhelper.getWritableDatabase();
    db.delete("user", null, null);
}


    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "Credit";    // Database Name
        private static final String TABLE_NAME = "user";   // Table Name
        private static final String TABLE_NAME1 = "transfer";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String email="email";     // Column I (Primary Key)
        private static final String NAME = "NAME";    //Column II
        private static final String current_credit="current_credit";    // Column III

        private static final String user_name="user_name";     // Column I (Primary Key)
        private static final String tranfer_name = "transfer_name";    //Column II
        private static final String credit_transfer= "credit_transfer";    // Column III



        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+NAME+" VARCHAR(255), "+email+" VARCHAR(255) ,"+ current_credit+" INTEGER);";

        private static final String CREATE_Transfer = "CREATE TABLE "+TABLE_NAME1+
                " ("+user_name+" VARCHAR(255), "+tranfer_name+" VARCHAR(255) ,"+ credit_transfer+" VARCHAR(225));";

        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_Transfer);
            } catch (Exception e) {

            }
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


    }
}
