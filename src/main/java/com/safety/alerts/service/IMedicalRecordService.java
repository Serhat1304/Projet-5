package com.safety.alerts.service;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.dto.MedicalRecordDTO;

import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecordDTO> getAllMedicalRecord();

    MedicalRecordDTO getMedicalRecord(String firstName, String lastName);

    MedicalRecordDTO saveMedicalRecord(MedicalRecordDTO medicalRecordDTO);

    MedicalRecordDTO updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO);

    void deleteMedicalRecord(String firstName, String lastName);
}
