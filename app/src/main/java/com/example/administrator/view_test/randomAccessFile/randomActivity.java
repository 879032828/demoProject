package com.example.administrator.view_test.randomAccessFile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.view_test.R;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class randomActivity extends AppCompatActivity {

    private static String TAG = randomActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Button read = (Button) findViewById(R.id.read);
        Button readDataOutputStream = (Button) findViewById(R.id.readDataOutputStream);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readRandomAccessFile();
            }
        });

        readDataOutputStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataOutputStream();
            }
        });
    }

    private void readRandomAccessFile() {
        File file = new File(getApplicationContext().getExternalCacheDir() + "/test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String str = "1asdfghj2345678";
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            try {
//                fileOutputStream.write(str.getBytes());
//                fileOutputStream.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        byte[] result = new byte[1024];
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.write(str.getBytes());
                randomAccessFile.read(result);
                String showContent = new String(result);
                Log.d(TAG, "randomAccessFile的大小：" + randomAccessFile.length());
                Log.d(TAG, "字符串：" + showContent);
                Log.d(TAG, "字符串长度：" + showContent.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void readDataOutputStream() {
        File file = new File(getApplicationContext().getExternalCacheDir() + "/test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
