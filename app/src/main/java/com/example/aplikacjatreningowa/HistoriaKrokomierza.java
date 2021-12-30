package com.example.aplikacjatreningowa;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoriaKrokomierza extends AppCompatActivity {

    TextView txt2,txt3,txt4;

    // spisywanie danych z klasy Krokomierz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_krokomierza);

        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);

        // tworzenie obiektu saves klasy SharedPreferences odpowiedzialnej za zapisywanie danych
        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // spisywanie liczby kroków przebytych dzisiaj
        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        // spisywanie liczby kroków przebytych w tym miesiącu
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        // spisywanie największej liczby kroków
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        txt2.setText("Najwyższy: " + highestStepsRunInOneDay);
        txt3.setText("Dzisiaj: " + stepsRunToday);
        txt4.setText("Ten miesiąc: " + stepsRunThisMonth);
    }

    public void resetScores (View view) {
        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = saves.edit();

        editor.putLong("STEPS_RUN_TODAY", 0);
        editor.putLong("STEPS_RUN_THIS_MONTH", 0);
        editor.putLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);
        editor.commit();

        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        txt2.setText("HIGHEST: " + highestStepsRunInOneDay);
        txt3.setText("TODAY: " + stepsRunToday);
        txt4.setText("THIS MONTH: " + stepsRunThisMonth);

    }
}