package com.example.careminder.model;

public class Patient {

    private String firstName;
    private String lastName;
    private Integer age;
    private boolean doctorFirstVisit;
    private Integer hospitalization;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isDoctorFirstVisit() {
        return doctorFirstVisit;
    }

    public void setDoctorFirstVisit(boolean doctorFirstVisit) {
        this.doctorFirstVisit = doctorFirstVisit;
    }

    public Integer getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(Integer hospitalization) {
        this.hospitalization = hospitalization;
    }

    public Patient(String firstName, String lastName, Integer age, boolean doctorFirstVisit, Integer hospitalization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.doctorFirstVisit = doctorFirstVisit;
        this.hospitalization = hospitalization;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", doctorFirstVisit=" + doctorFirstVisit +
                ", hospitalization=" + hospitalization +
                '}';
    }
}
