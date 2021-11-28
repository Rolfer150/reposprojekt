package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private Button cw_krokomierz;

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
    }

    public void openKrokomierz()
    {
        Intent intent = new Intent(this, Krokomierz.class);
        startActivity(intent);
    }
}