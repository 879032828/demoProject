package com.example.administrator.view_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.PipedInputStream;

public class VoiceActivity extends AppCompatActivity {

    PipedInputStream in;
    MAudioRecord m_audio_record;
    boolean isRecord;
    TextView start;
    TextView end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        isRecord = false;
        start = (TextView) findViewById(R.id.start);
        end = (TextView) findViewById(R.id.end);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecord) {
                    isRecord = false;
                    m_audio_record.stopRecord();
                } else {
                    isRecord = true;
                    startRecord();
                }
            }
        });
    }

    private void startRecord() {
        in = new PipedInputStream();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    m_audio_record = new MAudioRecord(VoiceActivity.this, in);
                    m_audio_record.StartAudioData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
