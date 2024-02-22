package com.example.careminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.careminder.R;
import com.example.careminder.dal.RequestDao;
import com.example.careminder.model.Request;

import org.json.JSONException;

public class PatientRecordingResultActivity extends AppCompatActivity {

    private RequestDao requestDao;
    private boolean isQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_recording_result);

        Intent intent = getIntent();
        isQuestion = getIntent().getBooleanExtra("isQuestion", false);

        requestDao = new RequestDao(this);
    }

    public void OnBackButtonClick(View view) {
        Intent intent = new Intent(this, PatientRecordingActivity.class);
        startActivity(intent);
        finish();
    }

    public void OnCancelClick(View view) {
        Intent intent = new Intent(this, PatientRecordingActivity.class);
        startActivity(intent);
        finish();
    }


    public void OnSendTextClick(View view) throws JSONException {
        PostRequest();

        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
        finish();

    }


    public void OnSendMoreClick(View view) throws JSONException {
        PostRequest();

        Intent intent = new Intent(this, PatientRecordingActivity.class);
        startActivity(intent);
        finish();
    }

    private void PostRequest() throws JSONException{

        EditText request = findViewById(R.id.editText);
        String text = request.getText().toString();

        Log.d("L:LLLLLLLLLLLLLLLLLLLLLLLLLLLLL", text);

        Integer tablet = 1;

        requestDao.postRequest(new Request(text, isQuestion, tablet, 1), new RequestDao.RequestCallBack() {
            @Override
            public void onSuccess(Request request) {

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    };
}