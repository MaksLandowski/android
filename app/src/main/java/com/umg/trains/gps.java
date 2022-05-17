package com.umg.trains;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class gps extends AppCompatActivity {

    Location pozycja;
    Location popPozycja;
    double wide, length, distanceInM, fullDistance;
    int stepLength = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gps);
        Button back= findViewById(R.id.button9);

        TextView textView = findViewById(R.id.szer);

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                textView.setText("No permission to GPS");
                            }
                        }
                );

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener sluchacz = new LocationListener() {
            @Override
            public void onLocationChanged(Location pozycjaW) {
                pozycja = pozycjaW;
                if (popPozycja == null) {
                    popPozycja = pozycjaW;
                }
                wide = pozycjaW.getLatitude();
                length = pozycjaW.getLongitude();
                distanceInM = pozycjaW.distanceTo(popPozycja);
                fullDistance += distanceInM;
                String odl;
                odl = "\nwidth " + String.format("%10.6f", wide) + "\nlength " +
                        String.format("%10.6f", length);
                odl += "\ndistance " +
                        String.format("%10.1f", distanceInM) + "\nfull distance" +
                        String.format("%10.1f", fullDistance) + "\nnumber of steps " +
                        String.format("%06d", (int) (fullDistance / stepLength));

                popPozycja = pozycjaW;

                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(pozycjaW.getLatitude(), pozycjaW.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        String cityName = addresses.get(0).getLocality();
                        odl += "\nMiasto: " + cityName;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                textView.setText(odl);

            }
        };

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, sluchacz);
        } catch (SecurityException se) {
            Log.v("no access", "security problems");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
}