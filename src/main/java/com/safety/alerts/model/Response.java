package com.safety.alerts.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    List<Firestation> firestations;
    List<Person> persons;
    List<MedicalRecord> medicalRecords;

}
