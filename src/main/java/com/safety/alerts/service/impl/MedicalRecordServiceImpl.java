package com.safety.alerts.service.impl;

import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.mapper.MedicalRecordMapper;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.repository.IMedicalRecordRepository;
import com.safety.alerts.service.IMedicalRecordService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class MedicalRecordServiceImpl implements IMedicalRecordService {

    private final MedicalRecordMapper medicalRecordMapper;
    private final IMedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordMapper medicalRecordMapper, IMedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordMapper = medicalRecordMapper;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    @Override
    public List<MedicalRecordDTO> getAll() {
        return medicalRecordRepository.getAll().stream()
                .map(medicalRecordMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO getMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);

        if(medicalRecord != null) {
            return medicalRecordMapper.map(medicalRecord);
        }

        return null;
    }

    @Override
    public void addMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        medicalRecordRepository.addMedicalRecord(medicalRecordMapper.map(medicalRecordDTO));
    }

    @Override
    public void updateMedicalRecord(String firstName, String lastName,MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);
        if (medicalRecord != null) {
            MedicalRecord newMedicalRecord = medicalRecordMapper.map(medicalRecordDTO);
            medicalRecord.setBirthDate(newMedicalRecord.getBirthDate());
            medicalRecord.setMedications(newMedicalRecord.getMedications());
            medicalRecord.setAllergies(newMedicalRecord.getAllergies());
            medicalRecordRepository.updateMedicalRecord(medicalRecord);
        }
    }


    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);
        if (medicalRecord != null) {
            medicalRecordRepository.deleteMedicalRecord(medicalRecord);
        }
    }
}
