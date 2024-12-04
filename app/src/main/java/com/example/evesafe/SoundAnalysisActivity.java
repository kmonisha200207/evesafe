package com.example.evesafe;


import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SoundAnalysisActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private TextView soundLevelText;
    private final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_analysis);

        soundLevelText = findViewById(R.id.soundLevelText);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_CODE);
        } else {
            startRecording();
        }
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile("/dev/null");
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            monitorSoundLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void monitorSoundLevel() {
        new Thread(() -> {
            while (true) {
                if (mediaRecorder != null) {
                    int level = mediaRecorder.getMaxAmplitude();
                    runOnUiThread(() -> soundLevelText.setText("Sound Level: " + level));
                    if (level > 20000) {
                        runOnUiThread(() -> Toast.makeText(this, "Abnormal Sound Detected!", Toast.LENGTH_LONG).show());
                        break;
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
        }
    }
}
