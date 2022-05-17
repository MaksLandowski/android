package com.umg.trains;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Camera extends AppCompatActivity {
    //

    public static final int RequestPermissionCode = 1;
    ImageView imageView;

    ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bitmap bitmap = (Bitmap) (result.getData() != null ? result.getData().getExtras().get("data") : null);
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.camera);
        imageView = findViewById(R.id.imageView);
        Button back= findViewById(R.id.button11);
        EnableRuntimePermission();
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }

    public void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Camera.this,
                Manifest.permission.CAMERA)) {
            Toast.makeText(Camera.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Camera.this, new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        if (requestCode == RequestPermissionCode) {
            if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Camera.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Camera.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
            }
        }
    }
}