package app.first.sandy.session8assignment4listviewwithdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Dbhealper db;
    Curseradapter curseradapter;
    Cursor cursor;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        db= new Dbhealper(this);

        Dbhealper handler = new Dbhealper(this);

        database=handler.getWritableDatabase();

        cursor= database.rawQuery("SELECT  * FROM data ",null);

        curseradapter = new Curseradapter(this,cursor);

        listView.setAdapter(curseradapter);

    }

    @Override
    //creating the menu
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }
//selecting option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addmenu) {
            // creating feilds to enter the data in a dialog

            AlertDialog.Builder additem = new AlertDialog.Builder(this);
            additem.setTitle("Enter the details");

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            final EditText entername =new EditText(MainActivity.this);
            entername.setHint("Enter your Name");
            linearLayout.addView(entername);

            final EditText enternumb =new EditText(MainActivity.this);
            enternumb.setHint("Enter your numb");
            enternumb.setInputType(InputType.TYPE_CLASS_NUMBER);
            linearLayout.addView(enternumb);

            final EditText enterdob =new EditText(MainActivity.this);
            enterdob.setHint("Enter your DOB");
            linearLayout.addView(enterdob);

            additem.setView(linearLayout);


            additem.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Beans details = new Beans();
                    details.setName(entername.getText().toString());
                    details.setPhone(enternumb.getText().toString());
                    details.setDob(enterdob.getText().toString());

                    db.open();
                    //save user input data into database
                    db.insertData(details);
                    db.close();
                    updateUI();

                }
            });

            additem.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            additem.show();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
// update the list after inserting the data
    private void updateUI(){

        Cursor update = database.rawQuery("SELECT  * FROM data ",null);
        curseradapter.changeCursor(update);


    }


}
