package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private int counter = 5;

    String userName = "";
    String userPassword = "";

    // klasa przechowująca dane logowania
    class Credentials {
        String name = "Admin";
        String password = "123456";
    }

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        eLogin = findViewById(R.id.btnLogin);

        // sprawdzenie czy przycisk login został kliknięty
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // wprowadzienie danych przez użytkownika
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                // sprawdzenie czy wprowadzone dane są puste
                if (userName.isEmpty() || userPassword.isEmpty()) {
                    // wyświetlenie informacji o pustych danych
                    Toast.makeText(Login.this, "Proszę podać login i hasło", Toast.LENGTH_LONG).show();

                } else {

                    // sprawdzanie zgodności danych
                    isValid = validate(userName, userPassword);

                    // jeśli są nieprawidłowe
                    if (!isValid) {

                        // zmniejszenie licznika
                        counter--;

                        // pokazanie liczby prób zalogowań
                        eAttemptsInfo.setText("Pozostało " + String.valueOf(counter) + " prób");

                        // zablokowanie przycisku logowania gdy nie ma już więcej prób
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(Login.this, "Wyczerpałeś wszystkie próby. Spróbuj zalogować się później", Toast.LENGTH_LONG).show();
                        }
                        // wyświetlenie komunikatu o nieprawidłowych danych logowania
                        else {
                            Toast.makeText(Login.this, "Niepoprawne dane, podaj jeszcze raz", Toast.LENGTH_LONG).show();
                        }
                    }
                    // jeśli dane są poprawne
                    else {

                        // przekierowanie aplikacji do klasy Menu
                        startActivity(new Intent(Login.this, Menu.class));
                    }
                }
            }
        });
    }

    // sprawdzanie zgodności danych
    private boolean validate(String userName, String userPassword) {
        // pobranie danych z klasy Credentials
        Credentials credentials = new Credentials();

        // sprawdzanie danych logowania
        if (userName.equals(credentials.name) && userPassword.equals(credentials.password)) {
            return true;
        }

        return false;
    }
}