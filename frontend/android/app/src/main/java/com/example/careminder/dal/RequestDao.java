package com.example.careminder.dal;

import static com.example.careminder.helper.RequestJsonParser.parsePostRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.careminder.helper.RequestJsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestDao {

    private static final String BASE_REQUEST_URL = "http://10.0.2.2:8000/api/requests/";

    private final RequestQueue requestQueue;

    public RequestDao(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void postRequest(final  com.example.careminder.model.Request request, final RequestCallBack callback) throws JSONException {
        Map<String, Object> params = new HashMap<>();

        params.put("text", request.getText());
        params.put("is_question", request.isQuestion());
        params.put("tablet_id", request.getTablet());
        params.put("for_role", request.getForRole());

        JSONObject jsonParams = new JSONObject(params);

        Log.d("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{TAG}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}", jsonParams.toString());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            com.example.careminder.model.Request parsedRequest = parsePostRequest(response);
                            callback.onSuccess(parsedRequest);
                        } catch (Exception e) {
                            callback.onError(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                byte[] body = new JSONObject(params).toString().getBytes();
                return body;
            }
        };


        requestQueue.add(stringRequest);
    }



    public interface RequestCallBack {
        void onSuccess(com.example.careminder.model.Request request);

        void onError(Exception e);
    }

}
