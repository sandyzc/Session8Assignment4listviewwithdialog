package app.first.sandy.session8assignment4listviewwithdialog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by manjula on 18-02-2017.
 */

public class Dbhealper extends SQLiteOpenHelper {

    private static final String DB_NAME = "details.db";
    private static final int DB_VER = 1;
    private static final String TABLE_NAME = "data";
    private static final String ID = "_id";
    private static final String NAME = " name ";
    private static final String NUMB = " numb ";
    private static final String DOB = " dob ";
    private Context ctx;
    private SQLiteDatabase database;


    public Dbhealper(Context context) {
        super(context,DB_NAME,null,DB_VER);
        ctx= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = " CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + "TEXT," +
                DOB + "TEXT," +
                NUMB + "TEXT )";

        db.execSQL(table);
        Log.i("create table","Creating table...........");
        Toast.makeText(ctx,"Database created",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String upgrade = "Drop Table if exists " + TABLE_NAME;
        db.execSQL(upgrade);

    }

    public void open(){

        Log.i("opendb","Checking weather database is null..........");
        if (this.database==null){
            Log.i("Database null","getting Writable database......");
            this.database = this.getWritableDatabase();
            Toast.makeText(ctx,"DATABASE is open now",Toast.LENGTH_SHORT).show();
        }

    }
    public void close(){

        if (this.database.isOpen()){
            this.database.close();
            Log.i("Close db","Database closed..........");
        }

    }

    public long insertData(Beans beans){

        ContentValues values = new ContentValues();
        values.put(NAME, beans.getName());
        values.put(NUMB, beans.getPhone());
        values.put(DOB,beans.getDob());

        return this.database.insert(TABLE_NAME,null,values);

    }




}
