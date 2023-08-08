package com.safety.alerts.mapper;

import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.model.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class MedicalRecordMapper {

    private final ModelMapper modelMapper;

    public MedicalRecordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MedicalRecordDTO map(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = modelMapper.map(medicalRecord, MedicalRecordDTO.class);

        medicalRecordDTO.setAge(calculateAge(medicalRecord.getBirthdate()));

        return medicalRecordDTO;
    }

    public MedicalRecord map(MedicalRecordDTO medicalRecordDTO) {
        return modelMapper.map(medicalRecordDTO, MedicalRecord.class);
    }

    public Integer calculateAge(String birthdate) {
        LocalDate currentDate = LocalDate.now();
        if (birthdate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter = formatter.withLocale(Locale.FRANCE);
            LocalDate birthDate = LocalDate.parse(birthdate, formatter);
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return null;
        }
    }
}