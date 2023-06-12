package com.safety.alerts.service;


import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.repository.MedicalRecordRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService (MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getMedicalRecord() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String lastName, String firstName) {
        return medicalRecordRepository.updateMedicalRecord(medicalRecord, firstName, lastName);
    }

    public MedicalRecord findByFirstAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findByFirstAndLastName(firstName, lastName);
    }

    public void deleteByFirstAndLastName(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstAndLastName(firstName, lastName);
    }
}
