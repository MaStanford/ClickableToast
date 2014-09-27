package com.stanford.toasttesting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class MyActivity extends Activity {

    private static final String TAG = MyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView listOfMethods = new TextView(this);
        Method[] methods = Toast.class.getMethods();
        Field field = null;
        Class[] classes = null;
        try {
            classes = Toast.class.getClasses();
            field = Toast.class.getField("mWM");
        }catch (NoSuchFieldException e){
            Log.d(TAG,"Didnt grab the mWM field");
        }


        for(int i = 0; i < methods.length -1 ; i++){
            listOfMethods.append(methods[i].getName() + '\n');
        }

        if(field != null)
            listOfMethods.append(field.getName());

        if(classes != null){
            for(int i = 0; i < classes.length -1 ; i++){
                listOfMethods.append(classes[i].getName() + '\n');
            }
        }

        Button mBtn = new Button(this);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runToast();
            }
        });

        ((ViewGroup) findViewById(R.id.main)).addView(mBtn);
        ((ViewGroup) findViewById(R.id.main)).addView(listOfMethods);
    }

    private void runToast(){
        Log.d(TAG,"runToast");
        Toast mToasty = new Toast(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View customView = inflater.inflate(R.layout.toast,null);
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is clicked form inside the tasot",Toast.LENGTH_SHORT).show();;
            }
        });
        mToasty.setView(customView);
        mToasty.setDuration(Toast.LENGTH_SHORT);
        mToasty.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
