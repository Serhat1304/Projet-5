package com.safety.alerts.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthDate;
    private List<String> medications;
    private List<String> allergies;

}
