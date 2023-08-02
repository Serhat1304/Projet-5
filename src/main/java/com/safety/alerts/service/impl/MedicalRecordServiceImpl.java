package com.safety.alerts.service.impl;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.mapper.MedicalRecordMapper;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.repository.IMedicalRecordRepository;
import com.safety.alerts.service.IMedicalRecordService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class MedicalRecordServiceImpl implements IMedicalRecordService {

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;


    @Override
    public List<MedicalRecordDTO> getAllMedicalRecord() {
        return medicalRecordRepository.getAll().stream()
                .map(medicalRecordMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO getMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);
        if(medicalRecord != null) {
            Logger.info("Getting MedicalRecord by name :  {} is successful", firstName, lastName);
            return medicalRecordMapper.map(medicalRecord);
        }Logger.error("Getting MedicalRecord by name : {} failed", firstName, lastName);
        return null;
    }

    @Override
    public MedicalRecordDTO saveMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordMapper.map(medicalRecordDTO);
        medicalRecordRepository.save(medicalRecord);
        Logger.info("Saving MedicalRecord is successful");
        return medicalRecordMapper.map(medicalRecord);
    }

    @Override
    public MedicalRecordDTO updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordMapper.map(medicalRecordDTO);
        MedicalRecord newMedicalRecord = medicalRecordRepository.update(medicalRecord);
        if (newMedicalRecord != null) {
            Logger.info("Updating MedicalRecord is successful");
            return medicalRecordMapper.map(newMedicalRecord);
        }Logger.error("Updating MedicalRecord failed");
        return null;
    }

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);
        if (medicalRecord != null) {
            medicalRecordRepository.delete(medicalRecord);
        }Logger.info("Delete MedicalRecord is successful");
    }
}
