package com.safety.alerts.repository;

import com.safety.alerts.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordRepository {

    List<MedicalRecord> getAll();

    MedicalRecord getMedicalRecord(String firstName, String lastName);

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

}
