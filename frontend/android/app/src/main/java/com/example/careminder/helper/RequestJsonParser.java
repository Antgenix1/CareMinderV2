package com.example.careminder.helper;

import android.util.Log;

import com.example.careminder.model.Patient;
import com.example.careminder.model.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestJsonParser {
    public static Request parsePostRequest(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String text = jsonObject.getString("text");
        boolean isQuestion = jsonObject.getBoolean("isQuestion");
        Integer tablet = jsonObject.getInt("tablet");
        Integer forRole = jsonObject.getInt("forRole");

        return new Request(text, isQuestion, tablet, forRole);
    }

    public static Request parseGetRequest(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String text = jsonObject.getString("text");
        boolean isQuestion = jsonObject.getBoolean("isQuestion");
        Request.State state = Request.State.valueOf(jsonObject.getString("state"));
        Date time = Date.valueOf(jsonObject.getString("time")); //
        String response = jsonObject.getString("response");
        Date responseTime = Date.valueOf(jsonObject.getString("responseTime"));
        Integer tablet = jsonObject.getInt("tablet");

        return new Request(text, isQuestion, state, time, response, responseTime, tablet);
    }

}

