package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Historia extends AppCompatActivity {

    private Button zapis_kroki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        zapis_kroki = (Button) findViewById(R.id.zapis_kroki);
        zapis_kroki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoriaKrokomierza();
            }
        });
    }

    public void openHistoriaKrokomierza()
    {
        Intent intent = new Intent(this, HistoriaKrokomierza.class);
        startActivity(intent);
    }
}