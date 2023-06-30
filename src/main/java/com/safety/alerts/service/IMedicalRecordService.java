package com.safety.alerts.service;

import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.model.Person;

import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecordDTO> getAll();

    MedicalRecordDTO getMedicalRecord(String firstName, String lastName);

    MedicalRecordDTO addMedicalRecord(MedicalRecordDTO medicalRecordDTO);

    MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecordDTO);

    void deleteMedicalRecord(String firstName, String lastName);
}
