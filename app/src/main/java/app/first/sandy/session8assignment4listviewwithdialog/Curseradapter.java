package app.first.sandy.session8assignment4listviewwithdialog;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by manjula on 18-02-2017.
 */

public class Curseradapter extends CursorAdapter {

    public Curseradapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.customlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView numb = (TextView) view.findViewById(R.id.numb);
        TextView dob = (TextView) view.findViewById(R.id.dob);


        String name1 = cursor.getString(1);
        String numb1 = cursor.getString(3);
        String dob1 = cursor.getString(2);

        name.setText(name1);
        numb.setText(numb1);
        dob.setText(dob1);



    }
}