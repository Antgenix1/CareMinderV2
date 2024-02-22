package com.example.careminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.careminder.R;
import com.example.careminder.dal.PatientDao;
import com.example.careminder.model.Patient;
import com.example.careminder.model.Request;
import com.example.careminder.touch.OnSwipeTouchListener;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private PatientDao patientDao;
    private FrameLayout popupContainer;
    private Button listButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientDao = new PatientDao(this);

        popupContainer = findViewById(R.id.popupContainer);
        listButton = findViewById(R.id.list_button);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupContainer.getVisibility() == View.VISIBLE) {
                    popupContainer.setVisibility(View.GONE);
                } else {
                    popupContainer.setVisibility(View.VISIBLE);
                }
            }
        });

        // Set request amounts //
        TextView amount_of_requests = findViewById(R.id.amount_of_requests);
        amount_of_requests.setText("42");

        TextView amount_waiting = findViewById(R.id.waiting_amount);
        amount_waiting.setText("3");
        TextView progress_amount = findViewById(R.id.progress_amount);
        progress_amount.setText("5");
        TextView complete_amount = findViewById(R.id.complete_amount);
        complete_amount.setText("2");


        // Set request amounts //

        // params //
        int margin = 16;
        int padding = 20;
        int width = 320;
        int height = 100;

        // top items title //
        LinearLayout title_row = findViewById(R.id.horizontal_scroll_description_title);
        String[] titles = {
                "Name",
                "Doctor",
                "Nurse",
                "1st Visit",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Hospitalization"
        };
        for (int i = 0; i < titles.length; i++) {
            String item = titles[i];

            TextView textView = new TextView(PatientActivity.this);
            textView.setText(item);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.setMargins(margin, 0, margin, 0);
            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.rounded_box_light);

            textView.setPadding(padding, padding, padding, padding);
            textView.setGravity(Gravity.CENTER);

            title_row.addView(textView);
        }

        // bottom items text //

        LinearLayout text_row = findViewById(R.id.horizontal_scroll_description_text);
        String[] text = {
                "Name",
                "Doctor",
                "Nurse",
                "1st Visit",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Examinations",
                "Hospitalization"
        };
        for (int i = 0; i < text.length; i++) {
            String item = text[i];

            TextView textView = new TextView(PatientActivity.this);
            textView.setText(item);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

            params.setMargins(margin, 0, margin, 0);
            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.rounded_box_response);

            textView.setPadding(padding, padding, padding, padding);
            textView.setGravity(Gravity.CENTER);

            text_row.addView(textView);
        }

    }


    public void OnRequestButtonClick(View view) {
        Intent intent = new Intent(this, PatientRecordingActivity.class);
        startActivity(intent);
        intent.putExtra("isQuestion", false);
    }

    public void OnQuestionButtonClick(View view) {
        Intent intent = new Intent(this, PatientRecordingActivity.class);
        startActivity(intent);
        intent.putExtra("isQuestion", true);
    }

    private void getPatients() {
        patientDao.getPatientById(new PatientDao.PatientCallBack(){
            @Override
            public void onSuccess(Patient patient){
                Log.d("Patient", patient.toString());
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    };
}
