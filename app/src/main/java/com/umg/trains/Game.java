package com.umg.trains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    boolean hasBeenPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game);
        Button shootingButton = findViewById(R.id.button);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);



        new CountDownTimer(30000,1000){
            public void onTick(long millisUntilFinished){
                if(hasBeenPaused==false) {
                    progressBar2.setProgress(progressBar2.getProgress() + 5);

                    if (progressBar2.getProgress() == 100) {
                        Toast.makeText(Game.this, "Your Message2", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), End_game.class);
                        startActivity(intent);
                        cancel();
                    }
                }
            }

            public void onFinish(){

            }
        }.start();

        shootingButton.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(progressBar.getProgress()+20);
                if(progressBar.getProgress()==100){
                    Toast.makeText(Game.this, "Your Message1", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), End_game.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        hasBeenPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        hasBeenPaused = false;
    }

}