package sarveshchavan777.database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by LENOVO on 12/17/2016.
 */

public class Helperclass extends SQLiteOpenHelper{

     private static final String G_DATABASE_NAME="GAMEDATA";
     private static final int G_DATABASE_VERSION=15;
     private static final String G_TABLE_NAME="GAME_TABLE";
     private static final String G_ID="_ID";
     private static final String  G_ANSWER="ANSWER";
     private static final String G_QUESTION="QUESTION";
     private static final String G_OPT1="OPT1";
     private static final String G_OPT2="OPT2";
     private static final String CREATE_TABLE=
            "CREATE TABLE "+G_TABLE_NAME+" ("+G_ID+" INTEGER PRIMARY KEY, "+G_QUESTION+" VARCHAR(255), "+G_OPT1+" VARCHAR(255),"+G_OPT2+" VARCHAR(255), "+G_ANSWER+" VARCHAR(255));";
    static final private String DROP_TABLE=
            "DROP TABLE IF EXISTS "+G_TABLE_NAME;

    private Context context;
    public Helperclass(Context context) {
        super(context,G_DATABASE_NAME , null, G_DATABASE_VERSION);
        this.context=context;
        Toast.makeText(context,"constructor called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Table created",Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {

            sqLiteDatabase.execSQL(DROP_TABLE);
            Toast.makeText(context,"Table droped if exits",Toast.LENGTH_LONG).show();
            onCreate(sqLiteDatabase);
        }catch (SQLiteException e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void insert(String q,String o1,String o2,String a,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Helperclass.G_QUESTION,q);
        contentValues.put(Helperclass.G_OPT1,o1);
        contentValues.put(Helperclass.G_OPT2,o2);
        contentValues.put(Helperclass.G_ANSWER,a);
        sqLiteDatabase.insert(Helperclass.G_TABLE_NAME,null,contentValues);
        Toast.makeText(context,"info added",Toast.LENGTH_LONG).show();

    }
    public void get(SQLiteDatabase sqLiteDatabase) {
        String coloumn[] = {Helperclass.G_ID, Helperclass.G_QUESTION, Helperclass.G_OPT1, Helperclass.G_OPT2, Helperclass.G_ANSWER};
        Cursor cursor = sqLiteDatabase.query(Helperclass.G_TABLE_NAME, coloumn, null, null, null, null, null);

        if(cursor.getCount()==0){
            Toast.makeText(context,"Table in the database is empty ",Toast.LENGTH_LONG).show();
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(1);
            String opt1 = cursor.getString(2);
            String opt2 = cursor.getString(3);
            String answer = cursor.getString(4);
            Toast.makeText(context, id + " " + question + " " + opt1 + " " + opt2 + " " + answer, Toast.LENGTH_LONG).show();
        }


    }
    //delete specific row
    public  void delete(SQLiteDatabase sqLiteDatabase,String oldname) {

        String coloumn[] = {Helperclass.G_ID, Helperclass.G_QUESTION, Helperclass.G_OPT1, Helperclass.G_OPT2, Helperclass.G_ANSWER};
        Cursor cursor = sqLiteDatabase.query(Helperclass.G_TABLE_NAME, coloumn, null, null, null, null, null);
        if(cursor.getCount()==0){
            Toast.makeText(context,"Table in the database is empty can't be deleted",Toast.LENGTH_LONG).show();
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String  question = cursor.getString(1);
            String opt1 = cursor.getString(2);
            String opt2 = cursor.getString(3);
            String answer = cursor.getString(4);
            if(oldname.equals(question)){
                String whereargs[] = {oldname};
                sqLiteDatabase.delete(Helperclass.G_TABLE_NAME, Helperclass.G_QUESTION + " =? ", whereargs);
                Toast.makeText(context, "Delted successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Delted unsuccessfully wrong QName", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void deletholetable(SQLiteDatabase sqLiteDatabase)
    {

            sqLiteDatabase.execSQL("delete from "+ Helperclass.G_TABLE_NAME);

        Toast.makeText(context, "Deleted  hole  table in database successfully", Toast.LENGTH_SHORT).show();


    }



}
