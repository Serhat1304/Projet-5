package com.safety.alerts.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MedicalRecordDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<String> medications;
    private List<String> allergies;
    private LocalDate birthdate;
    private Integer age;
}
