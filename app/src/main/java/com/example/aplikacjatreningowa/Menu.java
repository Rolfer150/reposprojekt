// implementacja paczki
package com.example.aplikacjatreningowa;

// implmentacja bibliotek
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// deklaracja klasy Menu będącej menu naszej aplikacji
public class Menu extends AppCompatActivity {

    // deklaracja obiektów przycisków w menu uruchamiających poszczególne funkcje
    private Button button; // przycisk uruchamiający mapę
    private Button button2; // przycisk uruchamiający ćwiczenia
    private Button button3; // przycisk uruchamiający pogodę
    private Button button4; // przycisk uruchamiający historię
    private Button button5; // przycisk uruchamiający kalkulator BMI

    // fragment klasy wywoływany, gdy działanie jest tworzone po raz pierwszy.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // po kliknięciu przycisku "Mapa", zostanie wywołana funkcja openMapa przez fragment klasy onClick
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapa();
            }
        });

        // po kliknięciu przycisku "Ćwiczenia", zostanie wywołana funkcja openCwiczenia przez fragment klasy onClick
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCwiczenia();
            }
        });

        // po kliknięciu przycisku "Pogoda", zostanie wywołana funkcja openPogoda przez fragment klasy onClick
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPogoda();
            }
        });

        // po kliknięciu przycisku "Historia", zostanie wywołana funkcja openHistoria przez fragment klasy onClick
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoria();
            }
        });

        // po kliknięciu przycisku "Kalkulator", zostanie wywołana funkcja openKalkulator przez fragment klasy onClick
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKalkulator();
            }
        });
    }

    // funkcja zostanie wywołana przez fragment onClick
    public void openMapa()
    {
        // aplikacja przełączy się na klasę Mapa
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }

    public void openCwiczenia()
    {
        // aplikacja przełączy się na klasę Cwiczenia
        Intent intent = new Intent(this, Cwiczenia.class);
        startActivity(intent);
    }

    public void openPogoda()
    {
        // aplikacja przełączy się na klasę Pogoda
        Intent intent = new Intent(this, Pogoda.class);
        startActivity(intent);
    }

    public void openHistoria()
    {
        // aplikacja przełączy się na klasę Historia
        Intent intent = new Intent(this, Historia.class);
        startActivity(intent);
    }

    public void openKalkulator()
    {
        // aplikacja przełączy się na klasę Kalkulator
        Intent intent = new Intent(this, Kalkulator.class);
        startActivity(intent);
    }
}