package sarveshchavan777.database1;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText ed1, ed2, ed3, ed4, ed5;
    Button b1, b2;
    Helperclass helperclass;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helperclass = new Helperclass(this);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

    }


    public void datainsert(View view) {
        String s1 = ed1.getText().toString();
        String s2 = ed2.getText().toString();
        String s3 = ed3.getText().toString();
        String s4 = ed4.getText().toString();
        SQLiteDatabase sqLiteDatabase = helperclass.getWritableDatabase();
        helperclass.insert(s1, s2, s3, s4, sqLiteDatabase);
        helperclass.close();
    }

    public void getdata(View view) {
        SQLiteDatabase sqLiteDatabase = helperclass.getWritableDatabase();
        helperclass.get(sqLiteDatabase);
    }

    public void deletable(View view) {
        SQLiteDatabase sqLiteDatabase = helperclass.getWritableDatabase();
        String s1 = ed5.getText().toString();
        helperclass.delete(sqLiteDatabase, s1);
    }

    public void deletholetable(View view) {

        SQLiteDatabase sqLiteDatabase = helperclass.getWritableDatabase();
        helperclass.deletholetable(sqLiteDatabase);

    }
}