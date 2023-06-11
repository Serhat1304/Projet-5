package com.safety.alerts.repository;

import com.safety.alerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public List<MedicalRecord> findAll(){
        return this.medicalRecordList;
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecordList.add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName){
        MedicalRecord newMedicalRecord = findByFirstAndLastName(firstName, lastName);
        newMedicalRecord.setBirthDate(medicalRecord.getBirthDate());
        newMedicalRecord.setMedications(medicalRecord.getMedications());
        newMedicalRecord.setAllergies(medicalRecord.getAllergies());

        return medicalRecordList.set(medicalRecordList.indexOf(findByFirstAndLastName(firstName,lastName)),newMedicalRecord);
    }

    public MedicalRecord findByFirstAndLastName(String firstName, String lastName) {
        return this.medicalRecordList.stream()
                .filter(medicalRecord -> (medicalRecord.getFirstName().equals(firstName) &&
                        medicalRecord.getLastName().equals(lastName))).findAny().orElseThrow();
    }
    public void deleteByFirstAndLastName(String firstName, String lastName) {
        this.medicalRecordList.removeIf(medicalRecord ->
                medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName));
    }
}
