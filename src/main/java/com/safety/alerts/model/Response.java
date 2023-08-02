package com.safety.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<Firestation> firestations;
    private List<Person> persons;
    private List<MedicalRecord> medicalrecords;

}
