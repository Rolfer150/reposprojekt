package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.provider.Settings;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    boolean isPermissionGranted; // zmienna typu bool, która będzie sprawdzać czy uzyskano dostęp
    GoogleMap mGoogleMap; // obiekt klasy GoogleMap
    SearchView searchview; // obiekt klasy SearchView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // implementacja funkcji sprawdzającej dostęp
        checkMyPermission();

        // funkcja if sprawdza czy uzyskano dostęp
        if(isPermissionGranted)
        {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            supportMapFragment.getMapAsync(this);
        }

        // implementacja paska wyszukiwania
        searchview = findViewById(R.id.map_search_view);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchview.getQuery().toString();
                List<Address> addressList = null;

                // jeśli nie podano lokalizacji to geokodowanie oblicza aktualną lokalizację
                if (location != null || location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(Mapa.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    //pobieranie współrzędnych geograficznych
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    // ustawienie znacznika lokalizacji
                    mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(location));

                    // poruszanie kamerą
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }

                return false;
            }

            // wywołanie zwrotne dotyczące zmian w tekście zapytania
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    // sprawdzenie dostępu
    private void checkMyPermission()
    {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener()
        {
            //fragment klasy wywołuje się gdy otrzyma dostęp
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse)
            {
                Toast.makeText(Mapa.this, "Uzyskano dostęp", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;
            }

            //fragment klasy wywołuje się gdy nie otrzyma dostępu
            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse)
            {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            //fragment klasy pokazuje interfejs użytkownika z uzasadnieniem
            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken)
            {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @SuppressLint("MissingPermission")

    // fragment klasy onMapReady wykrywający lokalizację sprzętu
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap = googleMap;
        mGoogleMap.setMyLocationEnabled(true);
    }

}