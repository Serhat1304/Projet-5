package com.safety.alerts.service;
import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.mapper.MedicalRecordMapper;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.util.DataHolder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Log4j2
public class MedicalRecordService implements IMedicalRecordService {

    private final MedicalRecordMapper medicalRecordMapper;
    private final DataHolder dataHolder;

    public MedicalRecordService(MedicalRecordMapper medicalRecordMapper, DataHolder dataHolder) {
        this.medicalRecordMapper = medicalRecordMapper;
        this.dataHolder = dataHolder;
    }


    @Override
    public List<MedicalRecordDTO> getAll() {
        List<MedicalRecord> medicalRecords = dataHolder.getResponse().getMedicalRecords();
        return medicalRecords.stream()
                .map(medicalRecordMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO getMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> medicalRecords = dataHolder.getResponse().getMedicalRecords();
        return medicalRecords.stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName) &&
                        medicalRecord.getLastName().equals(lastName))
                .map(medicalRecordMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public MedicalRecordDTO addMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordMapper.map(medicalRecordDTO);
        dataHolder.getResponse().getMedicalRecords().add(medicalRecord);
        return medicalRecordMapper.map(medicalRecord);
    }

    @Override
    public MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        List<MedicalRecord> medicalRecords = dataHolder.getResponse().getMedicalRecords();
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(medicalRecordDTO.getFirstName())&&
            medicalRecord.getLastName().equals(medicalRecordDTO.getLastName())) {
                medicalRecord.setBirthDate(medicalRecordDTO.getBirthDate());
                medicalRecord.setMedications(medicalRecordDTO.getMedications());
                medicalRecord.setAllergies(medicalRecordDTO.getAllergies());
                return medicalRecordMapper.map(medicalRecord);
            }
        }
        return null;
    }

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> medicalRecords = dataHolder.getResponse().getMedicalRecords();
        medicalRecords.removeIf(medicalRecord    -> medicalRecord.getFirstName().equals(firstName) &&
                medicalRecord.getLastName().equals(lastName));
    }
}
