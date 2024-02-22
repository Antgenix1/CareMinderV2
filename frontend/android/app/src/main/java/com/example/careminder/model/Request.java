package com.example.careminder.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Request {
    public enum State {
        WAITING(0),
        PROCESSING(1),
        FINISHED(2);

        private int value;

        private State(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private String text;
    private byte[] recording;
    private Integer forRole;
    private boolean isQuestion;
    private State state;
    private Date time;
    private String response;
    private Date responseTime;
    private Integer tablet;

    public Request(String text, boolean isQuestion, Integer tablet, Integer forRole) {
        this.text = text;
        this.isQuestion = isQuestion;
        this.tablet = tablet;
        this.forRole = forRole;
    }

    public Request(String text, boolean isQuestion, State state, Date time, String response, Date responseTime, Integer tablet) {
        this.text = text;
        this.isQuestion = isQuestion;
        this.state = state;
        this.time = time;
        this.response = response;
        this.responseTime = responseTime;
        this.tablet = tablet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getRecording() {
        return recording;
    }

    public void setRecording(byte[] recording) {
        this.recording = recording;
    }

    public Integer getForRole() {
        return forRole;
    }

    public void setForRole(Integer forRole) {
        this.forRole = forRole;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getTablet() {
        return tablet;
    }

    public void setTablet(Integer tablet) {
        this.tablet = tablet;
    }

    @Override
    public String toString() {
        return "Request{" +
                "text='" + text + '\'' +
                ", recording=" + Arrays.toString(recording) +
                ", forRole=" + forRole +
                ", isQuestion=" + isQuestion +
                ", state=" + state +
                ", time=" + time +
                ", response='" + response + '\'' +
                ", responseTime=" + responseTime +
                ", tablet=" + tablet +
                '}';
    }
}

