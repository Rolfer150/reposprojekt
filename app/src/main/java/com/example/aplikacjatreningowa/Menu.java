// implementacja paczki
package com.example.aplikacjatreningowa;

// implmentacja bibliotek
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    private Button button6; // przycisk wyloguj z aplikacji

    // fragment klasy wywoływany, gdy działanie jest tworzone po raz pierwszy.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Uruchom usługę, która planuje powiadomienia co godzinę
        Intent serviceIntent = new Intent(this, WaterReminderService.class);
        startService(serviceIntent);

        // po kliknięciu przycisku "Mapa", zostanie wywołana funkcja openMapa przez fragment klasy onClick
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapa();
            }
        });

        // po kliknięciu przycisku "Pogoda", zostanie wywołana funkcja openPogoda przez fragment klasy onClick
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPogoda();
            }
        });

        // po kliknięciu przycisku "Cwiczenia", zostanie wywołana funkcja openCwiczenia przez fragment klasy onClick
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openCwiczenia();
            }
        });

        // po kliknięciu przycisku "Kalkulator BMI", zostanie wywołana funkcja openHistoria przez fragment klasy onClick
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openKalkulator();
            }
        });

        // po kliknięciu przycisku "Historia", zostanie wywołana funkcja openHistoria przez fragment klasy onClick
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHistoria();
            }
        });

        button6 = (Button) findViewById(R.id.btnNotifications);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                makeNotification();
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

    public void makeNotification(){
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), channelID);
        builder.setSmallIcon(R.drawable.id_water)
                .setContentTitle("Nawodnienie organizmu")
                .setContentText("Minęła godzina bez picia, powinieneś uzupełnić płyny pijąc szklankę wody!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "Wartość do wprowadzenia.");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "Opis", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0, builder.build());
    }
}