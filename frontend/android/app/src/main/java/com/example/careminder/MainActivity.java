package com.example.careminder;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.careminder.activity.NurseActivity;
import com.example.careminder.activity.NurseEditPatientActivity;
import com.example.careminder.activity.PatientActivity;
import com.example.careminder.activity.PatientRecordingActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnPatientButtonClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        } else {
            Intent intent = new Intent(this, PatientActivity.class);
            startActivity(intent);
        }
    }

    public void OnNurseEditPatientButtonClick(View view) {
        Intent intent = new Intent(this, NurseEditPatientActivity.class);
        startActivity(intent);

    }

    public void OnRecordButtonClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        } else {
            Intent intent = new Intent(this, PatientRecordingActivity.class);
            startActivity(intent);
        }
    }

    public void OnNurseButtonClick(View view) {
        Intent intent = new Intent(this, NurseActivity.class);
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                } else {
                    // Permission denied - show a dialog
                    showMicrophonePermissionDialog();
                }
                return;
            }
        }
    }

    private void showMicrophonePermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Microphone Permission Needed")
                .setMessage("This app needs the Microphone permission to record audio. Please grant the permission.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
