package com.safety.alerts.model;

import lombok.Data;

import java.util.Date;

@Data
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String medications;
    private String allergies;

}
