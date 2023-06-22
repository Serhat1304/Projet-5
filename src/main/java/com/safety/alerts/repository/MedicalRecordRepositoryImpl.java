package com.safety.alerts.repository;

import com.safety.alerts.model.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordRepositoryImpl.class);

    private List<MedicalRecord> medicalRecords;
    public MedicalRecordRepositoryImpl(ArrayList<MedicalRecord> medicalRecords) {
        if(this.medicalRecords == null) {
            this.medicalRecords = new ArrayList<>();
        }
        this.medicalRecords.addAll(medicalRecords);
    }
    @Override
    public List<MedicalRecord> getAll() {
        return this.medicalRecords;
    }

    @Override
    public MedicalRecord getMedicalRecords(String birthDate) {
        for (MedicalRecord medicalRecord : this.medicalRecords) {
            if (Objects.equals(medicalRecord.getBirthDate(), birthDate)) ;
            logger.info("Request getting MedicalRecords successful");
            return medicalRecord;
        }
        logger.info("Request is successful");
        return null;
    }


    @Override
    public MedicalRecord addMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord deleteMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.remove(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.set(getAll()
                .indexOf(getMedicalRecordsByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())),medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord getMedicalRecordsByFirstAndLastName(String firstName, String lastName) {
        for(MedicalRecord medicalRecord : this.medicalRecords) {
            if(Objects.equals(medicalRecord.getFirstName(),firstName) && Objects.equals(medicalRecord.getLastName(), lastName)) {
                logger.info("Request getting medicalRecord by First and Last name is successful");
            }
        }
        logger.info("Request is successful");
        return null;
    }

}