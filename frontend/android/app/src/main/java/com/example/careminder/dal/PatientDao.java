package com.example.careminder.dal;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.careminder.helper.PatientJsonParser;
import com.example.careminder.model.Patient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientDao {
    private static final String BASE_PATIENT_URL = "http://10.0.2.2:8000/patients/";
    private static final Integer PATIENT = 2;
    private final RequestQueue requestQueue;

    public PatientDao(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

//    public void getPatients(final PatientsCallBack callback) {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_PATIENT_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            List<Patient> patient = PatientJsonParser.parsePatients(response);
//                            callback.onSuccess(patient);
//                        } catch (Exception e) {
//                            callback.onError(e);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callback.onError(error);
//            }
//        });
//        requestQueue.add(stringRequest);
//    }
//
//    public void postPatient(final Patient patient, final PatientCallBack callback) throws JSONException {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", patient.getName());
//        params.put("description", patient.getDescription());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_PATIENT_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            Patient patient = PatientJsonParser.parseInsertedPatientId(response);
//                            callback.onSuccess(patient);
//
//                        } catch (Exception e) {
//                            callback.onError(e);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callback.onError(error);
//            }
//        }) {
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }
//
//            @Override
//            public byte[] getBody() {
//                byte[] body = new JSONObject(params).toString().getBytes();
//                return body;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }


    public void getPatientById(final PatientCallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_PATIENT_URL + PATIENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Patient patient = PatientJsonParser.parsePatient(response);
                            callback.onSuccess(patient);
                        } catch (Exception e) {
                            callback.onError(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
        requestQueue.add(stringRequest);
    }


    public interface PatientCallBack {
        void onSuccess(Patient patient);

        void onError(Exception e);
    }
    
    public interface PatientsCallBack {
        void onSuccess(List<Patient> patient) throws JSONException;

        void onError(Exception e);
    }


}
