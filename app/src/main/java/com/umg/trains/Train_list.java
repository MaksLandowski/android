package com.umg.trains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Train_list extends AppCompatActivity {

    private static final String TAG = "MainActivity3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.train_list);
        ListView trainsListView = (ListView) findViewById(R.id.trainsList);
        Button menu= findViewById(R.id.button6);

        Train train1 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train2 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train3 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train4 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train5 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train6 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train7 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train8 = new Train(100,100,100,100,100,R.drawable.progress_my);
        Train train9 = new Train(100,100,100,100,100,R.drawable.progress_my);

        ArrayList<Train> trainsList = new ArrayList<>();
        trainsList.add(train1);
        trainsList.add(train2);
        trainsList.add(train3);
        trainsList.add(train4);
        trainsList.add(train5);
        trainsList.add(train6);
        trainsList.add(train7);
        trainsList.add(train8);
        trainsList.add(train9);

        TrainAdapter adapter = new TrainAdapter(this, R.layout.adapter_view_layout, trainsList);
        trainsListView.setAdapter(adapter);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
}