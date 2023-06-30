package com.safety.alerts.repository;

import com.safety.alerts.model.MedicalRecord;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Log4j2
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {

    private List<MedicalRecord> medicalRecords;

    public MedicalRecordRepositoryImpl(List<MedicalRecord> medicalRecords){
        this.medicalRecords = new ArrayList<>(medicalRecords);
        }

    @Override
    public List<MedicalRecord> getAll() {
        return medicalRecords;
    }

    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        for(MedicalRecord medicalRecord : medicalRecords) {
            if(Objects.equals(medicalRecord.getFirstName(), firstName) && Objects.equals(medicalRecord.getLastName(), lastName)) {
                return medicalRecord;
            }
        }
        log.error("MedicalRecord not found by firstName {} and lastName{}", firstName , lastName);
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
            log.info("Medical record deleted {}", medicalRecord);
            return medicalRecord;
        }else {
            log.error("Failed to delete medicalRecord : {}", medicalRecord);
            return null;
        }
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        int index = medicalRecords.indexOf(medicalRecord);
        if(index != -1) {
            medicalRecords.set(index, medicalRecord);
            log.info("Updated medical record : {}", medicalRecord);
            return medicalRecord;
        }else {
            log.error("Failed to updated medicalRecord : {}", medicalRecord);
            return null;
        }
    }
}