package com.example.careminder.helper;

import com.example.careminder.model.Patient;

import org.json.JSONException;
import org.json.JSONObject;

public class PatientJsonParser {
    public static Patient parsePatient(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String first_name = jsonObject.getString("first_name");
        String last_name = jsonObject.getString("last_name");
        Integer age = jsonObject.getInt("age");
        Boolean doctor_first_visit = jsonObject.getBoolean("doctor_first_visit");
        Integer hospitalization = jsonObject.getInt("hospitalization");

        return new Patient(first_name, last_name, age, doctor_first_visit, hospitalization);
    }
}
