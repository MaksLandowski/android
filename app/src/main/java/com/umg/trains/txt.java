package com.umg.trains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class txt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.end_game);
        Button restart= findViewById(R.id.button2);
        Toast.makeText(txt.this, "Tekst zostal stworzony", Toast.LENGTH_SHORT).show();
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
        streamwrite();
        streamread();
        rafmethod();
    }
    private void streamwrite() {
        String filename = "test.txt";
        String fileContents = "Hello world!";
        System.out.println(this.getFilesDir());
        File file = new File(this.getFilesDir(), filename);
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
            fos.write("\n".getBytes(StandardCharsets.UTF_8));
            fos.write("aaaaaa".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void streamread(){
        FileInputStream fis = null;
        try {
            fis = this.openFileInput("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line;
            while ((line = br.readLine()) != null)
            {
                text.append(line);
                text.append('\n');
            }
            br.close();
            System.out.println(text);
        }
        catch (IOException e)
        { }

    }


    private void rafmethod(){
        try {
            File f = new File(getFilesDir(), "test2.txt");
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.writeUTF("Hello World \n" );
            raf.seek(0);
            System.out.println("" + raf.readUTF());
            raf.seek(5);
            raf.writeUTF("This is an example");
            raf.seek(0);
            System.out.println("" + raf.readUTF());
            raf.close(); } catch (IOException ex) { ex.printStackTrace(); }

    }
}