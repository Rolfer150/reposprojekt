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

    SensorManager sensormanager;
    Sensor sensor;
    TextView txt2,txt3,txt4;
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
        txt3 = (TextView) findViewById(R.id.txt3);

        sensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // sensor "nasłuchuje" detektor ruchu urządzenia
        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent!= null){
                    float x_acceleration = sensorEvent.values[0]; // wykrywanie ruchu po współrzędnej x
                    float y_acceleration = sensorEvent.values[1]; // wykrywanie ruchu po współrzędnej y
                    float z_acceleration = sensorEvent.values[2]; // wykrywanie ruchu po współrzędnej z

                    // implementacja zmiennej o nazwie Magnitude, która będzie zapisywać wstrząśnięcia
                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    // liczenie kroków, jeśli moc magnitudy mieści się w przedziale 2-6
                    if (MagnitudeDelta >= 2 && MagnitudeDelta <= 6 ){
                        steps++;
                    }
                    txt2.setText(steps.toString()); // wypisanie kroków
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensormanager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // funkcja zatrzymująca krokomierz
    public void stop (View v) {
        isRunning = false; // zmienna bool sprawdzająca, czy urządzenie jest w ruchu
        startButtonPressed = false; // zmienna bool sprawdzająca, czy przycisk start jest włączony

        // jeśli wykonano mniej niż 1000 kroków
        if(steps < 1000)
            // wyświetlany jest komunikat "Gratulacje!! Przebiegłeś x kroków"
            Toast.makeText(Krokomierz.this, "Gratulacje!! Przebiegłeś " + steps + " kroków",Toast.LENGTH_LONG).show();
        // wyświetlany jest komunikat "Zrobiłeś ponad 1000 kroków!!"
        else {
            Toast.makeText(Krokomierz.this, "Zrobiłeś ponad 1000 kroków!!",Toast.LENGTH_LONG).show();
        }
        // po zatrzymaniu funkcja liczy spalone kalorie według poniższego wzoru
        txt3.setText("Średnio spalone kalorie:: " + (((float)steps/1000)*100) + " kcal");


    }

    // resetowanie licznika i zapis przebytych kroków
    public void reset (View v) {

        // tworzenie obiektu saves klasy SharedPreferences odpowiedzialnej za zapisywanie danych
        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // zmienna przechowująca liczbę kroków przebytych dzisiaj
        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        // zmienna przechowująca liczbę kroków przebytych przez miesiąc
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        // zmienna przechowująca największą liczbę kroków
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        // nadpisywanie przebytych kroków
        SharedPreferences.Editor editor = saves.edit();
        editor.putLong("STEPS_RUN_TODAY", steps + stepsRunToday);
        editor.putLong("STEPS_RUN_THIS_MONTH", steps + stepsRunThisMonth);

        // jeśli liczba przebytych kroków jest większa od poprzedniej największej liczby kroków
        if((steps+stepsRunToday) > highestStepsRunInOneDay) {
            // dane są nadpisywane
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

    }

    @Override
    protected void onPause() {
        super.onPause();

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

        if(startButtonPressed == true)
        {
            isRunning = true;
        }
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

    public void start (View view) {
        isRunning = true;
        startButtonPressed = true;
    }
}