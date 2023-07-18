package com.safety.alerts.mapper;

import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.model.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class MedicalRecordMapper {

    private final ModelMapper modelMapper;

    public MedicalRecordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MedicalRecordDTO map(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = modelMapper.map(medicalRecord, MedicalRecordDTO.class);

        medicalRecordDTO.setAge(calculateAge(medicalRecord.getBirthDate()));
        
        return medicalRecordDTO;
    }

    public MedicalRecord map(MedicalRecordDTO medicalRecordDTO) {
        return modelMapper.map(medicalRecordDTO, MedicalRecord.class);
    }

    private Integer calculateAge( LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
