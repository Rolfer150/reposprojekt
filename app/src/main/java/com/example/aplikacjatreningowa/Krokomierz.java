package com.example.aplikacjatreningowa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Krokomierz extends AppCompatActivity implements SensorEventListener {

    //Button btn1,btn2;

    SensorManager sensormanager;
    Sensor sensor;
    TextView txt2,txt3,txt4,txt5,txt6;
    private Integer steps = 0;
    boolean isRunning = false;
    boolean startButtonPressed = false;

    private double MagnitudePrevious = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krokomierz);

        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt4 = (TextView) findViewById(R.id.txt4);
        //btn2 = (Button) findViewById(R.id.btn2);
        txt3 = (TextView) findViewById(R.id.txt3);

        sensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent!= null){
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 3){
                        steps++;
                    }
                    txt2.setText(steps.toString());
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensormanager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop (View v) {
        isRunning = false;
        startButtonPressed = false;
        if(steps < 1000)
            Toast.makeText(Krokomierz.this, "Congrats!! You Have Run " + steps + " steps",Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(Krokomierz.this, "You are on Fire Today!!",Toast.LENGTH_LONG).show();
        }
        txt3.setText("Average calories burned:: " + (((float)steps/1000)*100) + " kcal");


    }

    public void reset (View v) {

        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        SharedPreferences.Editor editor = saves.edit();
        editor.putLong("STEPS_RUN_TODAY", steps + stepsRunToday);
        editor.putLong("STEPS_RUN_THIS_MONTH", steps + stepsRunThisMonth);
        if((steps+stepsRunToday) > highestStepsRunInOneDay) {
            editor.putLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", steps+stepsRunToday);
        }
        editor.commit();

        steps = 0;
        txt2.setText("" + steps);
        isRunning = true;
        startButtonPressed = false;
        txt3.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(startButtonPressed == true) isRunning = true;
//        Sensor countSensor = sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        if(countSensor != null){
//           sensormanager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
//        } else {
//            Toast.makeText(this,"NO sensor Found",Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(isRunning && startButtonPressed) {
            steps++;
            Log.i("Tagggggg", "" + steps);
            txt2.setText("" + steps);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void back (View view) {

        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        SharedPreferences.Editor editor = saves.edit();
        editor.putLong("STEPS_RUN_TODAY", steps + stepsRunToday);
        editor.putLong("STEPS_RUN_THIS_MONTH", steps + stepsRunThisMonth);
        if((steps+stepsRunToday) > highestStepsRunInOneDay) {
            editor.putLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", steps+stepsRunToday);
        }
        editor.commit();


        sensormanager.unregisterListener(this);

    }

    public void start (View view) {
        isRunning = true;
        startButtonPressed = true;
    }
}