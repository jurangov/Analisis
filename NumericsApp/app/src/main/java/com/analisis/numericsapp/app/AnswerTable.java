package com.analisis.numericsapp.app;

import android.content.SyncStatusObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static java.lang.Class.*;

public class AnswerTable extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_table);

        double [][] Amatrix = WrapperMatrix.matrix;

        ScrollView sv = new ScrollView(this);

        TableLayout ll=new TableLayout(this);
        HorizontalScrollView hsv = new HorizontalScrollView(this);

        for(int i=0; i<Amatrix.length; i++)
        {
            TableRow tbrow2=new TableRow(this);

            for(int j=0; j<Amatrix[i].length; j++)
            {
                TextView tv1=new TextView(this);
                tv1.setId(j);
                tv1.setText(String.format("%.3f", Amatrix[i][j]));
                tbrow2.addView(tv1);
            }
            ll.addView(tbrow2);
        }
        hsv.addView(ll);
        sv.addView(hsv);
        setContentView(sv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.answer_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
