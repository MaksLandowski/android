package com.umg.trains;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Service extends AppCompatActivity {

    LocalService mService;
    boolean mBound = false;
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        Button back= findViewById(R.id.button10);

        Button getNumber = findViewById(R.id.getNumber);
        getNumber.setOnClickListener(view -> {
            if (mBound) {
                int num = mService.getRandomNumber();
                Toast.makeText(Service.this, "number: " + num, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Service.this, "Service is not running!", Toast.LENGTH_SHORT).show();
            }
        });

        Button startService = findViewById(R.id.start_service);
        startService.setOnClickListener(view -> {
            if (!mBound) {
                Intent intent = new Intent(Service.this, LocalService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                Toast.makeText(Service.this, "Service started!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Service.this, "Service is already started!", Toast.LENGTH_LONG).show();
            }
        });


        Button stopService = findViewById(R.id.stop_Service);
        stopService.setOnClickListener(view -> {
            if (mBound) {
                unbindService(connection);
                mBound = false;
                Toast.makeText(Service.this, "Service stopped!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Service.this, "Service is not running!", Toast.LENGTH_LONG).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }
}