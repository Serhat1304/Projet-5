package com.safety.alerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.mapper.JSONMapperImpl;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.FirestationRepositoryImpl;
import com.safety.alerts.repository.MedicalRecordRepositoryImpl;
import com.safety.alerts.repository.PersonRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService{

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private JSONMapperImpl jsonMapper;
    private PersonRepositoryImpl personRepository;
    private MedicalRecordRepositoryImpl medicalRecordRepository;
    private FirestationRepositoryImpl firestationRepository;
    private ArrayList<Person> persons;
    private ArrayList<MedicalRecord> medicalRecords;

    public PersonService() throws IOException {
        this.jsonMapper = new JSONMapperImpl(new ObjectMapper());
        this.personRepository = new PersonRepositoryImpl(new ArrayList<>(this.jsonMapper.getResponse().getPersons()));
        this.medicalRecordRepository = new MedicalRecordRepositoryImpl(new ArrayList<>(this.jsonMapper.getResponse().getMedicalRecords()));
        this.firestationRepository = new FirestationRepositoryImpl(new ArrayList<>(this.jsonMapper.getResponse().getFirestations()));
    }

    @Override
    public List<Person> getall() {
        return this.personRepository.getAll();
    }

    @Override
    public Person getPerson(String email) {
        return this.personRepository.getPerson(email);
    }

    @Override
    public Person addPerson(Person person) {
        if(getPerson(person.getEmail()) != null) {
            logger.info("Add person failed");
            return null;
        }
        logger.info("Add person successful");
        return this.personRepository.addPerson(person);
    }

    @Override
    public Person updatePerson(Person person) {
        if(getPerson(person.getEmail()) != null) {
            logger.info("Updating person is successful");
            return this.personRepository.updatePerson(person);
        }
        logger.error("Updating person failed");
        return null ;
    }

    @Override
    public Boolean deletePerson(String firstName, String lastName) {
        List<Person> personToDelete = this.personRepository.getPersonByFirstAndLastName(firstName, lastName);
        for(Person person : personToDelete) {
            this.personRepository.deletePerson(person);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> getPersonByAddress(String address) {
        return this.personRepository.getPersonsByAddress(address);
    }

    @Override
    public List<String> getEmailByCity(String city) {
        return this.personRepository.getEmailByCity(city);
    }
}
