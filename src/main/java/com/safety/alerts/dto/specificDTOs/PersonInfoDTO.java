package com.safety.alerts.dto.specificDTOs;

import com.safety.alerts.dto.MedicalRecordDTO;
import lombok.Data;

@Data
public class PersonInfoDTO {
    private String lastName;
    private String firstName;
    private String address;
    private Integer age;
    private String mail;
    private MedicalRecordDTO medicalRecordDTO;
}
