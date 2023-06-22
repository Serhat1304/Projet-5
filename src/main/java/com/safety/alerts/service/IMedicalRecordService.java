package com.safety.alerts.service;

import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.model.Person;

import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecord> getAll();

    MedicalRecord getMedicalRecords(String birthdate);

    Boolean deleteMedicalRecords(String firstName, String lastName);

    MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord addMedicalRecords(MedicalRecord medicalRecord);

    List<String> getMedicationsFromPerson(Person person);

    List<String> getAllergiesFromPerson(Person person);

    int calculateAgeFromBirthdate(String birthdate);
}
