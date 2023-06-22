package com.safety.alerts.repository;

import com.safety.alerts.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordRepository {

    List<MedicalRecord> getAll();

    MedicalRecord getMedicalRecords(String birthDate);

    MedicalRecord addMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord getMedicalRecordsByFirstAndLastName(String firstName, String lastName);

}
