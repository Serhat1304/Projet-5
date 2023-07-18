package com.safety.alerts.repository.impl;

import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.repository.IMedicalRecordRepository;
import com.safety.alerts.util.DataHolder;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {

    private final List<MedicalRecord> medicalRecords;

    public MedicalRecordRepositoryImpl(DataHolder dataHolder) {
        this.medicalRecords = dataHolder.getResponse().getMedicalRecords();
    }

    @Override
    public List<MedicalRecord> getAll() {
        return medicalRecords;
    }

    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecords.stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName))
                .findFirst();

        if(optionalMedicalRecord.isPresent()) {
            return optionalMedicalRecord.get();
        }

        Logger.error("MedicalRecord not found by firstName {} and lastName{}", firstName , lastName);
        return null;
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord) {
        if(medicalRecords.remove(medicalRecord)) {
            Logger.info("Medical record deleted {}", medicalRecord);
            return medicalRecord;
        }else {
            Logger.error("Failed to delete medicalRecord : {}", medicalRecord);
            return null;
        }
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecords.stream()
                .filter(medicalRecord1 -> medicalRecord1.getFirstName().equals(medicalRecord.getFirstName())
                        && medicalRecord1.getLastName().equals(medicalRecord.getLastName()))
                .findFirst();

        if (optionalMedicalRecord.isPresent()) {
            MedicalRecord medicalRecord1 = optionalMedicalRecord.get();
            medicalRecord1.setBirthDate(medicalRecord.getBirthDate());
            medicalRecord1.setMedications(medicalRecord.getMedications());
            medicalRecord1.setAllergies(medicalRecord.getAllergies());

            Logger.info("Updated MedicalRecord successful");
            return medicalRecord1;
        }Logger.error("Updated MedicalRecord failed");
        return null;

    }
}