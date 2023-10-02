package com.example.aplikacjatreningowa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Kalkulator extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100; // Przeliczamy wzrost z cm na metry

            float bmi = weight / (height * height);

            String resultText;

            if (bmi < 18.5) {
                resultText = "Niedowaga";
            } else if (bmi < 24.9) {
                resultText = "Waga prawidłowa";
            } else if (bmi < 29.9) {
                resultText = "Nadwaga";
            } else {
                resultText = "Otyłość";
            }

            resultTextView.setText("BMI: " + bmi + "\n" + resultText);
        } else {
            resultTextView.setText("Proszę wprowadzić wagę i wzrost.");
        }
    }
}