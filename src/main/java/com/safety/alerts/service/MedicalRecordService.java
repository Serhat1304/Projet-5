package com.safety.alerts.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.mapper.JSONMapperImpl;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.MedicalRecordRepositoryImpl;
import com.safety.alerts.repository.PersonRepositoryImpl;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Data
public class MedicalRecordService implements IMedicalRecordService {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

    private JSONMapperImpl jsonMapper;
    private MedicalRecordRepositoryImpl medicalRecordRepository;
    private PersonRepositoryImpl personRepository;
    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordService() throws IOException {
        this.jsonMapper = new JSONMapperImpl(new ObjectMapper());
        this.medicalRecordRepository = new MedicalRecordRepositoryImpl(new ArrayList<>(this.jsonMapper.getResponse().getMedicalRecords()));
        this.personRepository = new PersonRepositoryImpl(new ArrayList<>(this.jsonMapper.getResponse().getPersons()));
    }


    @Override
    public List<MedicalRecord> getAll() {
        return this.medicalRecordRepository.getAll();
    }

    @Override
    public MedicalRecord getMedicalRecords(String birthdate) {
        return this.medicalRecordRepository.getMedicalRecords(birthdate);
    }

    @Override
    public Boolean deleteMedicalRecords(String firstName, String lastName){
        MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstAndLastName(firstName, lastName);
        if (medicalRecord != null){
            this.medicalRecordRepository.deleteMedicalRecords(medicalRecord);
            return true;
        }return false;
    }

    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        if(this.medicalRecordRepository.getMedicalRecordsByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()) != null) {
            logger.info("Request successful");
            return  this.medicalRecordRepository.updateMedicalRecords(medicalRecord);
        }logger.error("Medical record not found");
        return null;
    }

    @Override
    public MedicalRecord addMedicalRecords(MedicalRecord medicalRecord) {
            if(getMedicalRecords(medicalRecord.getBirthDate()) != null) {
                logger.error("Medical record already exist ");
            }
            return this.medicalRecordRepository.addMedicalRecords(medicalRecord);
    }

    @Override
    public List<String> getMedicationsFromPerson(Person person) {
        for(MedicalRecord medicalRecord : this.medicalRecords) {
            if(person.getFirstName().equals(medicalRecord.getFirstName()) &&
            person.getLastName().equals(medicalRecord.getLastName())) {
                logger.info("Getting medications is successful");
                return medicalRecord.getMedications();
            }
        }
        logger.info("Getting medications failed");
        return null;
    }

    @Override
    public List<String> getAllergiesFromPerson(Person person) {
        for(MedicalRecord medicalRecord : this.medicalRecords) {
            if(person.getFirstName().equals(medicalRecord.getFirstName()) &&
            person.getLastName().equals(medicalRecord.getLastName())) {
                logger.info("Getting allergies is successful");
                return medicalRecord.getAllergies();
            }
        }
        logger.info("Getting allergies failed");
        return null;
    }

    @Override
    public int calculateAgeFromBirthdate(String birthdate) {
        LocalDate currentDate = LocalDate.now();
        if(birthdate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter = formatter.withLocale(Locale.FRANCE);
            LocalDate birthDate = LocalDate.parse(birthdate, formatter);
            logger.info("Getting age is successful");
            return Period.between(birthDate, currentDate).getYears();
        }
        logger.info("Getting age failed");
        return 0;
    }

}
