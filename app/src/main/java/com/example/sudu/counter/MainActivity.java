package com.example.sudu.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.app.Activity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int count = 0;
    Button b1;
    Button b2;
    int r=200,b=20,g=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById( R.id.button1);
        b1.setOnClickListener(this);
        b2 = (Button)findViewById( R.id.button2);
        b2.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("counter", Context.MODE_PRIVATE);
        count = sp.getInt("counter", 0 );
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText( "" + count );
        changecolor();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",count);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count=savedInstanceState.getInt("counter");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText( "" + count );
    }

    public void onClick(View v) {
        if(v.getId() == R.id.button1) {
            count++;
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText( "" + count );
            changecolor();
        }
        else if(v.getId() == R.id.button2) {
            if(count != 0)
                changecolor();
            count = 0;
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText( "" + count );
        }
    }
    public void changecolor(){
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
        Random rnd = new Random();
        r = rnd.nextInt(155)+100;
        b = rnd.nextInt(155)+100;
        g = rnd.nextInt(155)+100;
        rl.setBackgroundColor( (int)Color.rgb(r,g,b));
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sp = getSharedPreferences("counter", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("counter",count);
        ed.commit();
    }
}

