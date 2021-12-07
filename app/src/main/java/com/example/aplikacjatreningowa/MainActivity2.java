package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private Button cw_krokomierz;
    private Button cw_rozciaganie;
    private Button cw_ramiona;
    private Button cw_brzuch;
    private Button cw_nogi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cw_krokomierz = (Button) findViewById(R.id.cw_krokomierz);
        cw_krokomierz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKrokomierz();
            }
        });

        cw_rozciaganie = (Button) findViewById(R.id.cw_rozciaganie);
        cw_rozciaganie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRozciaganie();
            }
        });

        cw_ramiona = (Button) findViewById(R.id.cw_ramiona);
        cw_ramiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRamiona();
            }
        });

        cw_brzuch = (Button) findViewById(R.id.cw_brzuch);
        cw_brzuch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrzuch();
            }
        });

        cw_nogi = (Button) findViewById(R.id.cw_nogi);
        cw_nogi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNogi();
            }
        });
    }

    public void openKrokomierz()
    {
        Intent intent = new Intent(this, Krokomierz.class);
        startActivity(intent);
    }

    public void openRozciaganie()
    {
        Intent intent = new Intent(this, Rozciaganie.class);
        startActivity(intent);
    }

    public void openRamiona()
    {
        Intent intent = new Intent(this, Ramiona.class);
        startActivity(intent);
    }

    public void openBrzuch()
    {
        Intent intent = new Intent(this, Brzuch.class);
        startActivity(intent);
    }

    public void openNogi()
    {
        Intent intent = new Intent(this, Nogi.class);
        startActivity(intent);
    }
}