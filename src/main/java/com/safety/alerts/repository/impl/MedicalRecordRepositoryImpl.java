package com.safety.alerts.repository.impl;

import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.repository.IMedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {

    private List<MedicalRecord> medicalRecords;

    @Override
    public void saveAll(List<MedicalRecord> medicalRecordList) {
        this.medicalRecords = medicalRecordList;
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

        return optionalMedicalRecord.orElse(null);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public boolean delete(MedicalRecord medicalRecord) {
        return medicalRecords.remove(medicalRecord);
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecords.stream()
                .filter(medicalRecord1 -> medicalRecord1.getFirstName().equals(medicalRecord.getFirstName())
                        && medicalRecord1.getLastName().equals(medicalRecord.getLastName()))
                .findFirst();

        if (optionalMedicalRecord.isPresent()) {
            MedicalRecord medicalRecord1 = optionalMedicalRecord.get();
            medicalRecord1.setBirthdate(medicalRecord.getBirthdate());
            medicalRecord1.setMedications(medicalRecord.getMedications());
            medicalRecord1.setAllergies(medicalRecord.getAllergies());

            return medicalRecord1;
        }
        return null;

    }
}