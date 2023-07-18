package com.safety.alerts.service;

import com.safety.alerts.dto.MedicalRecordDTO;

import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecordDTO> getAll();

    MedicalRecordDTO getMedicalRecord(String firstName, String lastName);

    void addMedicalRecord(MedicalRecordDTO medicalRecordDTO);

    void updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO);

    void deleteMedicalRecord(String firstName, String lastName);
}
