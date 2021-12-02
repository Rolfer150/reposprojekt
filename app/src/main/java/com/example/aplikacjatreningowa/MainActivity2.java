package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private Button cw_krokomierz;
    private Button cw_ramiona;

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

        cw_ramiona = (Button) findViewById(R.id.cw_ramiona);
        cw_ramiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRamiona();
            }
        });
    }

    public void openKrokomierz()
    {
        Intent intent = new Intent(this, Krokomierz.class);
        startActivity(intent);
    }

    public void openRamiona()
    {
        Intent intent = new Intent(this, Rozciaganie.class);
        startActivity(intent);
    }
}